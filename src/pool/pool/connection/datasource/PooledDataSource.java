/**
 * 
 */
package pool.connection.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.sql.DataSource;

import pool.connection.ConnectionPoolConfig;
import pool.connection.PoolableConnection;

/**
 * 连接池实现的DataSource
 * <p>
 * 实际上和 pool.connection.ConnectionPool是一样的。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-31
 */
public class PooledDataSource implements DataSource {
	
	/** 长时间没使用的时限 3小时 */
	private static final long LONG_TIME_NO_USE = 3 * 60 * 60 * 1000;
	
	// 使用LinkedList做容器，是因为插入和删除操作比较多
	private LinkedList<PoolableConnection> pool;
	
	private ConnectionPoolConfig config;
	
	// 定时器，用于定时收集并关闭长时间不用的连接，提高数据库的性能
	// 比如某系统白天的时候，需要差不多max数量的连接数，但到了晚上min数量就足够了
	// 那么使用定时器进行检查，回收长时间不用的连接，可以使得晚上池中连接的数量降下来
	private Timer collectTimer;
	
	public PooledDataSource(ConnectionPoolConfig config) {
		this.config = config;
		this.pool = new LinkedList<PoolableConnection>();
		this.collectTimer = new Timer("collector", true);
		initPool();
	}

	private void initPool() {
		addNewConnections(config.getInitSize());
		collectTimer.schedule(new CollectTask(), LONG_TIME_NO_USE, LONG_TIME_NO_USE);
	}

	private void addNewConnections(int amount) {
		for (int i = 0; i < amount; i++) {
			if (pool.size() > config.getMaxSize()) {
				throw new RuntimeException("Too many connections.");
			}
			Connection connection = createDBConnection();
			if (connection != null) {
				pool.add(new PoolableConnection(connection));
			}
		}
	}
	
	private Connection createDBConnection() {
		try {
			return DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取一个数据库连接
	 */
	public synchronized Connection getConnection() {
		Connection con = findFreeConnection();
		if (con == null) {
			recycleTimeoutMandatorily();
			addNewConnections(10);
			con = findFreeConnection();
		}
		return con;
	}

	private Connection findFreeConnection() {
		ListIterator<PoolableConnection> it = pool.listIterator();
		while (it.hasNext()) {
			PoolableConnection pc = (PoolableConnection) it.next();
			if (!pc.isInUse()) {// 空闲
				setInUse(pc);
				return pc;
			}
		}
		return null;
	}
	

	/**
	 * 强制回收超时的连接
	 * <p>
	 * 强制回收是指关闭真正的数据库连接，而不是简单改变使用状态。
	 * 之所以这么做，是因为若仅仅改变使用状态，那此连接很有可能会被再次分配使用，
	 * 而此前使用超时的客户端程序也可能会继续使用，造成并发问题。
	 */
	private void recycleTimeoutMandatorily() {
		// 另起一个线程执行强制回收任务
		new MandatoryRecycler().start();
	}

	private boolean isTimeOut(PoolableConnection pc) {
		return pc.isInUse()
				&& ((System.currentTimeMillis() - pc.getLastUsedTime()) > config.getTimeOut());
	}

	private void setInUse(PoolableConnection pc) {
		pc.setInUse(true);
		pc.setLastUsedTime(System.currentTimeMillis());
	}
	
	/**
	 * 强制回收器
	 */
	private class MandatoryRecycler extends Thread{
		
		public void run() {
			ListIterator<PoolableConnection> it = pool.listIterator();
			while (it.hasNext()) {
				PoolableConnection pc = (PoolableConnection) it.next();
				if (isTimeOut(pc)) {// 超时
					pc.realClose();
					it.remove();
				}
			}
		}
	}
	
	/**
	 * 收集器，用于收集长时间未使用的连接
	 */
	private class CollectTask extends TimerTask {

		public void run() {
			// 保证池内持有的连接不少于最小值
			if (pool.size() <= config.getMinSize()) {
				return;
			}
			ListIterator<PoolableConnection> it = pool.listIterator();
			while (it.hasNext()) {
				PoolableConnection pc = (PoolableConnection) it.next();
				if (!pc.isInUse()) {
					boolean isLongTimeNoUse = (System.currentTimeMillis() - pc
							.getLastUsedTime()) > LONG_TIME_NO_USE;
					if (isLongTimeNoUse && pool.size() > config.getMinSize()) {
						pc.realClose();
						it.remove();
					}
				}
			}
		}
	}

	/**
	 * 下面全是DataSource接口的方法，只实现必须的
	 */
	
	
	public Connection getConnection(String username, String password)
			throws SQLException {
		config.setUser(username);
		config.setPassword(password);
		return getConnection();
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}
}
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
 * ���ӳ�ʵ�ֵ�DataSource
 * <p>
 * ʵ���Ϻ� pool.connection.ConnectionPool��һ���ġ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-5-31
 */
public class PooledDataSource implements DataSource {
	
	/** ��ʱ��ûʹ�õ�ʱ�� 3Сʱ */
	private static final long LONG_TIME_NO_USE = 3 * 60 * 60 * 1000;
	
	// ʹ��LinkedList������������Ϊ�����ɾ�������Ƚ϶�
	private LinkedList<PoolableConnection> pool;
	
	private ConnectionPoolConfig config;
	
	// ��ʱ�������ڶ�ʱ�ռ����رճ�ʱ�䲻�õ����ӣ�������ݿ������
	// ����ĳϵͳ�����ʱ����Ҫ���max������������������������min�������㹻��
	// ��ôʹ�ö�ʱ�����м�飬���ճ�ʱ�䲻�õ����ӣ�����ʹ�����ϳ������ӵ�����������
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
	 * ��ȡһ�����ݿ�����
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
			if (!pc.isInUse()) {// ����
				setInUse(pc);
				return pc;
			}
		}
		return null;
	}
	

	/**
	 * ǿ�ƻ��ճ�ʱ������
	 * <p>
	 * ǿ�ƻ�����ָ�ر����������ݿ����ӣ������Ǽ򵥸ı�ʹ��״̬��
	 * ֮������ô��������Ϊ�������ı�ʹ��״̬���Ǵ����Ӻ��п��ܻᱻ�ٴη���ʹ�ã�
	 * ����ǰʹ�ó�ʱ�Ŀͻ��˳���Ҳ���ܻ����ʹ�ã���ɲ������⡣
	 */
	private void recycleTimeoutMandatorily() {
		// ����һ���߳�ִ��ǿ�ƻ�������
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
	 * ǿ�ƻ�����
	 */
	private class MandatoryRecycler extends Thread{
		
		public void run() {
			ListIterator<PoolableConnection> it = pool.listIterator();
			while (it.hasNext()) {
				PoolableConnection pc = (PoolableConnection) it.next();
				if (isTimeOut(pc)) {// ��ʱ
					pc.realClose();
					it.remove();
				}
			}
		}
	}
	
	/**
	 * �ռ����������ռ���ʱ��δʹ�õ�����
	 */
	private class CollectTask extends TimerTask {

		public void run() {
			// ��֤���ڳ��е����Ӳ�������Сֵ
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
	 * ����ȫ��DataSource�ӿڵķ�����ֻʵ�ֱ����
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
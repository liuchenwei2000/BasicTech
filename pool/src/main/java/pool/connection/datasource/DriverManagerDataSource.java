/**
 * 
 */
package pool.connection.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * DriverManager实现的数据源
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-31
 */
public class DriverManagerDataSource implements DataSource {
	
	private String dbUrl;
    private String user;
    private String password;
	
	public DriverManagerDataSource(String dbUrl, String user, String password) {
		this.dbUrl = dbUrl;
		this.user = user;
		this.password = password;
		initDrivers();
	}

	private void initDrivers() {
		// load drivers
	}

	/**
	 * 获取一个数据库连接
	 */
	public Connection getConnection() throws SQLException {
		// 每次都返回一个新的数据库连接
		return DriverManager.getConnection(this.dbUrl, this.user, this.password);
	}
	
	/**
	 * 下面全是DataSource接口的方法，只实现必须的
	 */
	public Connection getConnection(String username, String password)
			throws SQLException {
		return DriverManager.getConnection(dbUrl, username, password);
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

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}
}

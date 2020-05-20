/**
 * 
 */
package pool.connection;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 可缓存到池的数据库连接
 * <p>
 * 实际上是真正数据库连接的代理类，主要是为了重写close()方法。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-31
 */
public class PoolableConnection implements Connection {
	
	private boolean isInUse;// 是否正被使用
	
	private long lastUsedTime;// 最后一次被使用的时间
	
	private Connection realConnection;// 真正的数据库连接
	
	public PoolableConnection(Connection realConnection) {
		this.realConnection = realConnection;
	}
	
	/**
	 * 要特别实现close()方法而不是委托给真正的数据库连接对象
	 * <p>
	 * 对于客户端程序而言，一旦调用了close()方法，理应不该再使用连接，
	 * 但是从本方法实现来看，即便客户端再次使用此连接，仍然是可以的。
	 *
	 * @see java.sql.Connection#close()
	 */
	public void close() throws SQLException {
		// 在这里不能真正关闭数据库连接，否则的话无法达到连接可重用的目的。
		setInUse(false);
	}
	
	/**
	 * 真正关闭数据库连接
	 */
	public void realClose() {
		if (realConnection != null) {
			try {
				realConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isClosed() throws SQLException {
		return !isInUse;
	}
	
	public boolean isInUse() {
		return isInUse;
	}

	public void setInUse(boolean isInUse) {
		this.isInUse = isInUse;
	}

	public long getLastUsedTime() {
		return lastUsedTime;
	}

	public void setLastUsedTime(long lastUsedTime) {
		this.lastUsedTime = lastUsedTime;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return realConnection.unwrap(iface);
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return realConnection.isWrapperFor(iface);
	}

	public Statement createStatement() throws SQLException {
		return realConnection.createStatement();
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return realConnection.prepareStatement(sql);
	}

	public CallableStatement prepareCall(String sql) throws SQLException {
		return realConnection.prepareCall(sql);
	}

	public String nativeSQL(String sql) throws SQLException {
		return realConnection.nativeSQL(sql);
	}

	public void setAutoCommit(boolean autoCommit) throws SQLException {
		realConnection.setAutoCommit(autoCommit);
	}

	public boolean getAutoCommit() throws SQLException {
		return realConnection.getAutoCommit();
	}

	public void commit() throws SQLException {
		realConnection.commit();
	}

	public void rollback() throws SQLException {
		realConnection.rollback();
	}

	public DatabaseMetaData getMetaData() throws SQLException {
		return realConnection.getMetaData();
	}

	public void setReadOnly(boolean readOnly) throws SQLException {
		realConnection.setReadOnly(readOnly);
	}

	public boolean isReadOnly() throws SQLException {
		return realConnection.isReadOnly();
	}

	public void setCatalog(String catalog) throws SQLException {
		realConnection.setCatalog(catalog);
	}

	public String getCatalog() throws SQLException {
		return realConnection.getCatalog();
	}

	public void setTransactionIsolation(int level) throws SQLException {
		realConnection.setTransactionIsolation(level);
	}

	public int getTransactionIsolation() throws SQLException {
		return realConnection.getTransactionIsolation();
	}

	public SQLWarning getWarnings() throws SQLException {
		return realConnection.getWarnings();
	}

	public void clearWarnings() throws SQLException {
		realConnection.clearWarnings();
	}

	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return realConnection.createStatement(resultSetType,
				resultSetConcurrency);
	}

	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return realConnection.prepareStatement(sql, resultSetType,
				resultSetConcurrency);
	}

	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return realConnection.prepareCall(sql, resultSetType,
				resultSetConcurrency);
	}

	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return realConnection.getTypeMap();
	}

	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		realConnection.setTypeMap(map);
	}

	public void setHoldability(int holdability) throws SQLException {
		realConnection.setHoldability(holdability);
	}

	public int getHoldability() throws SQLException {
		return realConnection.getHoldability();
	}

	public Savepoint setSavepoint() throws SQLException {
		return realConnection.setSavepoint();
	}

	public Savepoint setSavepoint(String name) throws SQLException {
		return realConnection.setSavepoint(name);
	}

	public void rollback(Savepoint savepoint) throws SQLException {
		realConnection.rollback(savepoint);
	}

	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		realConnection.releaseSavepoint(savepoint);
	}

	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return realConnection.createStatement(resultSetType,
				resultSetConcurrency, resultSetHoldability);
	}

	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return realConnection.prepareStatement(sql, resultSetType,
				resultSetConcurrency, resultSetHoldability);
	}

	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return realConnection.prepareCall(sql, resultSetType,
				resultSetConcurrency, resultSetHoldability);
	}

	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		return realConnection.prepareStatement(sql, autoGeneratedKeys);
	}

	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
			throws SQLException {
		return realConnection.prepareStatement(sql, columnIndexes);
	}

	public PreparedStatement prepareStatement(String sql, String[] columnNames)
			throws SQLException {
		return realConnection.prepareStatement(sql, columnNames);
	}

	public Clob createClob() throws SQLException {
		return realConnection.createClob();
	}

	public Blob createBlob() throws SQLException {
		return realConnection.createBlob();
	}

	public NClob createNClob() throws SQLException {
		return realConnection.createNClob();
	}

	public SQLXML createSQLXML() throws SQLException {
		return realConnection.createSQLXML();
	}

	public boolean isValid(int timeout) throws SQLException {
		return realConnection.isValid(timeout);
	}

	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {
		realConnection.setClientInfo(name, value);
	}

	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {
		realConnection.setClientInfo(properties);
	}

	public String getClientInfo(String name) throws SQLException {
		return realConnection.getClientInfo(name);
	}

	public Properties getClientInfo() throws SQLException {
		return realConnection.getClientInfo();
	}

	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		return realConnection.createArrayOf(typeName, elements);
	}

	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		return realConnection.createStruct(typeName, attributes);
	}

	@Override
	public void setSchema(String schema) throws SQLException {
		realConnection.setSchema(schema);
	}

	@Override
	public String getSchema() throws SQLException {
		return realConnection.getSchema();
	}

	@Override
	public void abort(Executor executor) throws SQLException {
		realConnection.abort(executor);
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds)
			throws SQLException {
		realConnection.setNetworkTimeout(executor, milliseconds);
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		return realConnection.getNetworkTimeout();
	}
}
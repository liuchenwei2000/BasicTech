/**
 * 
 */
package pool.connection;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库连接池管理器
 * <p>
 * 主要应用在系统使用多数据库的场景下，管理各个连接池。
 * 如果是单个数据库的话，仅仅使用数据库连接池就足够了。
 * <p>
 * 本类对象一般都是单例，运行在JavaEE应用服务器中，统一管理数据库连接池。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-31
 */
public class ConnectionPoolManager {

	// 单例
	private static final ConnectionPoolManager INSTANCE = new ConnectionPoolManager();

	// 数据源_数据库连接池_Map
	private Map<String, ConnectionPool> datasource_pool_map;

	private ConnectionPoolManager() {
		init();
	}

	private void init() {
		loadDrivers();
		initDataSourcePoolMap();
	}

	private void loadDrivers() {
		try {
			// 这里可以根据需要，加载使用到的所有数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void initDataSourcePoolMap() {
		datasource_pool_map = new HashMap<String, ConnectionPool>();
		// 以下信息都可以通过配置文件进行配置，本例配置三个数据源
		datasource_pool_map.put("data source 1", new ConnectionPool(
				new ConnectionPoolConfig("jdbc:mysql://localhost:3306/db1", "user1", "password1")));
		datasource_pool_map.put("data source 2", new ConnectionPool(
				new ConnectionPoolConfig("jdbc:mysql://localhost:3306/db2", "user2", "password2")));
		datasource_pool_map.put("data source 3", new ConnectionPool(
				new ConnectionPoolConfig("jdbc:mysql://localhost:3306/db3", "user3", "password3")));
	}

	public static ConnectionPoolManager getInstance() {
		return INSTANCE;
	}

	/**
	 * 返回指定数据源的一个连接
	 * 
	 * @param dataSource
	 *            数据源名称
	 */
	public Connection getConnetion(String dataSource) {
		ConnectionPool pool = datasource_pool_map.get(dataSource);
		if (pool != null) {
			return pool.getConnection();
		}
		throw new RuntimeException(dataSource + " was not found.");
	}
}
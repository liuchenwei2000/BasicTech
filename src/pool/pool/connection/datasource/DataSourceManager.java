/**
 * 
 */
package pool.connection.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import pool.connection.ConnectionPoolConfig;

/**
 * 数据源管理器
 * <p>
 * 主要应用在系统使用多数据库的场景下，管理各个数据源。
 * <p>
 * 本类对象一般都是单例，运行在JavaEE应用服务器中，统一管理数据源。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-31
 */
public class DataSourceManager {

	// 单例
	private static final DataSourceManager INSTANCE = new DataSourceManager();

	// 数据源名称_数据源_Map
	private Map<String, DataSource> name_ds_map;

	private DataSourceManager() {
		init();
	}

	private void init() {
		initJNDI();
		initMap();
	}

	private void initJNDI() {
		// 通过JNDI服务注册数据源
	}

	private void initMap() {
		name_ds_map = new HashMap<String, DataSource>();
		// 以下信息都可以通过配置文件进行配置，本例配置两个数据源
		name_ds_map.put("data source 1", new PooledDataSource(
				new ConnectionPoolConfig("jdbc:mysql://localhost:3306/db1",
						"user1", "password1")));
		name_ds_map.put("data source 2", new DriverManagerDataSource(
				"jdbc:mysql://localhost:3306/db2", "user2", "password2"));
	}

	public static DataSourceManager getInstance() {
		return INSTANCE;
	}

	/**
	 * 返回指定数据源对象
	 * 
	 * @param dataSource
	 *            数据源名称
	 */
	public DataSource lookup(String datasourceName){
		DataSource dataSource = name_ds_map.get(datasourceName);
		if (dataSource != null) {
			return dataSource;
		}
		throw new RuntimeException(dataSource + " was not found.");
	}
}
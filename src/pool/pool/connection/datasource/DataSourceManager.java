/**
 * 
 */
package pool.connection.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import pool.connection.ConnectionPoolConfig;

/**
 * ����Դ������
 * <p>
 * ��ҪӦ����ϵͳʹ�ö����ݿ�ĳ����£������������Դ��
 * <p>
 * �������һ�㶼�ǵ�����������JavaEEӦ�÷������У�ͳһ��������Դ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-5-31
 */
public class DataSourceManager {

	// ����
	private static final DataSourceManager INSTANCE = new DataSourceManager();

	// ����Դ����_����Դ_Map
	private Map<String, DataSource> name_ds_map;

	private DataSourceManager() {
		init();
	}

	private void init() {
		initJNDI();
		initMap();
	}

	private void initJNDI() {
		// ͨ��JNDI����ע������Դ
	}

	private void initMap() {
		name_ds_map = new HashMap<String, DataSource>();
		// ������Ϣ������ͨ�������ļ��������ã�����������������Դ
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
	 * ����ָ������Դ����
	 * 
	 * @param dataSource
	 *            ����Դ����
	 */
	public DataSource lookup(String datasourceName){
		DataSource dataSource = name_ds_map.get(datasourceName);
		if (dataSource != null) {
			return dataSource;
		}
		throw new RuntimeException(dataSource + " was not found.");
	}
}
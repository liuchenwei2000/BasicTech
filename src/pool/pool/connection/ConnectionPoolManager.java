/**
 * 
 */
package pool.connection;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * ���ݿ����ӳع�����
 * <p>
 * ��ҪӦ����ϵͳʹ�ö����ݿ�ĳ����£�����������ӳء�
 * ����ǵ������ݿ�Ļ�������ʹ�����ݿ����ӳؾ��㹻�ˡ�
 * <p>
 * �������һ�㶼�ǵ�����������JavaEEӦ�÷������У�ͳһ�������ݿ����ӳء�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-5-31
 */
public class ConnectionPoolManager {

	// ����
	private static final ConnectionPoolManager INSTANCE = new ConnectionPoolManager();

	// ����Դ_���ݿ����ӳ�_Map
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
			// ������Ը�����Ҫ������ʹ�õ����������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void initDataSourcePoolMap() {
		datasource_pool_map = new HashMap<String, ConnectionPool>();
		// ������Ϣ������ͨ�������ļ��������ã�����������������Դ
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
	 * ����ָ������Դ��һ������
	 * 
	 * @param dataSource
	 *            ����Դ����
	 */
	public Connection getConnetion(String dataSource) {
		ConnectionPool pool = datasource_pool_map.get(dataSource);
		if (pool != null) {
			return pool.getConnection();
		}
		throw new RuntimeException(dataSource + " was not found.");
	}
}
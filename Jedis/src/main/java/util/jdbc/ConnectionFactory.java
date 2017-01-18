/**
 * 
 */
package util.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接工厂
 *
 * @author 刘晨伟
 * 
 * 创建日期：2013-6-16
 */
public class ConnectionFactory {

	/** 配置文件路径  */
	private static final String CONFIG_FILE = "config/datasource.properties";

	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	static {
		Properties config = load();
		init(config);
	}

	/**
	 * 创建一个数据库连接
	 */
	public static Connection create() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	private static void init(Properties config) {
		driver = config.getProperty("driver");
		url = config.getProperty("url");
		username = config.getProperty("username");
		password = config.getProperty("password");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Properties load() {
		Properties settings = new Properties();
		try {
			FileInputStream in = new FileInputStream(CONFIG_FILE);
			// 加载配置文件信息
			settings.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return settings;
	}
}

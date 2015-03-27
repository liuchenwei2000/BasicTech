/**
 * 
 */
package pool.connection.datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * 应用客户端程序
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-31
 */
public class AppClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 连接池相关对象的初始化动作，都是伴随着系统初始化发生的，而不是等到使用它的时候懒加载
		DataSourceManager.getInstance();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 直接获取数据源对象，由它返回数据库连接
		DataSource ds = DataSourceManager.getInstance().lookup("data source 1");
		Connection con = null;
		try {
			con = ds.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from user");
			System.out.println(rs);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
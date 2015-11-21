/**
 * 
 */
package pool.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		ConnectionPoolManager.getInstance();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Connection con = ConnectionPoolManager.getInstance().getConnetion("data source 1");
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from user");
			System.out.println(rs);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					// 关闭连接，实际是将连接归还给池，但客户端并不知道此实现细节
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

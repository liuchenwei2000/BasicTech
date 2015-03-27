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
 * Ӧ�ÿͻ��˳���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-5-31
 */
public class AppClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ���ӳ���ض���ĳ�ʼ�����������ǰ�����ϵͳ��ʼ�������ģ������ǵȵ�ʹ������ʱ��������
		DataSourceManager.getInstance();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// ֱ�ӻ�ȡ����Դ���������������ݿ�����
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
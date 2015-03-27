/**
 * 
 */
package pool.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
					// �ر����ӣ�ʵ���ǽ����ӹ黹���أ����ͻ��˲���֪����ʵ��ϸ��
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
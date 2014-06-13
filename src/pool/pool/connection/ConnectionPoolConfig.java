/**
 * 
 */
package pool.connection;

/**
 * ���ݿ����ӳ�������Ϣ
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-5-31
 */
public class ConnectionPoolConfig {

	private int initSize = 10;// ��ʼ������
	private int minSize = initSize;// ��С������
	private int maxSize = 100;// ���������
	
	private long timeOut = 10 * 60 * 1000;// ��ʱʱ��(10����)��������Ϊ���ӳ�ʱ 

	private String url;// ���ݿ�url
	private String user;// ���ݿ��½�û�
	private String password;// ���ݿ��½����
	
	public ConnectionPoolConfig(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public int getInitSize() {
		return initSize;
	}

	public void setInitSize(int initSize) {
		this.initSize = initSize;
	}

	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}
}
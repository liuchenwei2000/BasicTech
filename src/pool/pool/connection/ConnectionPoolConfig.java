/**
 * 
 */
package pool.connection;

/**
 * 数据库连接池配置信息
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-31
 */
public class ConnectionPoolConfig {

	private int initSize = 10;// 初始连接数
	private int minSize = initSize;// 最小连接数
	private int maxSize = 100;// 最大连接数
	
	private long timeOut = 10 * 60 * 1000;// 超时时限(10分钟)，超过认为连接超时 

	private String url;// 数据库url
	private String user;// 数据库登陆用户
	private String password;// 数据库登陆密码
	
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

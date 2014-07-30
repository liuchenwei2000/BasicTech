/**
 * 
 */
package demo2;

/**
 * 测试类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月30日
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int size = 5;
		Store store = new Store();
		for (int i = 0; i < size; i++) {
			new Thread(new Producer(store), "Producer" + (i + 1)).start();
			new Thread(new Consumer(store), "Consumer" + (i + 1)).start();
		}
	}
}

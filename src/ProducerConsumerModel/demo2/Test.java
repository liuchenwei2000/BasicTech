/**
 * 
 */
package demo2;

/**
 * ������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��30��
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

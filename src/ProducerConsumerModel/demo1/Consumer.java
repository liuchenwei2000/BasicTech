/**
 * 
 */
package demo1;

/**
 * ������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��30��
 */
public class Consumer implements Runnable {

	private Store store;
	
	public Consumer(Store store) {
		this.store = store;
	}

	public void run() {
		// ��ͣ��ȡ��Ʒ
		while (true) {
			store.take();
			try {
				Thread.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


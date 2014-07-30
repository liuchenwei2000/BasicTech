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
public class Producer implements Runnable {

	private Store store;
	
	public Producer(Store store) {
		this.store = store;
	}

	public void run() {
		while (true) {
			store.offer(new Goods());
			try {
				Thread.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

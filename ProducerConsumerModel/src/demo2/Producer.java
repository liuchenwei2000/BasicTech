/**
 * 
 */
package demo2;

/**
 * 生产者
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月30日
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

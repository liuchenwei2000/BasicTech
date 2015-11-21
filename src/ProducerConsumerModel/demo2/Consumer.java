/**
 * 
 */
package demo2;

/**
 * 消费者
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月30日
 */
public class Consumer implements Runnable {

	private Store store;
	
	public Consumer(Store store) {
		this.store = store;
	}

	public void run() {
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

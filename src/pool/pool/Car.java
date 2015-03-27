/**
 * 
 */
package pool;

/**
 * 轿车
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-28
 */
public class Car implements Vehicle {

	private int id;
	
	public Car(int id) {
		this.id = id;
		try {
			// 线程停1s，代表创建对象是代价高昂的
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void move() {
		System.out.println("car " + id + " is moving");
	}
}
/**
 * 
 */
package pool;

/**
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-28
 */
public class Client {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		try {
			// 系统启动的时候会初始化池VehiclePool
			Class.forName(VehiclePool.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Thread.sleep(1000);
		System.out.println("start!");
		new Thread(new Task(), "Mike").start();
		Thread.sleep(200);
		new Thread(new Task(), "Tom").start();
		Thread.sleep(200);
		new Thread(new Task(), "Peter").start();
		Thread.sleep(200);
		new Thread(new Task(), "Nick").start();
		Thread.sleep(200);
		new Thread(new Task(), "Ann").start();
		Thread.sleep(200);
		new Thread(new Task(), "Tony").start();
		Thread.sleep(200);
		new Thread(new Task(), "Tim").start();
	}

}
class Task implements Runnable {

	@Override
	public void run() {
		Vehicle vehicle = VehiclePool.getVehicle();
		System.out.println("【" +Thread.currentThread().getName() + "】 I want a vehicle.");
		vehicle.move();
		try {
			Thread.sleep(1100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("【" +Thread.currentThread().getName() + "】I give back the vehicle.");
		VehiclePool.giveBack(vehicle);
	}
}
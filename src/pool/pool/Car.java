/**
 * 
 */
package pool;

/**
 * �γ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-5-28
 */
public class Car implements Vehicle {

	private int id;
	
	public Car(int id) {
		this.id = id;
		try {
			// �߳�ͣ1s�������������Ǵ��۸߰���
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
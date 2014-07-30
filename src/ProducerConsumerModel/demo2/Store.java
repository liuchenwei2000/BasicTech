/**
 * 
 */
package demo2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �̵�
 * <p>
 * ����������Ʒ�Ĺ�����ȡ����
 * <p>
 * ���synchronized��ʽ��Lock��ʽ�ܹ���ȫ������������������⡣
 * ���ܱ�֤�����߲����ڴ����ʱ�������ݣ����ܱ�֤�����߲����ڴ����ʱ�������ݡ�
 * ��ȫ����ΪLock������Դ�����ֹһ���������󣬶�synchronizedֻ��һ����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��30��
 */
public class Store {
	
	/** ������� */
	private static final int MAX_SIZE = 5;

	private Lock lock = new ReentrantLock();
	
	// �����
	private Condition fullCondition = lock.newCondition();
	// �����
	private Condition emptyCondition = lock.newCondition();
	
	private List<Goods> goodsList;

	public Store() {
		this.goodsList = new ArrayList<Goods>();
	}
	
	/**
	 * ���
	 */
	public void offer(Goods goods) {
		lock.lock();
		try {
			while (MAX_SIZE == goodsList.size()) {
				System.out.println("Thread " + Thread.currentThread().getName() + " is waiting for offering goods........");
				fullCondition.await();// ������ˣ��������߳���Ҫ�ȴ�
			}
			System.out.println("Thread " + Thread.currentThread().getName() + " offers goods " + goods.getId());
			goodsList.add(goods);
			emptyCondition.signalAll();// ���д���ˣ�֪ͨ�ȴ����������߳�
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * ȡ��
	 */
	public Goods take() {
		Goods goods = null;
		lock.lock();
		try {
			while (goodsList.isEmpty()) {
				try {
					System.out.println("Thread " + Thread.currentThread().getName() + " is waiting for taking goods........");
					emptyCondition.await();// ������ˣ��������߳���Ҫ�ȴ�
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			goods = goodsList.remove(0);
			System.out.println("Thread " + Thread.currentThread().getName() + " takes goods " + goods.getId());
			fullCondition.signalAll();// ������б�ȡ�ߵ��ˣ�֪ͨ�ȴ����������߳�
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return goods;
	}
}

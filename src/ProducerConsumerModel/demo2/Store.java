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
 * 商店
 * <p>
 * 用来管理商品的供货和取货。
 * <p>
 * 相比synchronized方式，Lock方式能够完全解决生产者消费者问题。
 * 既能保证生产者不会在存货满时加入数据，又能保证消费者不会在存货空时消耗数据。
 * 这全都因为Lock对象可以创建不止一个条件对象，而synchronized只有一个。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月30日
 */
public class Store {
	
	/** 最大存货量 */
	private static final int MAX_SIZE = 5;

	private Lock lock = new ReentrantLock();
	
	// 存货满
	private Condition fullCondition = lock.newCondition();
	// 存货空
	private Condition emptyCondition = lock.newCondition();
	
	private List<Goods> goodsList;

	public Store() {
		this.goodsList = new ArrayList<Goods>();
	}
	
	/**
	 * 存货
	 */
	public void offer(Goods goods) {
		lock.lock();
		try {
			while (MAX_SIZE == goodsList.size()) {
				System.out.println("Thread " + Thread.currentThread().getName() + " is waiting for offering goods........");
				fullCondition.await();// 存货满了，生产者线程需要等待
			}
			System.out.println("Thread " + Thread.currentThread().getName() + " offers goods " + goods.getId());
			goodsList.add(goods);
			emptyCondition.signalAll();// 又有存货了，通知等待的消费者线程
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 取货
	 */
	public Goods take() {
		Goods goods = null;
		lock.lock();
		try {
			while (goodsList.isEmpty()) {
				try {
					System.out.println("Thread " + Thread.currentThread().getName() + " is waiting for taking goods........");
					emptyCondition.await();// 存货空了，消费者线程需要等待
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			goods = goodsList.remove(0);
			System.out.println("Thread " + Thread.currentThread().getName() + " takes goods " + goods.getId());
			fullCondition.signalAll();// 存货又有被取走的了，通知等待的生产者线程
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return goods;
	}
}

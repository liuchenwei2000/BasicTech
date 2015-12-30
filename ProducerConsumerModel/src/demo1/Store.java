/**
 * 
 */
package demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * 商店
 * <p>
 * 用来管理商品的供货和取货。
 * <p>
 * 因为 synchronized 方式只能提供一个条件对象，所以无法完全解决生产者消费者问题。
 * 要么只能保证生产者不会在存货满时加入数据，要么只能消费者保证不会在存货空时消耗数据。
 * 本例实现了后者。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月30日
 */
public class Store {

	private List<Goods> goodsList;

	public Store() {
		this.goodsList = new ArrayList<Goods>();
	}
	
	/**
	 * 供货
	 */
	public synchronized void offer(Goods goods){
		System.out.println("Thread " + Thread.currentThread().getName() + " offers goods " + goods.getId());
		goodsList.add(goods);
		notifyAll();// 供货后通知等待的取货线程
	}
	
	/**
	 * 取货
	 */
    public synchronized Goods take() {
    	while(goodsList.isEmpty()){
    		try {
    			System.out.println("Thread " + Thread.currentThread().getName() + " is waiting for taking goods........");
				wait();// 无货的时候，等待供货
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	Goods goods = goodsList.remove(0);
    	System.out.println("Thread " + Thread.currentThread().getName() + " takes goods " + goods.getId());
		return goods;
	}
}

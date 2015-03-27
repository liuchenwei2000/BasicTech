/**
 * 
 */
package demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * �̵�
 * <p>
 * ����������Ʒ�Ĺ�����ȡ����
 * <p>
 * ��Ϊ synchronized ��ʽֻ���ṩһ���������������޷���ȫ������������������⡣
 * Ҫôֻ�ܱ�֤�����߲����ڴ����ʱ�������ݣ�Ҫôֻ�������߱�֤�����ڴ����ʱ�������ݡ�
 * ����ʵ���˺��ߡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��30��
 */
public class Store {

	private List<Goods> goodsList;

	public Store() {
		this.goodsList = new ArrayList<Goods>();
	}
	
	/**
	 * ����
	 */
	public synchronized void offer(Goods goods){
		System.out.println("Thread " + Thread.currentThread().getName() + " offers goods " + goods.getId());
		goodsList.add(goods);
		notifyAll();// ������֪ͨ�ȴ���ȡ���߳�
	}
	
	/**
	 * ȡ��
	 */
    public synchronized Goods take() {
    	while(goodsList.isEmpty()){
    		try {
    			System.out.println("Thread " + Thread.currentThread().getName() + " is waiting for taking goods........");
				wait();// �޻���ʱ�򣬵ȴ�����
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	Goods goods = goodsList.remove(0);
    	System.out.println("Thread " + Thread.currentThread().getName() + " takes goods " + goods.getId());
		return goods;
	}
}

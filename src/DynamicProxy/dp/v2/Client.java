/**
 * 
 */
package dp.v2;

import java.lang.reflect.Proxy;

import dp.FactoryImpl;
import dp.IFactory;
import dp.IService;
import dp.ServiceImpl;

/**
 * ��̬����ͻ���ʹ��ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Proxy ���ṩ���ڴ�����̬�������ʵ���ľ�̬������
		 * <p>
		 * ��̬������������ʱ�������࣬������������ʱ���ʵ��ָ���Ľӿڡ�
		 * Proxy���������þ�̬��Proxy.newProxyInstance()����������ʱ��̬�ش����ġ�
		 * <p>
		 * newProxyInstance ��������һ��ָ���ӿڵĴ�����ʵ�����ýӿڿ��Խ���������ָ�ɵ�ָ���ĵ��ô������(InvocationHandler)��
		 * �ڶ��������������κνӿڣ����������� MyInvocationHandle ���Խ����κνӿڵ�ʵ����
		 */
		// �������� Proxy �� MyInvocationHandler ������ʵ�� IService �ӿڵĴ�����ʵ��
		IService serviceProxy = (IService) Proxy.newProxyInstance(
				IService.class.getClassLoader(),
				new Class[] { IService.class }, new MyInvocationHandler(new ServiceImpl()));
		serviceProxy.operate("Hello");
		System.out.println();
		
		// Ҳ���Խ������������Ĺ��̷�װ��������ʵ��ԭ���������һ���ġ�
		IFactory factoryProxy = getProxy(new FactoryImpl());
		System.out.println(factoryProxy.create());
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T getProxy(T t) {
		return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t
				.getClass().getInterfaces(), new MyInvocationHandler(t));
	}
}

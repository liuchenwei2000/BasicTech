/**
 * 
 */
package v2;

import java.lang.reflect.Proxy;

import common.FactoryImpl;
import common.IFactory;
import common.IService;
import common.ServiceImpl;

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
		// ���������� Proxy �� MyInvocationHandler ������ʵ�� IFactory �ӿڵĴ�����ʵ��
		IFactory factoryProxy = (IFactory) Proxy.newProxyInstance(
				IFactory.class.getClassLoader(),
				new Class[] { IFactory.class }, new MyInvocationHandler(new FactoryImpl()));
		System.out.println(factoryProxy.create());
	}
}

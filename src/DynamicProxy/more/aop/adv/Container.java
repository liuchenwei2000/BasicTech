/**
 * 
 */
package more.aop.adv;

import java.lang.reflect.Proxy;

import dp.FactoryImpl;
import dp.IFactory;
import dp.IService;
import dp.ServiceImpl;

/**
 * ����ģ������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
public class Container {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * ����1��ΪIServiceҵ������ṩ��־���ܺͷ���ִ��ʱ��ͳ�ƹ���
		 */
		// ��Ҫ��Щ���ܾ�ע����Щ������
		InvocationHandlerChain chain = new InvocationHandlerChain(new ServiceImpl());
		chain.register(new LoggingInterceptor());
		chain.register(new TimeInterceptor());
		
		IService serviceProxy = (IService) Proxy.newProxyInstance(
				IService.class.getClassLoader(),
				new Class[] { IService.class }, chain);
		
		// �ͻ�����ServiceClient��ȫ����Ҫ���κ��޸�
		ServiceClient serviceClient = new ServiceClient(serviceProxy);
		serviceClient.doSomething();
		System.out.println();
		
		/*
		 * ����2��ΪIFactoryҵ������ṩ����ִ��ʱ��ͳ�ƹ��ܺ���־����
		 */
		chain = new InvocationHandlerChain(new FactoryImpl());
		chain.register(new TimeInterceptor());
		chain.register(new LoggingInterceptor());
		
		IFactory factoryProxy = (IFactory) Proxy.newProxyInstance(
				IFactory.class.getClassLoader(),
				new Class[] { IFactory.class }, chain);
		
		// �ͻ�����FactoryClient��ȫ����Ҫ���κ��޸�
		FactoryClient factoryClient = new FactoryClient(factoryProxy);
		System.out.println(factoryClient.say());
	}
}

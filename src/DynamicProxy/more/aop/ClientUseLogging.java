/**
 * 
 */
package more.aop;

import java.lang.reflect.Proxy;

import dp.FactoryImpl;
import dp.IFactory;
import dp.IService;
import dp.ServiceImpl;

/**
 * ��־ÿһ��ҵ�����ķ�������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
public class ClientUseLogging {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * ����Ķ���װ����̣�ʵ���Ͽ���ͨ������ע������������� 
		 */
		IService serviceProxy = (IService) Proxy.newProxyInstance(
				IService.class.getClassLoader(),
				new Class[] { IService.class }, new InvocationLoggingHandler(new ServiceImpl()));
		serviceProxy.operate("Hello");
		System.out.println();
		
		IFactory factoryProxy = (IFactory) Proxy.newProxyInstance(
				IFactory.class.getClassLoader(),
				new Class[] { IFactory.class }, new InvocationLoggingHandler(new FactoryImpl()));
		System.out.println(factoryProxy.create());
	}

}

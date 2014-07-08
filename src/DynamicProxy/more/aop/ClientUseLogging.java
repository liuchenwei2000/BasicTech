/**
 * 
 */
package more.aop;

import java.lang.reflect.Proxy;

import dp.FactoryImpl;
import dp.IFactory;
import dp.IService;
import dp.ServiceImpl;
import dp.v2.MyInvocationHandler;

/**
 * ʾ��
 * <p>
 * 
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
		IService serviceProxy = (IService) Proxy.newProxyInstance(
				IService.class.getClassLoader(),
				new Class[] { IService.class }, new InvocationLoggingHandler(new ServiceImpl()));
		serviceProxy.operate("Hello");
		System.out.println();
		// ���������� Proxy �� MyInvocationHandler ������ʵ�� IFactory �ӿڵĴ�����ʵ��
		IFactory factoryProxy = (IFactory) Proxy.newProxyInstance(
				IFactory.class.getClassLoader(),
				new Class[] { IFactory.class }, new InvocationLoggingHandler(new FactoryImpl()));
		System.out.println(factoryProxy.create());
	}

}

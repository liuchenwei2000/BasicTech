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
 * 示例
 * <p>
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
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
		// 这里又利用 Proxy 和 MyInvocationHandler 创建了实现 IFactory 接口的代理类实例
		IFactory factoryProxy = (IFactory) Proxy.newProxyInstance(
				IFactory.class.getClassLoader(),
				new Class[] { IFactory.class }, new InvocationLoggingHandler(new FactoryImpl()));
		System.out.println(factoryProxy.create());
	}

}

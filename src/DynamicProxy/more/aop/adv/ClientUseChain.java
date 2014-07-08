/**
 * 
 */
package more.aop.adv;

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
public class ClientUseChain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InvocationHandlerChain chain = new InvocationHandlerChain(new ServiceImpl());
		chain.register(new LoggingInterceptor());
		chain.register(new TimeInterceptor());
		
		IService serviceProxy = (IService) Proxy.newProxyInstance(
				IService.class.getClassLoader(),
				new Class[] { IService.class }, chain);
		serviceProxy.operate("Hello");
		System.out.println();
		
		
		chain = new InvocationHandlerChain(new FactoryImpl());
		chain.register(new TimeInterceptor());
		chain.register(new LoggingInterceptor());
		IFactory factoryProxy = (IFactory) Proxy.newProxyInstance(
				IFactory.class.getClassLoader(),
				new Class[] { IFactory.class }, chain);
		System.out.println(factoryProxy.create());
	}

}

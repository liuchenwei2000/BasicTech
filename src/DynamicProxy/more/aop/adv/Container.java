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
 * 用来模拟容器
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public class Container {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 场景1：为IService业务对象提供日志功能和方法执行时间统计功能
		 */
		// 需要哪些功能就注册哪些拦截器
		InvocationHandlerChain chain = new InvocationHandlerChain(new ServiceImpl());
		chain.register(new LoggingInterceptor());
		chain.register(new TimeInterceptor());
		
		IService serviceProxy = (IService) Proxy.newProxyInstance(
				IService.class.getClassLoader(),
				new Class[] { IService.class }, chain);
		
		// 客户端类ServiceClient完全不需要有任何修改
		ServiceClient serviceClient = new ServiceClient(serviceProxy);
		serviceClient.doSomething();
		System.out.println();
		
		/*
		 * 场景2：为IFactory业务对象提供方法执行时间统计功能和日志功能
		 */
		chain = new InvocationHandlerChain(new FactoryImpl());
		chain.register(new TimeInterceptor());
		chain.register(new LoggingInterceptor());
		
		IFactory factoryProxy = (IFactory) Proxy.newProxyInstance(
				IFactory.class.getClassLoader(),
				new Class[] { IFactory.class }, chain);
		
		// 客户端类FactoryClient完全不需要有任何修改
		FactoryClient factoryClient = new FactoryClient(factoryProxy);
		System.out.println(factoryClient.say());
	}
}

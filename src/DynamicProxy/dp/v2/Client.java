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
 * 动态代理客户端使用示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Proxy 类提供用于创建动态代理类和实例的静态方法。
		 * <p>
		 * 动态代理类是运行时创建的类，当它被创建的时候会实现指定的接口。
		 * Proxy本身是利用静态的Proxy.newProxyInstance()方法在运行时动态地创建的。
		 * <p>
		 * newProxyInstance 方法返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序(InvocationHandler)。
		 * 第二个参数可以是任何接口，第三个参数 MyInvocationHandle 可以接收任何接口的实例。
		 */
		// 这里利用 Proxy 和 MyInvocationHandler 创建了实现 IService 接口的代理类实例
		IService serviceProxy = (IService) Proxy.newProxyInstance(
				IService.class.getClassLoader(),
				new Class[] { IService.class }, new MyInvocationHandler(new ServiceImpl()));
		serviceProxy.operate("Hello");
		System.out.println();
		
		// 也可以将创建代理对象的过程封装起来，其实现原理和上面是一样的。
		IFactory factoryProxy = getProxy(new FactoryImpl());
		System.out.println(factoryProxy.create());
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T getProxy(T t) {
		return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t
				.getClass().getInterfaces(), new MyInvocationHandler(t));
	}
}

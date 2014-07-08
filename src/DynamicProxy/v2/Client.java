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
		// 这里又利用 Proxy 和 MyInvocationHandler 创建了实现 IFactory 接口的代理类实例
		IFactory factoryProxy = (IFactory) Proxy.newProxyInstance(
				IFactory.class.getClassLoader(),
				new Class[] { IFactory.class }, new MyInvocationHandler(new FactoryImpl()));
		System.out.println(factoryProxy.create());
	}
}

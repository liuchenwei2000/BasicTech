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
 * 日志每一个业务对象的方法调用
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
		/*
		 * 下面的对象装配过程，实际上可以通过依赖注入由容器来完成 
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

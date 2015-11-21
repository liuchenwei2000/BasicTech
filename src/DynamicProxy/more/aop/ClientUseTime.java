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
 * 统计每一个业务对象方法的调用时间
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public class ClientUseTime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IService serviceProxy = (IService) Proxy.newProxyInstance(
				IService.class.getClassLoader(),
				new Class[] { IService.class }, new InvocationTimeHandler(new ServiceImpl()));
		serviceProxy.operate("Hello");
		System.out.println();

		IFactory factoryProxy = (IFactory) Proxy.newProxyInstance(
				IFactory.class.getClassLoader(),
				new Class[] { IFactory.class }, new InvocationTimeHandler(new FactoryImpl()));
		System.out.println(factoryProxy.create());
	}

}

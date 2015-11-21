/**
 * 
 */
package dp.v1;

import dp.IFactory;

/**
 * IFactory实例的代理类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public class FactoryProxy implements IFactory {
	
	private IFactory realFactory;

	public FactoryProxy(IFactory realFactory) {
		this.realFactory = realFactory;
	}

	public String create() {
		System.out.println("enter FactoryProxy.create()");
		String result = realFactory.create();
		System.out.println("leave FactoryProxy.create()");
		return result;
	}
}

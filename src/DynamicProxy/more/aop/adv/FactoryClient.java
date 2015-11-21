/**
 * 
 */
package more.aop.adv;

import dp.IFactory;

/**
 * 使用IFactory的客户端
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月9日
 */
public class FactoryClient {

	private IFactory factory;

	public FactoryClient(IFactory factory) {
		this.factory = factory;
	}

	public String say() {
		return factory.create();
	}
}

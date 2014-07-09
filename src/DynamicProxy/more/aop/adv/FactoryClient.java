/**
 * 
 */
package more.aop.adv;

import dp.IFactory;

/**
 * ʹ��IFactory�Ŀͻ���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��9��
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

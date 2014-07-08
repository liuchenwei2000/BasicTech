/**
 * 
 */
package v1;

import common.IFactory;

/**
 * IFactoryʵ���Ĵ�����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
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

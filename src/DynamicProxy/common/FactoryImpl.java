/**
 * 
 */
package common;

/**
 * �ӿ�2ʵ����ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
public class FactoryImpl implements IFactory {

	public String create() {
		System.out.println("in FactoryImpl.create()");
		return "FactoryImpl";
	}
}

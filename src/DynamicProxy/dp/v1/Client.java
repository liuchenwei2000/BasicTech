/**
 * 
 */
package dp.v1;

import dp.FactoryImpl;
import dp.IFactory;
import dp.IService;
import dp.ServiceImpl;

/**
 * ����ģʽ�ͻ���ʹ��ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IService service = new ServiceProxy(new ServiceImpl());
		service.operate("Hello");
		
		System.out.println();

		IFactory factory = new FactoryProxy(new FactoryImpl());
		System.out.println(factory.create());
	}
}

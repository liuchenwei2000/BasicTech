/**
 * 
 */
package v1;

import common.FactoryImpl;
import common.IFactory;
import common.IService;
import common.ServiceImpl;

/**
 * 代理模式客户端使用示例
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
		IService service = new ServiceProxy(new ServiceImpl());
		service.operate("Hello");
		
		System.out.println();

		IFactory factory = new FactoryProxy(new FactoryImpl());
		System.out.println(factory.create());
	}
}

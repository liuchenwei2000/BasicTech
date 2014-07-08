/**
 * 
 */
package v1;

import common.IService;

/**
 * IService实例的代理类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public class ServiceProxy implements IService {
	
	private IService realService;

	public ServiceProxy(IService realService) {
		this.realService = realService;
	}

	public void operate(String word) {
		System.out.println("enter ServiceProxy.operate");
		realService.operate(word);
		System.out.println("leave ServiceProxy.operate");
	}
}

/**
 * 
 */
package more.aop.adv;

import dp.IService;

/**
 * 使用IService的客户端
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月9日
 */
public class ServiceClient {

	private IService service;

	public ServiceClient(IService service) {
		this.service = service;
	}

	public void doSomething() {
		service.operate("Hello");
	}
}

/**
 * 
 */
package more.aop.adv;

import dp.IService;

/**
 * ʹ��IService�Ŀͻ���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��9��
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

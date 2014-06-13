/**
 * 
 */
package app;

import delegate.ExceptionService;
import exception.AppException;

/**
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2012-11-13
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExceptionService es = new ExceptionService();
		App app = new App();
		try {
			app.doBusiness1();
		} catch (AppException ae) {
			es.handleException(ae);
		}
		try {
			app.doBusiness2();
		} catch (AppException ae) {
			es.handleException(ae);
		}
	}

}

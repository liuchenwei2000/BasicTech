/**
 * 
 */
package handler;

import exception.AppException;

/**
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2012-11-13
 */
public class DAOExceptionHandler implements IExceptionHandler {

	/**
	 * (non-Javadoc)
	 * 
	 * @see handler.IExceptionHandler#handleException(exception.AppException)
	 */
	@Override
	public void handleException(AppException ae) {
		System.out.println("A DAO exception has been handled:");
		System.out.println(ae.getMessage());
	}
}

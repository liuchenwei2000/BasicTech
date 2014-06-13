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
public interface IExceptionHandler {

	public void handleException(AppException ae);
}

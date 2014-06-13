/**
 * 
 */
package delegate;

import handler.ExceptionHandlerChain;
import handler.IExceptionHandler;
import log.Logger;
import exception.AppException;

/**
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2012-11-13
 */
public class ExceptionService {
	
	private ExceptionHandlerChain exceptionHandlerChain;
	
	public ExceptionService() {
		super();
		this.exceptionHandlerChain = new ExceptionHandlerChain();
	}

	public void handleException(AppException ae){
		Logger.getInstance().log(ae.getMessage());
		IExceptionHandler handler = getExceptionHandlerChain().findExceptionHandler(ae.getClass().getName());
		if(handler != null) {
			handler.handleException(ae);
		}
	}

	public ExceptionHandlerChain getExceptionHandlerChain() {
		return exceptionHandlerChain;
	}
}
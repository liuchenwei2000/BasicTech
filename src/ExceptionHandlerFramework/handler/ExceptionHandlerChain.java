/**
 * 
 */
package handler;

import java.util.HashMap;
import java.util.Map;

import exception.BusinessException;
import exception.DAOException;

/**
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2012-11-13
 */
public class ExceptionHandlerChain {

	private Map<String, IExceptionHandler> exceptionClassName_handler_map = new HashMap<String, IExceptionHandler>();

	public ExceptionHandlerChain() {
		initMap();
	}

	private void initMap() {
		addExceptionHandler(BusinessException.class.getName(),
				new BusinessExceptionHandler());
		addExceptionHandler(DAOException.class.getName(),
				new DAOExceptionHandler());
	}

	public void addExceptionHandler(String exceptionClassName,
			IExceptionHandler handler) {
		exceptionClassName_handler_map.put(exceptionClassName, handler);
	}

	public void removeExceptionHandler(String exceptionClassName) {
		exceptionClassName_handler_map.put(exceptionClassName, null);
	}

	public IExceptionHandler findExceptionHandler(String exceptionClassName) {
		return exceptionClassName_handler_map.get(exceptionClassName);
	}
}
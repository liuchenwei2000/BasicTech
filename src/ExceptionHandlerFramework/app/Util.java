/**
 * 
 */
package app;

import exception.BusinessException;

/**
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2012-11-13
 */
public class Util {

	public static void operation(){
		// some code here
		// ...
		throw new BusinessException("in app.Util.operation()"); 
	}
}

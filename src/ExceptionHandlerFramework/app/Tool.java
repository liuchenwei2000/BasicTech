/**
 * 
 */
package app;

import exception.DAOException;

/**
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2012-11-13
 */
public class Tool {

	public void method(){
		// some code here
		// ...
		throw new DAOException("in app.Tool.method()"); 
	}
}

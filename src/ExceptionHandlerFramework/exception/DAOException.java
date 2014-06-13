/**
 * 
 */
package exception;

/**
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2012-11-13
 */
public class DAOException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DAOException() {
	}

	/**
	 * @param message
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

}

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
public class BusinessException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public BusinessException() {
	}

	/**
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

}

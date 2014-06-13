/**
 * 
 */
package log;

import java.text.DateFormat;
import java.util.Date;

/**
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2012-11-13
 */
public final class Logger {

	private static final Logger INSTANCE = new Logger();
	
	private Logger(){
		// do nothing
	}
	
	public void log(String content) {
		System.out.print(getDateTime());
		System.out.println(content);
	}

	private String getDateTime() {
		return "[LOG] [" + DateFormat.getInstance().format(new Date()) + "]：";
	}

	public static Logger getInstance() {
		return INSTANCE;
	}
}
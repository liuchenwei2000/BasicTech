/**
 * 
 */
package more.aop.adv;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志拦截器
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public class LoggingInterceptor implements MethodInterceptor{

	private SimpleDateFormat formatter = new SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss.SSS");

	public void beforeInvoking(Object proxy, Method method, Object[] args) {
		String methodInfo = proxy.getClass().getName() + "." + method.getName();
		System.out.println(formatter.format(new Date()) + " enter "
				+ methodInfo);
	}

	public void afterInvoking(Object proxy, Method method, Object[] args) {
		String methodInfo = proxy.getClass().getName() + "." + method.getName();
		System.out.println(formatter.format(new Date()) + " leave "
				+ methodInfo);
	}
}

/**
 * 
 */
package more.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 提供日志功能的InvocationHandler
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public class InvocationLoggingHandler implements InvocationHandler {

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	
	// 具体实现类实例
	private Object target;
	
	public InvocationLoggingHandler(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String methodInfo = target.getClass().getName() + "." + method.getName();
		System.out.println(formatter.format(new Date()) + " enter " + methodInfo);
		Object result = method.invoke(target, args);
		System.out.println(formatter.format(new Date()) + " leave " + methodInfo);
		return result;
	}
}

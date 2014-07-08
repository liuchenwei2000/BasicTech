/**
 * 
 */
package more.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 与动态代理对应的调用处理器示例
 * <p>
 * 通常会把一个具体实现类的实例传给调用处理器，然后把具体业务的实现委派给这个对象去完成。
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

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String methodInfo = target.getClass().getName() + "." + method.getName();
		System.out.println(formatter.format(new Date()) + " enter " + methodInfo);
		Object result = method.invoke(target, args);
		System.out.println(formatter.format(new Date()) + " leave " + methodInfo);
		return result;
	}
}

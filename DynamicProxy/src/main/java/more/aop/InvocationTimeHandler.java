/**
 * 
 */
package more.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 提供执行时间统计功能的InvocationHandler
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public class InvocationTimeHandler implements InvocationHandler {

	// 具体实现类实例
	private Object target;
	
	public InvocationTimeHandler(Object target) {
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		long start = System.currentTimeMillis();
		Object result = method.invoke(target, args);
		long end = System.currentTimeMillis();
		String methodInfo = target.getClass().getName() + "." + method.getName();
		System.out.println(methodInfo + " total time : " + (end - start)/1000.0 + "s");
		return result;
	}
}

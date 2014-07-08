/**
 * 
 */
package more.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 与动态代理对应的调用处理器示例
 * <p>
 * 通常会把一个具体实现类的实例传给调用处理器，然后把具体业务的实现委派给这个对象去完成。
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

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
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

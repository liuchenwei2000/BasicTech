/**
 * 
 */
package dp.v2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 与动态代理对应的调用处理器示例
 * <p>
 * 通常会把一个具体实现类的实例传给调用处理器，然后把具体业务的实现委派给这个对象去完成。
 * InvocationHandler的工作是响应代理的任何调用，可以把它想成是代理收到方法调用后，请求做实际工作的对象。
 * Java负责创建真实代理类和对象，我们只需提供在方法调用发生时知道做什么的handler。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public class MyInvocationHandler implements InvocationHandler {

	// 具体实现类实例
	private Object target;
	
	public MyInvocationHandler(Object target) {
		this.target = target;
	}

	/**
	 * 需要实现这个方法
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// 方法执行前处理
		String methodInfo = target.getClass().getName() + "." + method.getName();
		System.out.println("enter " + methodInfo);
		// 方法执行委托给具体实例
		Object result = method.invoke(target, args);
		// 方法执行后处理
		System.out.println("leave " + methodInfo);
		return result;
	}
}

/**
 * 
 */
package more.aop.adv;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 与动态代理对应的调用处理器示例
 * <p>
 * 通常会把一个具体实现类的实例传给调用处理器，然后把具体业务的实现委派给这个对象去完成。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public class InvocationHandlerChain implements InvocationHandler {

	private List<MethodInterceptor> interceptors;
	// 具体实现类实例
	private Object target;
	
	public InvocationHandlerChain(Object target) {
		this.target = target;
		this.interceptors = new ArrayList<MethodInterceptor>();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		for(MethodInterceptor interceptor : interceptors){
			interceptor.beforeInvoking(target, method, args);
		}
		Object result = method.invoke(target, args);
		for(MethodInterceptor interceptor : interceptors){
			interceptor.afterInvoking(target, method, args);
		}
		return result;
	}
	
	public void register(MethodInterceptor interceptor){
		interceptors.add(interceptor);
	}
}

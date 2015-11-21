/**
 * 
 */
package more.aop.adv;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器链
 * <p>
 * 把多个拦截器串成一条链，在一个动态代理的InvocationHandler中拦截所有的方法调用，就可以挨个执行链上的拦截器。
 * 这样一来，不管有多少基础设施服务需要提供，都可以应对自如。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public class InvocationHandlerChain implements InvocationHandler {

	// 拦截器列表
	private List<MethodInterceptor> interceptors;
	// 具体实现类实例
	private Object target;
	
	public InvocationHandlerChain(Object target) {
		this.target = target;
		this.interceptors = new ArrayList<MethodInterceptor>();
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		for(MethodInterceptor interceptor : interceptors){
			interceptor.beforeInvoking(target, method, args);
		}
		// 执行方法调用
		Object result = method.invoke(target, args);
		for(MethodInterceptor interceptor : interceptors){
			interceptor.afterInvoking(target, method, args);
		}
		return result;
	}
	
	/**
	 * 注册拦截器
	 */
	public void register(MethodInterceptor interceptor){
		interceptors.add(interceptor);
	}
	
	/**
	 * 移除拦截器
	 */
	public void remove(MethodInterceptor interceptor){
		interceptors.remove(interceptor);
	}
}

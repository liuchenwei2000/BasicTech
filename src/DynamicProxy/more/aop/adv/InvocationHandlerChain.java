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
 * �붯̬�����Ӧ�ĵ��ô�����ʾ��
 * <p>
 * ͨ�����һ������ʵ�����ʵ���������ô�������Ȼ��Ѿ���ҵ���ʵ��ί�ɸ��������ȥ��ɡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
public class InvocationHandlerChain implements InvocationHandler {

	private List<MethodInterceptor> interceptors;
	// ����ʵ����ʵ��
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

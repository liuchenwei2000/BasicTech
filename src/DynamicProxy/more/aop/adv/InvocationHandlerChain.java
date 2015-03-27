/**
 * 
 */
package more.aop.adv;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * ��������
 * <p>
 * �Ѷ������������һ��������һ����̬�����InvocationHandler���������еķ������ã��Ϳ��԰���ִ�����ϵ���������
 * ����һ���������ж��ٻ�����ʩ������Ҫ�ṩ��������Ӧ�����硣
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
public class InvocationHandlerChain implements InvocationHandler {

	// �������б�
	private List<MethodInterceptor> interceptors;
	// ����ʵ����ʵ��
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
		// ִ�з�������
		Object result = method.invoke(target, args);
		for(MethodInterceptor interceptor : interceptors){
			interceptor.afterInvoking(target, method, args);
		}
		return result;
	}
	
	/**
	 * ע��������
	 */
	public void register(MethodInterceptor interceptor){
		interceptors.add(interceptor);
	}
	
	/**
	 * �Ƴ�������
	 */
	public void remove(MethodInterceptor interceptor){
		interceptors.remove(interceptor);
	}
}

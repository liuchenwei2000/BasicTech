/**
 * 
 */
package more.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * �ṩִ��ʱ��ͳ�ƹ��ܵ�InvocationHandler
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
public class InvocationTimeHandler implements InvocationHandler {

	// ����ʵ����ʵ��
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

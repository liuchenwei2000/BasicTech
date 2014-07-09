/**
 * 
 */
package more.aop.adv;

import java.lang.reflect.Method;

/**
 * ����ִ��ʱ��ͳ��������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
public class TimeInterceptor implements MethodInterceptor{
	
	private long start;

	public void beforeInvoking(Object proxy, Method method, Object[] args) {
		this.start = System.currentTimeMillis();
	}

	public void afterInvoking(Object proxy, Method method, Object[] args) {
		long end = System.currentTimeMillis();
		String methodInfo = proxy.getClass().getName() + "." + method.getName();
		System.out.println(methodInfo + " total time : " + (end - start)
				/ 1000.0 + "s");
	}
}

/**
 * 
 */
package more.aop.adv;

import java.lang.reflect.Method;

/**
 * ʾ��
 * <p>
 * 
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
public interface MethodInterceptor {

	public void beforeInvoking(Object target, Method method, Object[] args);
	
	public void afterInvoking(Object target, Method method, Object[] args);
}

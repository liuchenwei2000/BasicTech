/**
 * 
 */
package more.aop.adv;

import java.lang.reflect.Method;

/**
 * 示例
 * <p>
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public interface MethodInterceptor {

	public void beforeInvoking(Object target, Method method, Object[] args);
	
	public void afterInvoking(Object target, Method method, Object[] args);
}

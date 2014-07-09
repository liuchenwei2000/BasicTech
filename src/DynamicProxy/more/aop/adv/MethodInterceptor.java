/**
 * 
 */
package more.aop.adv;

import java.lang.reflect.Method;

/**
 * 方法拦截器
 * <p>
 * 对于每个具有横切性质的问题，用一个拦截器来解决。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public interface MethodInterceptor {

	public void beforeInvoking(Object target, Method method, Object[] args);

	public void afterInvoking(Object target, Method method, Object[] args);
}

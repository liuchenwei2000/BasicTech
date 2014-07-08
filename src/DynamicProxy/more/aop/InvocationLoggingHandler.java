/**
 * 
 */
package more.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * �붯̬�����Ӧ�ĵ��ô�����ʾ��
 * <p>
 * ͨ�����һ������ʵ�����ʵ���������ô�������Ȼ��Ѿ���ҵ���ʵ��ί�ɸ��������ȥ��ɡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
public class InvocationLoggingHandler implements InvocationHandler {

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	// ����ʵ����ʵ��
	private Object target;
	
	public InvocationLoggingHandler(Object target) {
		this.target = target;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		String methodInfo = target.getClass().getName() + "." + method.getName();
		System.out.println(formatter.format(new Date()) + " enter " + methodInfo);
		Object result = method.invoke(target, args);
		System.out.println(formatter.format(new Date()) + " leave " + methodInfo);
		return result;
	}
}

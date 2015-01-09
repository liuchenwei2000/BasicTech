/**
 * 
 */
package dp.v2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * �붯̬�����Ӧ�ĵ��ô�����ʾ��
 * <p>
 * ͨ�����һ������ʵ�����ʵ���������ô�������Ȼ��Ѿ���ҵ���ʵ��ί�ɸ��������ȥ��ɡ�
 * InvocationHandler�Ĺ�������Ӧ������κε��ã����԰�������Ǵ����յ��������ú�������ʵ�ʹ����Ķ���
 * Java���𴴽���ʵ������Ͷ�������ֻ���ṩ�ڷ������÷���ʱ֪����ʲô��handler��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
public class MyInvocationHandler implements InvocationHandler {

	// ����ʵ����ʵ��
	private Object target;
	
	public MyInvocationHandler(Object target) {
		this.target = target;
	}

	/**
	 * ��Ҫʵ���������
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// ����ִ��ǰ����
		String methodInfo = target.getClass().getName() + "." + method.getName();
		System.out.println("enter " + methodInfo);
		// ����ִ��ί�и�����ʵ��
		Object result = method.invoke(target, args);
		// ����ִ�к���
		System.out.println("leave " + methodInfo);
		return result;
	}
}

/**
 * 
 */
package dp;

/**
 * �ӿ�1ʵ����ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
public class ServiceImpl implements IService {

	public void operate(String word) {
		System.out.println("in ServiceImpl.operate :" + word);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

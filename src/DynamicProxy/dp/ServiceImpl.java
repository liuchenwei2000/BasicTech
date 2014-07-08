/**
 * 
 */
package dp;

/**
 * 接口1实现类示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
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

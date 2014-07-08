/**
 * 
 */
package common;

/**
 * 接口2实现类示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public class FactoryImpl implements IFactory {

	public String create() {
		System.out.println("in FactoryImpl.create()");
		return "FactoryImpl";
	}
}

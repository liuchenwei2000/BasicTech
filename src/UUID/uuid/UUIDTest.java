/**
 * 
 */
package uuid;

import java.util.UUID;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.RandomBasedGenerator;

/**
 * UUID生成示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月28日
 */
public class UUIDTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		generateByJDK();
		generateByJUG();
	}
	
	/**
	 * 使用JDK内置的UUID类生成
	 */
	private static void generateByJDK() {
		// 获取 Type4 UUID 的静态工厂：使用加密的强伪随机数生成器生成该 UUID
		String uuid = UUID.randomUUID().toString();// 可用来生成数据库的主键
		printUUID(uuid);
	}
	
	/**
	 * 使用Java UUID Generator(JCG)框架生成
	 * <p>
	 * 纯Java实现，开源LGPL协议，采用了Native的方式产生真正的UUID，而且提供了不同平台的实现。
	 */
	private static void generateByJUG() {
		RandomBasedGenerator generator = Generators.randomBasedGenerator();
		String uuid = generator.generate().toString();
		printUUID(uuid);
	}

	private static void printUUID(String uuid) {
		System.out.println(uuid);
		System.out.println(uuid.toUpperCase());
		System.out.println(uuid.replaceAll("-", "").toUpperCase());
		System.out.println();
	}
}

/**
 * 
 */
package uuid;

import java.util.UUID;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.RandomBasedGenerator;

/**
 * UUID����ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��28��
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
	 * ʹ��JDK���õ�UUID������
	 */
	private static void generateByJDK() {
		// ��ȡ Type4 UUID �ľ�̬������ʹ�ü��ܵ�ǿα��������������ɸ� UUID
		String uuid = UUID.randomUUID().toString();// �������������ݿ������
		printUUID(uuid);
	}
	
	/**
	 * ʹ��Java UUID Generator(JCG)�������
	 * <p>
	 * ��Javaʵ�֣���ԴLGPLЭ�飬������Native�ķ�ʽ����������UUID�������ṩ�˲�ͬƽ̨��ʵ�֡�
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

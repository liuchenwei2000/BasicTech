/**
 * 
 */
package demo1;

/**
 * ��Ʒ
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��30��
 */
public class Goods {

	private static int counter = 1;

	private int id;

	public Goods() {
		this.id = counter++;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "Goods [id=" + id + "]";
	}
}

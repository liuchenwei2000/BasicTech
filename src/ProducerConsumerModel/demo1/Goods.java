/**
 * 
 */
package demo1;

/**
 * 商品
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月30日
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

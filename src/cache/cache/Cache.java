/**
 * 
 */
package cache;

/**
 * ʾ��
 * <p>
 * 
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��13��
 */
public interface Cache<K, V> {

	public void put(K k, V v);

	public V get(K k);
}

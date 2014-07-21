/**
 * 
 */
package cache;

/**
 * 示例
 * <p>
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月13日
 */
public interface Cache<K, V> {

	public void put(K k, V v);

	public V get(K k);
}

/**
 * 
 */
package cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 示例
 * <p>
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月13日
 */
public class LRUCache<K, V> implements Cache<K, V> {

	public static final int SIZE_DEFAULT = 20; 
	
	private List<K> keys;
	private Map<K,V> map;
	
	private int size;
	
	public LRUCache() {
		this(SIZE_DEFAULT);
	}
	
	public LRUCache(int size) {
		this.size = size;
		this.keys = new LinkedList<K>();
		this.map = new HashMap<K, V>();
	}

	public void put(K k, V v) {
		if (map.containsKey(k)) {
			map.put(k, v);
		} else {
			if (isFull()) {
				eliminate();
			}
			keys.add(k);
			map.put(k, v);
		}
	}

	private boolean isFull() {
		return keys.size() == size;
	}

	private void eliminate() {
		K oldest = keys.remove(0);
		map.remove(oldest);
	}
	
	public V get(K k) {
		V v = map.get(k);
		if(v != null) {
			keys.remove(k);
			keys.add(k);
		}
		return v;
	}

}

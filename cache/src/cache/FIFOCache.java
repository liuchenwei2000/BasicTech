/**
 * 
 */
package cache;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 示例
 * <p>
 * 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月13日
 */
public class FIFOCache<K, V> implements Cache<K, V> {

	public static final int SIZE_DEFAULT = 20; 
	
	private Queue<K> keys;
	private Map<K,V> map;
	
	private int size;
	
	public FIFOCache() {
		this(SIZE_DEFAULT);
	}
	
	public FIFOCache(int size) {
		this.size = size;
		this.keys = new PriorityQueue<K>();
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
		K oldest = keys.poll();
		map.remove(oldest);
	}
	
	public V get(K k) {
		return map.get(k);
	}

}

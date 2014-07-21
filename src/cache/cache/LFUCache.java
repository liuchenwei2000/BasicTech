/**
 * 
 */
package cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
public class LFUCache<K, V> implements Cache<K, V> {

	public static final int SIZE_DEFAULT = 20; 
	
	private List<Counter> keys;
	private Map<K,V> map;
	
	private int size;
	
	public LFUCache() {
		this(SIZE_DEFAULT);
	}
	
	public LFUCache(int size) {
		this.size = size;
		this.keys = new ArrayList<Counter>();
		this.map = new HashMap<K, V>();
	}

	public void put(K k, V v) {
		if (map.containsKey(k)) {
			map.put(k, v);
		} else {
			if (isFull()) {
				eliminate();
			}
			keys.add(new Counter(k));
			map.put(k, v);
		}
	}

	private boolean isFull() {
		return keys.size() == size;
	}

	private void eliminate() {
		Counter first = keys.get(0);
		keys.remove(0);
		map.remove(first.getK());
	}
	
	public V get(K k) {
		V v = map.get(k);
		if(v != null) {
			find(k).increase();
			Collections.sort(keys);
		}
		return v;
	}

	private Counter find(K k) {
		for (Counter counter : keys) {
			if(counter.getK().equals(k)){
				return counter;
			}
		}
		return null;
	}

	private class Counter implements Comparable<Counter>{
		
		private K k;
		private int count;
		
		public Counter(K k) {
			this.k = k;
			this.count = 0;
		}
		
		public K getK() {
			return k;
		}
		
		public int getCount() {
			return count;
		}
		
		public void increase(){
			count++;
		}

		public int compareTo(Counter o) {
			return getCount() - o.getCount();
		}
	}
}

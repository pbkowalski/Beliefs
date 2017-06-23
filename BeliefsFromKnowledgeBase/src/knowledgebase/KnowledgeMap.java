package knowledgebase;

import java.util.Map;

public class KnowledgeMap<K,V> {
private Map<K,V> map;


public Map<K,V> getMap() {
	return map;
}

public void setMap(Map<K,V> map) {
	this.map = map;
}

public KnowledgeMap<K,V> add(K key, V value){
	this.map.put(key, value);
	return this;
}

public V get(K key){
	return this.map.get(key);
	
}

}

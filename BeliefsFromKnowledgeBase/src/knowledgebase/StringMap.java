package knowledgebase;

import java.util.Map;

public class StringMap<K> extends KnowledgeMap<K, String> {

	public void add(K property, int i) {
		Map<K, String> map = this.getMap();
		map.put(property, Integer.toString(i));
		setMap(map);		
	}
	public void add(K property, double i) {
		Map<K, String> map = this.getMap();
		map.put(property, Double.toString(i));
		setMap(map);		
	}
}

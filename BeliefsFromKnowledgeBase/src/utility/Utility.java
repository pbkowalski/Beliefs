package utility;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Utility {
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
	    return map.entrySet()
	              .stream()
	              .sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/))
	              .collect(Collectors.toMap(
	                Map.Entry::getKey, 
	                Map.Entry::getValue, 
	                (e1, e2) -> e1, 
	                LinkedHashMap::new
	              ));
	}
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescending(Map<K, V> map) {
	    return map.entrySet()
	              .stream()
	              .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
	              .collect(Collectors.toMap(
	                Map.Entry::getKey, 
	                Map.Entry::getValue, 
	                (e1, e2) -> e1, 
	                LinkedHashMap::new
	              ));
	}
	
	public static Class<?> getStringType(String string){
		//tests for null, integer, float and boolean (in this order) otherwise assumes alphanumeric string
		if (string==null){
			return null;
		}
		try {Integer.parseInt(string);}
		catch(NumberFormatException e){
			try {Double.parseDouble(string);}
				catch(NumberFormatException f){
					if (string.equalsIgnoreCase("true") || string.equalsIgnoreCase("false")){
						return Boolean.class;
					}
					else
						return String.class;
				}
			return Double.class;
		}
		return Integer.class;
	}
}

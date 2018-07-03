package beliefs;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Proposition implements Comparable<Proposition>{

	private final String value;
	
	public Proposition(String value){
		this.value = value;
	}
	public String get() {
		return value;
	}
	
	@Override
	public int compareTo(Proposition arg0) {
		return value.compareTo(arg0.value);
	}	
	@Override
	public boolean equals(Object o) {
	    // self check
	    if (this == o)
	        return true;
	    // null check
	    if (o == null)
	        return false;
	    // type check and cast
	    if (getClass() != o.getClass())
	        return false;
	    Proposition aProp = (Proposition) o;
	    // field comparison
	    return Objects.equals(value, aProp.value);
	}
	
	@Override
	public int hashCode() {
		return this.value.hashCode();
		
	}
	public static HashSet<Proposition> createFocalSet(String[] fs) {
		HashSet<Proposition> focalSet = new HashSet<Proposition>();
		for (String prop: fs) {
			focalSet.add(new Proposition(prop));
		}
		return focalSet;
	}
	public static HashSet<Proposition> createFocalSet(Set<String> fs) {
		HashSet<Proposition> focalSet = new HashSet<Proposition>();
		for (String prop: fs) {
			focalSet.add(new Proposition(prop));
		}
		return focalSet;
	}
	public HashSet<Proposition> createFocalSet(){
		HashSet<Proposition> focalSet = new HashSet<Proposition>();
		focalSet.add(this);
		return focalSet;

	}
}

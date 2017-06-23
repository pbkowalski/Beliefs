package knowledgebase.proposition;

public class PropositionProperty<T> {
	private String property;
	private T t; //testing this approach - this just has to 'hold' a class
	
/*	public PropositionProperty(String property){
		this.property = property;
	}*/
	
	public PropositionProperty(String property, T example){
		this.property = property;
		this.t = example;
	}
	public String getProperty() {
		return property;
	}
	
	public Class<? extends Object> getType() {
		return t.getClass();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj){
	    if (obj == null) {
	        return false;
	    }
	    if (!PropositionProperty.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    PropositionProperty aProp = (PropositionProperty) obj;
		return this.property.equalsIgnoreCase(aProp.property);
	}
	@Override
	public int hashCode() {
	    int hash = 3;
	    hash = 53 * hash + (this.property != null ? this.property.hashCode() : 0);
	    return hash;
	}
}

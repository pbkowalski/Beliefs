package knowledgebase.evidencepropertyfunction;

import knowledgebase.proposition.Proposition;

public abstract class EvidencePropertyFunction {
	public enum Type{
		FUZZY, DIRECT, CONDITIONAL, BOOLEAN
	}
	Type type;
	
	public Type getType(){
		return this.type;
	}
	
	public double getSuspectValue(Proposition suspect){
		System.out.println("dis broke");

		return -1;
	}

}

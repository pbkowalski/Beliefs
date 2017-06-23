package knowledgebase.evidencepropertyfunction;


import java.util.List;


import knowledgebase.evidencepropertyfunction.LookupKey;
import knowledgebase.proposition.Proposition;
import knowledgebase.proposition.PropositionProperty;

public class EPFLookup extends EvidencePropertyFunctionFuzzy{
	LookupTable lookupTable;
	public EPFLookup(List<PropositionProperty<?>> parameterList, LookupTable lookupTable){
		this.parameters = parameterList;
		this.lookupTable = lookupTable;
		this.type = Type.FUZZY;

	}

	@Override
	public double getSuspectValue(Proposition suspect) {
    	LookupKey lookupKey = new LookupKey();
    	
		for (PropositionProperty<?> property : parameters){
			lookupKey.put(property.getProperty(),  suspect.get(property));
		}
/*		if(this.lookupTable.containsKey(lookupKey)){
			System.out.println("TRUE");
		}
		else
			System.out.println("FALSE");
*/
		return this.lookupTable.get(lookupKey);
	}
	
	
}

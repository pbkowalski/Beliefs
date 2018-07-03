package knowledgebase.evidencepropertyfunction;

import java.util.List;

import knowledgebase.proposition.Proposition;
import knowledgebase.proposition.PropositionProperty;

public class EPFFuzzyDirect extends EvidencePropertyFunctionFuzzy{
	PropositionProperty<?> parameter;
	public EPFFuzzyDirect(List<PropositionProperty<?>> parameterList){
		if (parameterList.size()!=1){
			throw new RuntimeException("Direct fuzzy evidence property function must take exactly one parameter");
		}
		if(!parameterList.get(0).getType().isAssignableFrom(new Double(0.0).getClass())){
			throw new RuntimeException("Direct fuzzy evidence property function must parameter of type double");
		}
		this.parameter=parameterList.get(0);
		this.type = Type.FUZZY;
	}
	
	public EPFFuzzyDirect(PropositionProperty<?> parameter){
		Class<?> test = parameter.getType();
		if(!(test.isAssignableFrom(new Double(0.0).getClass()))){
			throw new RuntimeException("Direct fuzzy evidence property function must have parameter of type double");
		}
		this.parameter=parameter;
		this.type = Type.FUZZY;
	}
	@Override
	public double getSuspectValue(Proposition suspect) {
		//can be integers or doubles
		//check for integer first (can't cast directly)
		if (suspect.get(parameter).getClass().isInstance(new Integer(1))){
			return ((Integer) suspect.get(parameter)).doubleValue();
		}
		return (double)suspect.get(parameter);
	}

}

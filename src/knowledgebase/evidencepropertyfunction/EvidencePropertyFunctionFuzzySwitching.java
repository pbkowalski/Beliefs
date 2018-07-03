package knowledgebase.evidencepropertyfunction;

import java.util.List;

import knowledgebase.evidencepropertyfunction.EvidencePropertyFunction.Type;
import knowledgebase.proposition.Proposition;
import knowledgebase.proposition.PropositionProperty;

public class EvidencePropertyFunctionFuzzySwitching extends EvidencePropertyFunctionFuzzy {
	private List<EvidencePropertyFunction> orderedFunctionList;
	private List<String> switchingList;
	public EvidencePropertyFunctionFuzzySwitching(List<EvidencePropertyFunction> orderedFunctionList, List<PropositionProperty<?>> parameterList, List<String> switchList){
		//very ugly solution as it would be cleaner to somehow use an enum, but I'm not sure as to how to do it
		this.type = Type.FUZZY;
		this.parameters = parameterList;
		this.orderedFunctionList = orderedFunctionList;
		this.switchingList = switchList;
	}
	
	//First parameter in the parameter list is switching statement
	public double getSuspectValue(Proposition suspect){
		PropositionProperty<?> switcher = parameters.get(0);
		if (this.switchingList.contains(suspect.get(switcher))){
			int functionIndex = switchingList.indexOf(suspect.get(switcher));
			EvidencePropertyFunction functionToCall = orderedFunctionList.get(functionIndex);
			return functionToCall.getSuspectValue(suspect);
		}
		else
			System.out.println("error");
			return -1;

	}
}

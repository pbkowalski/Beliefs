package knowledgebase.evidencepropertyfunction;

import java.util.Collections;
import java.util.List;

import knowledgebase.proposition.Proposition;
import knowledgebase.proposition.PropositionProperty;

public class EPFBoolean extends EvidencePropertyFunction {
	List<PropositionProperty<?>> parameters;
	List<PropositionProperty<?>> notparameters;

	String keyword;
	public EPFBoolean(List <PropositionProperty<?>> propertyList){
		this.type = Type.BOOLEAN;
		this.parameters = propertyList;
		this.keyword = "and";
	}
	public EPFBoolean(List <PropositionProperty<?>> propertyList, String param){
		this.type = Type.BOOLEAN;
		this.parameters = propertyList;
		switch(param){
		case "or":
			this.keyword="or";
			break;
		case "and":
			this.keyword = "and";
			break;
		case "not":
			this.parameters = Collections.emptyList();
			this.notparameters=propertyList;
			break;
		}
	}
	public EPFBoolean(List <PropositionProperty<?>> propertyList, List <PropositionProperty<?>> notPropertyList, String param){
		this.type = Type.BOOLEAN;
		this.parameters = propertyList;
		this.notparameters = notPropertyList;
		switch(param){
		case "or":
			this.keyword="or";
			break;
		case "and":
			this.keyword = "and";
			break;
		}
	}	
	public EPFBoolean(List <PropositionProperty<?>> propertyList, String param1, String param2){
		this.type = Type.BOOLEAN;
		if (param2=="not"){
			this.notparameters = propertyList;
			this.parameters = Collections.emptyList();
		}
		else{
			this.parameters = propertyList;
			this.notparameters = Collections.emptyList();

		}
		if (keyword == "or"){
			this.keyword="or";
		}
		else{
		this.keyword = "and";
		}
	}
	@Override
	public double getSuspectValue(Proposition suspect){
		Boolean  accumulator;
		if (this.keyword.equals("and")){
			accumulator = true;
			for (PropositionProperty<?> property : this.parameters){
				accumulator = accumulator && suspect.getBooleanProperty(property);
			}
			for (PropositionProperty<?> property : this.notparameters){
				accumulator = accumulator &! suspect.getBooleanProperty(property);
			}
		}
		else{
			accumulator = false;
			for (PropositionProperty<?> property : this.parameters){
				accumulator = accumulator || suspect.getBooleanProperty(property);
			}
			for (PropositionProperty<?> property : this.notparameters){
				accumulator = accumulator |! suspect.getBooleanProperty(property);
			}
		}
		if(accumulator.compareTo(true) == 0)
			return 1;
		else
			return 0;
	}

}

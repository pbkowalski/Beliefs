package knowledgebase.evidencepropertyfunction;

import java.util.List;

import knowledgebase.proposition.Proposition;
import knowledgebase.proposition.PropositionProperty;

public abstract class EvidencePropertyFunctionFuzzy extends EvidencePropertyFunction {
	List<PropositionProperty<?>> parameters;
	
	@Override
	public abstract double getSuspectValue(Proposition suspect);
	
}

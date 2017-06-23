package knowledgebase.evidencepropertyfunction;

import java.util.ArrayList;

import knowledgebase.proposition.Proposition;
import knowledgebase.proposition.PropositionProperty;

public class EvidencePropertyFunctionFuzzyThreshold extends EvidencePropertyFunctionFuzzy {
	private double dev, min;
	public EvidencePropertyFunctionFuzzyThreshold(double threshold, double deviation, PropositionProperty<?> t){
		this.type = Type.FUZZY;
		this.dev = deviation;
		this.min = threshold;
		this.parameters = new ArrayList<PropositionProperty<?>>(1);
		parameters.add(t);
	}
	@Override
	public double getSuspectValue(Proposition suspect){
		return calculateMembershipDegree(suspect);
	}
	public double calculateMembershipDegree(Proposition suspect){
		//for this threshold rule the only SuspectProperty passed is 't'
		double t = ((Number) suspect.getMap().get(parameters.get(0))).doubleValue();
		//obtain t from suspect
		if (t<=min){
			return 0;
		}
		else if (t>=min + dev){
			return 1;}
		else{
			return ((t - min)/dev);
		}
			
		

	}
}

package knowledgebase.evidencepropertyfunction;

import java.util.ArrayList;

import knowledgebase.proposition.Proposition;
import knowledgebase.proposition.PropositionProperty;

public class EvidencePropertyFunctionFuzzyTriangular extends EvidencePropertyFunctionFuzzy {
	private double peak, leftW, rightW;
	public EvidencePropertyFunctionFuzzyTriangular(double peak, double leftWidth, double rightWidth, PropositionProperty<?> t){
		this.type = Type.FUZZY;
		this.peak = peak;
		this.leftW = leftWidth;
		this.rightW = rightWidth;
		this.parameters = new ArrayList<PropositionProperty<?>>(1);
		parameters.add(t);
	}
	@Override
	public double getSuspectValue(Proposition suspect){
		return calculateMembershipDegree(suspect);
	}
	public double calculateMembershipDegree(Proposition suspect){
		//for this triangular rule the only SuspectProperty passed is 't'
		double t = Double.parseDouble((String) suspect.getMap().get(parameters.get(0)));
		//peak, leftW, rightW defined by the fucntion 
		//obtain t from suspect
		
		if ( leftW<= t && t<=peak ){
			//left of peak
			return (1-(peak-t)/leftW);
		}
		else if ( peak<= t && t<= (peak + rightW) ){
			//right of peak
			return (1-(peak-t)/rightW);
		}
		else
			return 0;
	}
}

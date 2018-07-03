package executables;

import RefereeToolbox.RFDempster_Powerset;
import beliefs.FrameOfDiscernment;
import beliefs.MassAssignment;
import beliefs.RandomSet;

public class murderCaseFusion {

	public static void main(String[] args) throws Exception {  		  
  
		  FrameOfDiscernment frameOfDisc1 = new FrameOfDiscernment(5);
		  
		  double[] probabilities1 = new double[32];
		  probabilities1[1]=1;
		  MassAssignment mass1 = new MassAssignment(probabilities1, frameOfDisc1);
		  mass1.setConfidence(0.3);
		  
		  double[] probabilities2 = new double[32];
		  probabilities2[4]=0.45;
		  probabilities2[6]=0.25;
		  probabilities2[7]=0.3;
		  MassAssignment mass2 = new MassAssignment(probabilities2, frameOfDisc1);
		  mass2.setConfidence(0.7);
		  
	  
		  double[] probabilities3 = new double[32];
		  probabilities3[8]=0.2;
		  probabilities3[24]=0.1;
		  probabilities3[25]=0.4;
		  probabilities3[31]=0.3;
		  MassAssignment mass4 = new MassAssignment(probabilities3, frameOfDisc1);
		  mass4.setConfidence(0.8);
		  
		  RandomSet randomSet1 = new RandomSet(frameOfDisc1, mass1);
		  RandomSet randomSet2 = new RandomSet(frameOfDisc1, mass2);
		  RandomSet randomSet4 = new RandomSet(frameOfDisc1, mass4);

      RFDempster_Powerset RFDempster = new RFDempster_Powerset();
      RandomSet[] rs = {randomSet2, randomSet4};
      RandomSet fusionResult2 = randomSet1.fuseRS(rs, RFDempster);
      fusionResult2.getMassAssignment().print(1);
      System.out.println("---");
      fusionResult2.plausibilityMass(new String[]{"A"}).getMassAssignment().print(1);;
      System.out.print(fusionResult2.getFuser().state(1));
	}
}

package executables;

import beliefs.RandomSet;
import beliefs.FrameOfDiscernment;
import beliefs.MassAssignment;



import RefereeToolbox.RFDempster_Powerset;


public class marineVesselFusion {

	public static void main(String[] args) throws Exception {  		  
		  
		  String[] set1 = {"fS", "cGS", "sPS", "caS", "crS", "nS", "lPS"};
		  FrameOfDiscernment frame1 = new FrameOfDiscernment(set1);
		  String[][] keys = {{"fS", "cGS", "sPS", "crS", "lPS"},
				  {"fS", "cGS", "sPS"}
		  };
		  double[] probs = {0.2, 0.8};
		  String[][] keys2 = {{"fS", "cGS", "sPS", "caS", "nS", "crS", "lPS"},
				  {"fS", "cGS", "sPS", "crS", "lPS"},
				  {"cGS", "crS", "lPS"},
				  {"cGS", "lPS"},
		  };
		  double[] probs2 = {0.22, 0.11, 0.44, 0.22};
		  
	      RFDempster_Powerset referee1 = new RFDempster_Powerset();
		  MassAssignment testMass = new MassAssignment(keys, probs, 1);
		  MassAssignment testMass2 = new MassAssignment(keys2, probs2, 1);

		  RandomSet randomSet1 = new RandomSet(frame1, testMass);
		  System.out.print(randomSet1.getFuser().state(1));
		  RandomSet randomSet2 = new RandomSet(frame1, testMass2);
		  System.out.print(randomSet2.getFuser().state(1));
	      
		  System.out.println("Fusion of 2 random sets");

	      RandomSet fusionResult = randomSet1.fuseRS(randomSet2, referee1);
	      fusionResult = fusionResult.fuseRS(randomSet2, referee1);
		  fusionResult.getMassAssignment().print(0);
		  //Make decision
		  
		  //Find distances
		  RandomSet plausConditionedResult = fusionResult.plausibilityMass(new String[]{"cGS"});


		  double dist1 = randomSet1.findDistance(plausConditionedResult);
		  double dist2 = randomSet2.findDistance(plausConditionedResult);
		  RandomSet plausConditionedResult2 = fusionResult.plausibilityMass(new String[]{"lPS"});
		  double dist3 = randomSet1.findDistance(plausConditionedResult2);
		  double dist4 = randomSet2.findDistance(plausConditionedResult2);
		  System.out.println("Distances to m cond on cGS");
		  System.out.print(dist1);System.out.print(",");System.out.print(dist2);System.out.print("\n");
		  System.out.println("Distances to m cond on lPS");
		  System.out.print(dist3);System.out.print(",");System.out.print(dist4);

	    //  System.out.print(fusionResult.getFuser().state(1));
	      
	 //     RFDisjunctive_Powerset referee2 = new RFDisjunctive_Powerset();
	 //     RFDuboisPrade_Powerset referee3 = new RFDuboisPrade_Powerset();
	//      RFPCRSharp_Powerset referee5  =new RFPCRSharp_Powerset();
	        


	  }
}

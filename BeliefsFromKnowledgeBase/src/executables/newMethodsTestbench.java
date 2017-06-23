package executables;

import beliefs.RandomSet;
import beliefs.FrameOfDiscernment;
import beliefs.MassAssignment;



import RefereeToolbox.RFDempster_Powerset;


public class newMethodsTestbench {

	public static void main(String[] args) throws Exception {  		  
		  
		  String[] set1 = {"Ann", "Joe", "Mary"};
		  String[] set2 = {"Dave", "Brian"};
		  FrameOfDiscernment frame1 = new FrameOfDiscernment(set1);
		  FrameOfDiscernment frame2 = new FrameOfDiscernment(set2);
		  String[][] keys = {{"Ann"},{"Joe"},{"Mary","Joe"},{"Mary"}};
		  double[] probs = {0.25, 0.25, 0.4, 0.1};
		  String[][] keys2 = {{"Dave"},{"Brian"},{"Dave","Brian"}};
		  double[] probs2 = {0.1, 0.1, 0.8};
		  
		  String[][] keys3 = {{"Dave"},{"Brian"},{"Ann"}};
		  double[] probs3 = {0.15, 0.15, 0.7};
		  MassAssignment testMass = new MassAssignment(keys, probs, 0.7);
		  MassAssignment testMass2 = new MassAssignment(keys2, probs2, 0.7);
		  MassAssignment testMass3 = new MassAssignment(keys3, probs3, 0.5);

		  RandomSet randomSet1 = new RandomSet(frame1, testMass);
		  System.out.print(randomSet1.getFuser().state(1));
		  RandomSet randomSet2 = new RandomSet(frame2, testMass2);
		  System.out.print(randomSet2.getFuser().state(1));
		  RandomSet randomSet3 = new RandomSet(testMass3);
		  System.out.print(randomSet3.getFuser().state(1));
	      RFDempster_Powerset referee1 = new RFDempster_Powerset();
	      
		  System.out.println("Fusion of 2 random sets");

	      RandomSet fusionResult = randomSet1.fuseRS(randomSet2, referee1);
	      fusionResult = fusionResult.fuseRS(randomSet2, referee1);
		  
	      System.out.print(fusionResult.getFuser().state(1));
	      
	      System.out.println("Fusion of 3 (or more) random sets");
	      RandomSet[] rs = {randomSet2, randomSet3};
	      RandomSet fusionResult2 = randomSet1.fuseRS(rs, referee1);
	      System.out.print(fusionResult2.getFuser().state(1));
	 //     RFDisjunctive_Powerset referee2 = new RFDisjunctive_Powerset();
	 //     RFDuboisPrade_Powerset referee3 = new RFDuboisPrade_Powerset();
	//      RFPCRSharp_Powerset referee5  =new RFPCRSharp_Powerset();
	        


	  }
}

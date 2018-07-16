package executables;

import beliefs.RandomSet;
import beliefs.FrameOfDiscernment;
import beliefs.MassAssignment;

import java.util.Arrays;

import RefereeToolbox.RFDempster_Powerset;


public class FUSION2018_Example {

	public static void main(String[] args) throws Exception {  		  
		double[] probs1 =  {0, 0.7, 0, 0, 0, 0, 0,  0.3};
		double[] probs2 =  {0, 0, 0.25, 0.2, 0.2, 0, 0.1,  0.25};
	//	double[] probs2 =  {0, 0, 0.25, 0.2, 0.15, 0, 0.2,  0.2};

		String[] set = {"A", "B", "C"};
		FrameOfDiscernment frame = new FrameOfDiscernment(set);
		
		  MassAssignment testMass1 = new MassAssignment(probs1, frame);
		  MassAssignment testMass2 = new MassAssignment(probs2, frame);
		  System.out.println("Mass 1:");

		  RandomSet randomSet1 = new RandomSet(frame, testMass1);
		  randomSet1.getMassAssignment().print(1);
		  System.out.println("\nMass 2:");

		  RandomSet randomSet2 = new RandomSet(frame, testMass2);
		  randomSet2.getMassAssignment().print(1);

	      RFDempster_Powerset referee1 = new RFDempster_Powerset();
	      
		  System.out.println("\nFusion result:");

	      RandomSet fusionResult = randomSet1.fuseRS(randomSet2, referee1);
	      fusionResult = fusionResult.fuseRS(randomSet2, referee1);
		  fusionResult.getMassAssignment().print(1);
		  
	      String[] decision = fusionResult.makeDecisionDistances(true);
	      
		  
	      System.out.println("\nDistances from fusion result to singletons");
	      for (String focus: fusionResult.getFrameOfDiscernment().getElements()){
				RandomSet foo = new RandomSet(focus);
				foo.extendRandomSet(fusionResult.getFrameOfDiscernment());
				double d = fusionResult.findDistance(foo);
				System.out.print(focus);System.out.print(" : ");System.out.print(d);System.out.print("\n");
	      }
	      System.out.print("Decision: ");System.out.print(Arrays.toString(decision));
	      System.out.println("\n\nGoodness of decision for singletons:");
	      for (String focus: fusionResult.getFrameOfDiscernment().getElements()){
	    	  double g = fusionResult.goodnessOfDecision(new String[] {focus});
	    	  System.out.print(focus);System.out.print(" : ");System.out.print(g);System.out.print("\n");	    	  
	      }
	      
	      System.out.println("\nContribution degrees of mass1:");
	      for (String focus: fusionResult.getFrameOfDiscernment().getElements()){
	    	  RandomSet plausRes = fusionResult.plausibilityMass(focus);
	    	  double c = 1 - randomSet1.findDistance(plausRes);
	    	  System.out.print(focus);System.out.print(" : ");System.out.print(c);System.out.print("\n");	    	  
	      }
	      System.out.println("\nContribution degrees of mass2:");
	      for (String focus: fusionResult.getFrameOfDiscernment().getElements()){
	    	  RandomSet plausRes = fusionResult.plausibilityMass(focus);
	    	  double c = 1 - randomSet2.findDistance(plausRes);
	    	  System.out.print(focus);System.out.print(" : ");System.out.print(c);System.out.print("\n");	    	  
	      }


	 //     RFDisjunctive_Powerset referee2 = new RFDisjunctive_Powerset();
	 //     RFDuboisPrade_Powerset referee3 = new RFDuboisPrade_Powerset();
	//      RFPCRSharp_Powerset referee5  =new RFPCRSharp_Powerset();
	        


	  }
}

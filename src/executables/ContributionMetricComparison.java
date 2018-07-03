package executables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import RefereeToolbox.RFDempster_Powerset;
import RefereeToolbox.RFDisjunctive_Powerset;
import RefereeToolbox.RFDuboisPrade_Powerset;
import RefereeToolbox.RFPCR6_Powerset;
import RefereeToolbox.RefereeFunctionDempster;
import beliefs.FrameOfDiscernment;
import beliefs.MassAssignment;
import beliefs.RandomSet;
import utility.CSVUtil;

public class ContributionMetricComparison {

	public static void main(String[] args) throws Exception {  	
	/*Initialization*/
		int monteCarloReps = 1000;
	  String[] set1 = {"A", "B", "C", "D", "E", "F", "G"};
	  FrameOfDiscernment frame = new FrameOfDiscernment(set1);
	  RandomSet fusionResult;
	  //manually constructed mass assignment
	  Set<String> singletonSet = new HashSet<String>();
	  //RandomSet randomSet1 = new RandomSet(frame, new MassAssignment(singletonSet));
	  Random rngen = new Random(2000);
	  Random rngen2 = new Random(2001);

	  RandomSet randomSet1, randomSet2, randomSet3, randomSet4;
	  double testcounter=0;
	  for(int i=0; i< monteCarloReps; i++){
	//	  fusionResult = new RandomSet(frame, new MassAssignment(new TreeSet<String>(Arrays.asList(set1)))); // vacuous mass function
		  List<RandomSet> rsList = new ArrayList<RandomSet>();
		  
		  //number of unbiased randomly generated BBAs
		  int numberOfBbas = 6;
		  for (int j=0; j<numberOfBbas; j++){
			  rsList.add(new RandomSet(frame, new MassAssignment(frame,rngen, 8)));
		  }
		  
		  //Selection of fusion method
		  
	      RFPCR6_Powerset referee1 = new RFPCR6_Powerset();

	//	  RFDempster_Powerset referee1 = new RFDempster_Powerset();
	      
	      
	      
	      fusionResult = RandomSet.fuseRSList(rsList, referee1);
	//      RandomSet store = fusionResult;
	 //     System.out.println(fusionResult.getFuser().state(1));
	//      System.out.print(fusionResult.getFuser().state(1));
	      String[] decision = fusionResult.makeDecisionDistances();
	      
	      
	      
	 //     System.out.print("Decision:  ");System.out.print(decision);System.out.print("\n");

	      //find the key contributor
	      RandomSet plausConditionedResult = fusionResult.plausibilityMass(decision);
	//      System.out.println("BBA conditioned on decision:");
    //	  System.out.println(plausConditionedResult.getFuser().state(1));

		  List<Double> distances = new ArrayList<Double>();
	//	  System.out.print("Contributions, qods: \n");
		  for (RandomSet rs1: rsList){
			  distances.add(rs1.findDistance(plausConditionedResult));
		//	  System.out.println(1- rs1.findDistance(plausConditionedResult));
		//	  System.out.println(rs1.goodnessOfDecision(decision));
		  }
		  int index = distances.indexOf(Collections.min(distances));
	 //     System.out.print("Key contributor: rs ");System.out.print(index+1);System.out.print("\n");
		  //compute decision quality
		  double quality1 = fusionResult.goodnessOfDecision(decision);
	 //     System.out.print("Decision quality: ");System.out.print(quality1);System.out.print("\n");

		  
		  
		  // remove key contributor
		//  rsList.remove(index); 
		  /*
		   * for comparison try removing a random element instead - one of these lines needs to be commented out
		   */
		  rsList.remove(rngen2.nextInt(rsList.size()));
		  
		  /*
		   * TRY: for each element remove it, compute contribution, see the change in quality, place back
		   */
		  //recompute fusion
	      fusionResult = RandomSet.fuseRSList(rsList, referee1);
	//      System.out.println(fusionResult.getFuser().state(1));

	      //recompute the quality of decision as if the same decision had been made without the key contributor
		  double quality2 = fusionResult.goodnessOfDecision(decision);
	//	  System.out.print("Decision quality (new): ");System.out.print(quality2);System.out.print("\n");
		  //ideally a drop in quality can be seen
		//  System.out.println(quality2 - quality1);
		  
	      if (quality2< quality1){
	    	  testcounter++;
	      }
	      
		  
	 //     System.out.print(decision); System.out.print("Key contributor: random set "); System.out.print(index+1);System.out.print(" Decision quality: ");  System.out.print(fusionResult.goodnessOfDecision(decision));System.out.print("\n");
	  }
	  System.out.println("Number of times quality2 - quality1: ");
      System.out.print(testcounter);

	}
}

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
import beliefs.FrameOfDiscernment;
import beliefs.MassAssignment;
import beliefs.RandomSet;
import utility.CSVUtil;

public class goodnessDecisionPlayground {

	public static void main(String[] args) throws Exception {  	
	/*Initialization*/
		int monteCarloReps = 1;
	  String[] set1 = {"A", "B", "C"};
	  FrameOfDiscernment frame = new FrameOfDiscernment(set1);
	  RandomSet fusionResult;
	  //manually constructed mass assignment
	  Set<String> singletonSet = new HashSet<String>();
	  //RandomSet randomSet1 = new RandomSet(frame, new MassAssignment(singletonSet));
	  Random rngen = new Random(1338);
	  RandomSet randomSet1, randomSet2, randomSet3, randomSet4;
	  double testcounter=0;
	  for(int i=0; i< monteCarloReps; i++){
		  fusionResult = new RandomSet(frame, new MassAssignment(new TreeSet<String>(Arrays.asList(set1)))); // vacuous mass function
		  List<RandomSet> rsList = new ArrayList<RandomSet>();
		  int numberOfBbas = 2;
		  for (int j=0; j<numberOfBbas; j++){
			  rsList.add(new RandomSet(frame, new MassAssignment(frame,rngen)));
		  }
		 // rsList.add(randomSet1);rsList.add(randomSet2);rsList.add(randomSet3);rsList.add(randomSet4);
	      RFDempster_Powerset referee1 = new RFDempster_Powerset();
	      for (RandomSet rs: rsList){
	    	  fusionResult = fusionResult.fuseRS(rs, referee1);
	    	  System.out.println(rs.getFuser().state(1));
	      }
	      System.out.println(fusionResult.getFuser().state(1));
	//      System.out.print(fusionResult.getFuser().state(1));
	      String[] decision = fusionResult.makeDecisionDistances();
	      
	      System.out.print("Decision:  ");System.out.print(decision);System.out.print("\n");

	      //find the key contributor
	      RandomSet plausConditionedResult = fusionResult.plausibilityMass(decision);
	      System.out.println("BBA conditioned on decision:");
    	  System.out.println(plausConditionedResult.getFuser().state(1));

		  List<Double> distances = new ArrayList<Double>();
		  System.out.print("Contributions, qods: \n");
		  for (RandomSet rs1: rsList){
			  distances.add(rs1.findDistance(plausConditionedResult));
			  System.out.println(1- rs1.findDistance(plausConditionedResult));
			  System.out.println(rs1.goodnessOfDecision(decision));

		  }
		  int index = distances.indexOf(Collections.min(distances));
	      System.out.print("Key contributor: rs ");System.out.print(index+1);System.out.print("\n");

		  //compute decision quality
		  double quality1 = fusionResult.goodnessOfDecision(decision);
	      System.out.print("Decision quality: ");System.out.print(quality1);System.out.print("\n");

		  // remove key contributor
		  rsList.remove(index);
		  
		  //recompute fusion
		  fusionResult = new RandomSet(frame, new MassAssignment(new TreeSet<String>(Arrays.asList(set1)))); // vacuous mass function
	      for (RandomSet rs: rsList){
	    	  fusionResult = fusionResult.fuseRS(rs, referee1);
	      }
	      System.out.println(fusionResult.getFuser().state(1));

	      //recompute the quality of decision as if the same decision had been made without the key contributor
		  double quality2 = fusionResult.goodnessOfDecision(decision);
		  System.out.print("Decision quality (new): ");System.out.print(quality2);System.out.print("\n");
		  //ideally a drop in quality can be seen
		//  System.out.println(quality2 - quality1);
		  
	      if (quality2 - quality1 < 0){
	    	  testcounter++;
	      }
	      
		  
	 //     System.out.print(decision); System.out.print("Key contributor: random set "); System.out.print(index+1);System.out.print(" Decision quality: ");  System.out.print(fusionResult.goodnessOfDecision(decision));System.out.print("\n");
	  }
      System.out.println(testcounter);

	}
}

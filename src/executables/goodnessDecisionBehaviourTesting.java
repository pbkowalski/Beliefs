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

public class goodnessDecisionBehaviourTesting {

	public static void main(String[] args) throws Exception {  	
	/*Initialization*/
		int monteCarloReps = 5;
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

		  for(int k = 2; k< 10; k++){

			  int numberOfBbas = k;
			  while(rsList.size()<numberOfBbas){
				  rsList.add(new RandomSet(frame, new MassAssignment(frame,rngen)));
			  }

			  RFDempster_Powerset referee1 = new RFDempster_Powerset();
		      for (RandomSet rs: rsList){
		    	  fusionResult = fusionResult.fuseRS(rs, referee1);
		    	//  System.out.println(rs.getFuser().state(1));
		      }
		      String[] decision = fusionResult.makeDecisionDistances();
		      
			  //compute decision quality
			  double quality1 = fusionResult.goodnessOfDecision(decision);
		      System.out.print("Decision quality: ");System.out.print(quality1);System.out.print("\n");
			  
		  }
	      System.out.println("===========");

		 }
		  

	}
}

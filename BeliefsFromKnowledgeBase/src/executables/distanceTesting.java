package executables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import RefereeToolbox.RFDempster_Powerset;
import RefereeToolbox.RFDisjunctive_Powerset;
import RefereeToolbox.RFDuboisPrade_Powerset;
import beliefs.FrameOfDiscernment;
import beliefs.MassAssignment;
import beliefs.RandomSet;
import utility.CSVUtil;

public class distanceTesting {

	public static void main(String[] args) throws Exception {  	
	/*Initialization*/
	//	int monteCarloReps = 500;
	  String[] set1 = {"A", "B"};
	  FrameOfDiscernment frame = new FrameOfDiscernment(set1);
	  //manually constructed mass assignment
	  Set<String> singletonSet = new HashSet<String>();
	  //RandomSet randomSet1 = new RandomSet(frame, new MassAssignment(singletonSet));
	  Random rngen = new Random(1337);
	  RandomSet randomSet1 = new RandomSet(frame, new MassAssignment(frame, rngen));
	  RandomSet randomSet2 = new RandomSet(frame, new MassAssignment(frame, rngen));
	  RandomSet	randomSet3 = new RandomSet(frame, new MassAssignment(frame, rngen));
      RFDempster_Powerset referee1 = new RFDempster_Powerset();
      RandomSet[] rs = {randomSet2, randomSet3};
      RandomSet fusionResult = randomSet1.fuseRS(rs, referee1);
      System.out.print(fusionResult.getFuser().state(1));

      System.out.println(fusionResult.makeDecisionDistances());
	}
}

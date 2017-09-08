package executables;

import java.math.BigDecimal;
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

public class contributionMetricTests {

	public static void main(String[] args) throws Exception {  	
	/*Initialization*/
	  String[] set1 = {"A", "B"};
	  FrameOfDiscernment frame = new FrameOfDiscernment(set1);
	  //manually constructed mass assignment
	  Set<String> singletonSet = new HashSet<String>();
	  singletonSet.add("A");
	  //RandomSet randomSet1 = new RandomSet(frame, new MassAssignment(singletonSet));
	  Random rngen = new Random(1337);
	  
	  String[][] keys = {{"A"},{"B"},{"A","B"}};
	  double[] probs = {0.4,0,0.6};
	  //all mass -> A
	 RandomSet randomSet1 = new RandomSet(frame, new MassAssignment(keys, probs, 1));

	  RandomSet randomSet2;



		//  System.out.print("=");
		  rngen.setSeed(32167);
		  List<Double> contribution = new ArrayList<Double>();
		  List<Double> contribution1 = new ArrayList<Double>();
		  List<Double> contributionDiff = new ArrayList<Double>();

		  List<Double> belief = new ArrayList<Double>();
		  List<Double> belief_F = new ArrayList<Double>();
		  List<Double> plaus = new ArrayList<Double>();
		  List<Double> plaus_F = new ArrayList<Double>();
		  List<Double> bel_diff = new ArrayList<Double>();
		  List<Double> plaus_diff = new ArrayList<Double>();
		  List<Double> Amass = new ArrayList<Double>();
		  List<Double> Bmass = new ArrayList<Double>();
		  List<Double> ABmass = new ArrayList<Double>();

		  String[] theProp = new String[]{"A"};
	//	  for (int i = 0; i <monteCarloReps; i++){
			//a little hack to avoid triple nested loop
		  for (int i =0; i<11; i++){
			  BigDecimal propA = new BigDecimal(i);
			  propA = propA.divide(new BigDecimal(10));
			  
			  for(int j = 0; j<=10-i;j++){		  
				  BigDecimal propB = new BigDecimal(j);
				  propB = propB.divide(new BigDecimal(10));
				  BigDecimal propAB = new BigDecimal(1);
				  propAB = propAB.subtract(propA).subtract(propB);
				  System.out.println("A: " + propA.doubleValue() + " B: "+ propB.doubleValue() +" AB: " + propAB.doubleValue());
				  double[] probs2 = {propA.doubleValue(),propB.doubleValue(),propAB.doubleValue()};
				  randomSet2 = new RandomSet(frame, new MassAssignment(keys, probs2, 1));
			      	RFDempster_Powerset RF = new RFDempster_Powerset();
				   //   RandomSet[] rs = {randomSet2, randomSet3, randomSet4};
				   //   RandomSet fusionResult = randomSet1.fuseRS(rs, RFDempster);
				      RandomSet fusionResult = randomSet1.fuseRS(randomSet2, RF);
					  List<RandomSet> rsList = new ArrayList<RandomSet>();
					  rsList.add(randomSet1);rsList.add(randomSet2);
					  if(j==10){
//						  System.out.println("debug");
					  }
					  RandomSet plausConditionedResult = fusionResult.plausibilityMass(theProp);
					//  contribution.add(1-randomSet2.plausibilityMass(theProp).findDistance(plausConditionedResult));
					//  contribution1.add(1-randomSet1.plausibilityMass(theProp).findDistance(plausConditionedResult));
					  contribution.add(1-randomSet2.findDistance(plausConditionedResult));
					  contribution1.add(1-randomSet1.findDistance(plausConditionedResult));
					  contributionDiff.add(randomSet1.findDistance(plausConditionedResult)-randomSet2.findDistance(plausConditionedResult));
					  Amass.add(propA.doubleValue());
					  Bmass.add(propB.doubleValue());
					  ABmass.add(propAB.doubleValue());
					  belief.add(randomSet2.Bel(theProp));
					  belief_F.add(fusionResult.Bel(theProp));
					  plaus.add(randomSet2.Pl(theProp));
					  plaus_F.add(fusionResult.Pl(theProp));
					  bel_diff.add(fusionResult.Bel(theProp) - randomSet2.Bel(theProp));
					  plaus_diff.add(fusionResult.Pl(theProp) - randomSet2.Pl(theProp));
				  
			  }
		  }

		  	


		//  }
		  
		  List<String> listOfHeaders = Arrays.asList("m1(A)","m1(B)","m1(AB)","contribution(m1|A)","contribution(m2|A)(0.6,0.2,0.2)","C(m2)-C(m1)","bel1(A)","plaus1(A)","bel_F(A)","bel_diff","pl_diff");
		  List<List<Double>> listOfData = new ArrayList<List<Double>>();
		  listOfData.add(Amass);listOfData.add(Bmass);listOfData.add(ABmass);
		  listOfData.add(contribution);listOfData.add(contribution1);listOfData.add(contributionDiff);
		  listOfData.add(belief);listOfData.add(plaus);listOfData.add(belief_F);listOfData.add(bel_diff);listOfData.add(plaus_diff);
		  CSVUtil.writeByColumn(listOfData, listOfHeaders, "contributionplay19_04006.csv");


	  
	
	}
}

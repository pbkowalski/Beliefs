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

public class sensitivityAnalysis {

	public static void main(String[] args) throws Exception {  	
	/*Initialization*/
	  String[] set1 = {"A", "B", "C", "D"};
	  FrameOfDiscernment frame = new FrameOfDiscernment(set1);
	  //manually constructed mass assignment
	  Set<String> singletonSet = new HashSet<String>();
	  singletonSet.add("A");
	  String[] testedvalue = new String[]{"A"};
	  double[] probabilities1 = new double[16];
	  probabilities1[1]=1;
	  MassAssignment mass1 = new MassAssignment(probabilities1, frame);
	  mass1.setConfidence(0.3);
	  
	  double[] probabilities2 = new double[16];
	  probabilities2[2]=0.4;
	  probabilities2[6]=0.4;
	  probabilities2[7]=0.2;
	  MassAssignment mass2 = new MassAssignment(probabilities2, frame);
	  mass2.setConfidence(0.8);
      RFDempster_Powerset RF = new RFDempster_Powerset();
	  RandomSet randomSet1 = new RandomSet(frame, mass1);
	  RandomSet randomSet2 = new RandomSet(frame, mass2);
      RandomSet fusionResult = randomSet1.fuseRS(randomSet2, RF);
	  RandomSet plausConditionedResult = fusionResult.plausibilityMass(testedvalue);
	  List<Double> listConf = new ArrayList<Double>();
	  List<Double> distances1 = new ArrayList<Double>();
	  List<Double> distances2 = new ArrayList<Double>();
	  List<Double> decAPbet1 = new ArrayList<Double>();
	  List<Double> decAPbet2 = new ArrayList<Double>();


	  for (double conf = 1; conf >= -0.001; conf = conf- 0.01){
		  int decisionPlaus = 0;
		  int decisionPbet = 0;
		  System.out.print("=");
		  if(conf<0){
			  randomSet1.setConfidence(0);
			  randomSet2.setConfidence(0);
			  listConf.add(0.0);
		  }
		  else{
			  randomSet1.setConfidence(conf);
			  randomSet2.setConfidence(conf);
			  listConf.add(conf);
			  }
		  
		  double distanceRs1 = randomSet1.findDistance(plausConditionedResult);
		  double distanceRs2 = randomSet2.findDistance(plausConditionedResult);
		  distances1.add(1-distanceRs1);
		  distances2.add(1-distanceRs2);
	  }
	  
	  //recompute varying rs1
	  randomSet2.setConfidence(0.7);
	  for (double conf = 1; conf >= -0.001; conf = conf- 0.01){
		  int decisionPbet1 = 0;
		  System.out.print("=");
		  if(conf<0){
			  randomSet1.setConfidence(0);
		  }
		  else{
			  randomSet1.setConfidence(conf);
			  }
	      fusionResult = randomSet1.fuseRS(randomSet2, RF);	  
		  String[] result = fusionResult.makeDecisionBetP();

		  if (Arrays.equals(result, testedvalue)){
			  decisionPbet1=1;
		  }
		  decAPbet1.add(fusionResult.Bel(testedvalue));
		  //decAPbet1.add((double) decisionPbet1);
	  }
	  
	  //recompute varying rs2
	  randomSet1.setConfidence(0.3);
	  for (double conf = 1; conf >= -0.001; conf = conf- 0.01){
		  int decisionPbet2 = 0;
		  System.out.print("=");
		  if(conf<0){
			  randomSet2.setConfidence(0);		  
			  }
		  else{
			  randomSet2.setConfidence(conf);
			  }
	      fusionResult = randomSet1.fuseRS(randomSet2, RF);
		  
		  String[] result = fusionResult.makeDecisionBetP();

		  if (Arrays.equals(result, testedvalue)){
			  decisionPbet2=1;
		  }
		  decAPbet2.add(fusionResult.Bel(testedvalue));

		  //decAPbet2.add((double) decisionPbet2);
	  }
	  List<String> listOfHeaders = Arrays.asList("conf","contribution1","contribution2","varying1", "varying2");
	  List<List<Double>> listOfData = new ArrayList<List<Double>>();
	  listOfData.add(listConf);listOfData.add(distances1);listOfData.add(distances2);listOfData.add(decAPbet1);listOfData.add(decAPbet2);
	  CSVUtil.writeByColumn(listOfData, listOfHeaders, "sensitivity.csv");
	}
}

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

public class informationSourceIdentification {

	public static void main(String[] args) throws Exception {  	
	/*Initialization*/
		int monteCarloReps = 5000;
	  String[] set1 = {"A", "B", "C"};
	  FrameOfDiscernment frame = new FrameOfDiscernment(set1);
	  //manually constructed mass assignment
	  Set<String> singletonSet = new HashSet<String>();
	  singletonSet.add("A");
	  //RandomSet randomSet1 = new RandomSet(frame, new MassAssignment(singletonSet));
	  Random rngen = new Random(1337);
	  RandomSet randomSet1 = new RandomSet(frame, new MassAssignment(frame, rngen, 0.7, 1));
	  RandomSet randomSet2, randomSet3, randomSet4;

	  List<Double> idRate = new ArrayList<Double>();
	  List<Double> decARatePbet = new ArrayList<Double>();
	  List<Double> decARatePlaus = new ArrayList<Double>();
	  List<Double> listConf = new ArrayList<Double>();


	  for (double conf = 0.8; conf >= -0.001; conf= conf - 0.01){
		  System.out.print("=");
		  rngen.setSeed(32167);
		  if(conf<0){
			  randomSet1.setConfidence(0);
			  listConf.add(0.0);
		  }
		  else{
			  randomSet1.setConfidence(conf);
			  listConf.add(conf);
			  }
		  int id_0 = 0;
		  int decisionPbet = 0;
		  int decisionPlaus = 0;
		  for (int i = 0; i <monteCarloReps; i++){
			  randomSet2 = new RandomSet(frame, new MassAssignment(frame, rngen));
			//  randomSet3 = new RandomSet(frame, new MassAssignment(frame, rngen));
			//  randomSet4 = new RandomSet(frame, new MassAssignment(frame, rngen));
		
		      RFDuboisPrade_Powerset RF = new RFDuboisPrade_Powerset();
		   //   RandomSet[] rs = {randomSet2, randomSet3, randomSet4};
		   //   RandomSet fusionResult = randomSet1.fuseRS(rs, RFDempster);
		      RandomSet fusionResult = randomSet1.fuseRS(randomSet2, RF);
			  List<RandomSet> rsList = new ArrayList<RandomSet>();
			  rsList.add(randomSet1);rsList.add(randomSet2);
			  //rsList.addAll(Arrays.asList(rs));
			  RandomSet plausConditionedResult = fusionResult.plausibilityMass(new String[]{"A"});
			  List<Double> distances = new ArrayList<Double>();
			  for (RandomSet rs1: rsList){
				  distances.add(rs1.findDistance(plausConditionedResult));
			  }
			  int index = distances.indexOf(Collections.min(distances));
			/*  System.out.println("Biased bba");
			  randomSet1.getMassAssignment().print(1);
			  System.out.println("Random bba");
			  randomSet2.getMassAssignment().print(1);
			  System.out.println("Fusion result");
			  fusionResult.getMassAssignment().print(1);*/
			  if(index == 0){id_0++;}
			  String[] result = fusionResult.makeDecisionBetP();
			  String[] result2 = fusionResult.makeDecisionPlaus();
			  if (Arrays.equals(result, new String[]{"A"})){
				  decisionPlaus++;
			  }
			  if (Arrays.equals(result2, new String[]{"A"})){
				  decisionPbet++;
			  }
		  }
		  idRate.add(((double)id_0/(double)monteCarloReps));
		  decARatePbet.add(((double)decisionPbet/(double)monteCarloReps));
		  decARatePlaus.add(((double)decisionPlaus/(double)monteCarloReps));

	  }
	  System.out.println("evidence identification rate");
	  for (double idr : idRate){
		  System.out.println(idr);
	  }
	  System.out.println("Hypothesis id rate (pbet)");

	  for (double idr : decARatePbet){
		  System.out.println(idr);
	  }
	  System.out.println("Hypothesis id rate (plaus)");
	  for (double idr : decARatePlaus){
		  System.out.println(idr);
	  }
	  List<String> listOfHeaders = Arrays.asList("Conf","idrate_distance","idrate_pbet","idrate_plaus");
	  List<List<Double>> listOfData = new ArrayList<List<Double>>();
	  listOfData.add(listConf);listOfData.add(idRate);listOfData.add(decARatePbet);listOfData.add(decARatePlaus);
	  CSVUtil.writeByColumn(listOfData, listOfHeaders, "results_bias07_fod3.csv");
	}
}

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

public class informationSourceID_varyingbias {

	public static void main(String[] args) throws Exception {  	
	/*Initialization*/
		int monteCarloReps = 15000;
	  String[] set1 = {"A", "B", "C"};
	  FrameOfDiscernment frame = new FrameOfDiscernment(set1);
	  //manually constructed mass assignment
	  Set<String> singletonSet = new HashSet<String>();
	  singletonSet.add("A");
	  //RandomSet randomSet1 = new RandomSet(frame, new MassAssignment(singletonSet));
	  Random rngen = new Random(1337);
	  RandomSet randomSet1;
	  RandomSet randomSet2, randomSet3, randomSet4;
	  List<Double> idRate = new ArrayList<Double>();
	  List<Double> decARatePbet = new ArrayList<Double>();
	  List<Double> decARatePlaus = new ArrayList<Double>();
	  List<Double> listConf = new ArrayList<Double>();
	  List<Double> avgbelieflist = new ArrayList<Double>(monteCarloReps);
	  List<Double> avgplauslist = new ArrayList<Double>(monteCarloReps);
	  List<Double> avgbetplist = new ArrayList<Double>(monteCarloReps);
	  for (double bias = 0.5; bias >= -0.001; bias= bias - 0.01){
		  System.out.print("=");
		  List<Double> belieflist = new ArrayList<Double>(monteCarloReps);
		  List<Double> plauslist = new ArrayList<Double>(monteCarloReps);
		  List<Double> betplist = new ArrayList<Double>(monteCarloReps);
		  rngen.setSeed(32167);
		  if(bias<0){
			  bias=0.0;
		  }
		  listConf.add(bias);
		  int id_0 = 0;
		  int decisionPbet = 0;
		  int decisionPlaus = 0;
		  for (int i = 0; i <monteCarloReps; i++){

			  randomSet1 = new RandomSet(frame, new MassAssignment(frame, rngen, bias, 1));
			  randomSet2 = new RandomSet(frame, new MassAssignment(frame, rngen));
			//  randomSet3 = new RandomSet(frame, new MassAssignment(frame, rngen));
			//  randomSet4 = new RandomSet(frame, new MassAssignment(frame, rngen));
		
			  RFDisjunctive_Powerset RF = new RFDisjunctive_Powerset();
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
			  belieflist.add(randomSet1.Bel(new String[]{"A"}));
			  plauslist.add(randomSet1.Pl(new String[]{"A"}));
			  betplist.add(randomSet1.Pbet(new String[]{"A"}));

			  String[] result = fusionResult.makeDecisionBetP();
			  String[] result2 = fusionResult.makeDecisionPlaus();
			  if (Arrays.equals(result, new String[]{"A"})){
				  decisionPlaus++;
			  }
			  if (Arrays.equals(result2, new String[]{"A"})){
				  decisionPbet++;
			  }
			  //avoid forever loop
			  if(bias==0.0){
				  bias=-0.001;
			  }
		  }
		  double sum = 0;
		  double avg;
		  for(double val:belieflist){
			  sum = sum+val;
		  }
		  avg = sum/(double)monteCarloReps;
		  avgbelieflist.add(avg);
		  sum=0;
		  for(double val:plauslist){
			  sum = sum+val;
		  }
		  avg = sum/(double)monteCarloReps;
		  avgplauslist.add(avg);
		  sum=0;
		  for(double val:betplist){
			  sum = sum+val;
		  }
		  avg = sum/(double)monteCarloReps;
		  avgbetplist.add(avg);
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
	  List<String> listOfHeaders = Arrays.asList("Bias","idrate_distance","idrate_pbet","idrate_plaus","avgbel","avgplaus","avgbetp");
	  List<List<Double>> listOfData = new ArrayList<List<Double>>();
	  listOfData.add(listConf);listOfData.add(idRate);listOfData.add(decARatePbet);listOfData.add(decARatePlaus);listOfData.add(avgbelieflist);listOfData.add(avgplauslist);listOfData.add(avgbetplist);
	  CSVUtil.writeByColumn(listOfData, listOfHeaders, "results_varbias_fod3_disjunctive.csv");
	}
}

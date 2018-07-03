package executables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import RefereeToolbox.RFDempster_Powerset;
import RefereeToolbox.RFDisjunctive_Powerset;
import RefereeToolbox.RFDuboisPrade_Powerset;
import RefereeToolbox.RFPCR6_Powerset;
import RefereeToolbox.RFPCRSharp_Powerset;
import RefereeToolbox.RefereeFunctionDefault;
import RefereeToolbox.RefereeFunctionDempster;
import RefereeToolbox.finalPowerset;
import beliefs.FrameOfDiscernment;
import beliefs.MassAssignment;
import beliefs.RandomSet;
import utility.CSVUtil;

public class ContributionMetricComparison4 {

	public static void main(String[] args) throws Exception {  	
	/*Initialization*/
//		int monteCarloReps = 10;
	  String[] set1 = {"A", "B", "C"};
	  FrameOfDiscernment frame = new FrameOfDiscernment(set1);
	  RandomSet fusionResult;
	  //manually constructed mass assignment
	  Set<String> singletonSet = new HashSet<String>();
	  //RandomSet randomSet1 = new RandomSet(frame, new MassAssignment(singletonSet));
	  Random rngen = new Random(2000);
	  List<Double>  propContList= new ArrayList<Double>();
	  List<Double>  deltaQList = new ArrayList<Double>();
	  List<Double>  MCRunList = new ArrayList<Double>();
	  List<Double>  propContListTessem = new ArrayList<Double>();
	  List<Double>  propQList = new ArrayList<Double>();
	  List<Double>  belList = new ArrayList<Double>();
	  List<Double>  bbanoList = new ArrayList<Double>();

      RFDempster_Powerset referee1 = new RFDempster_Powerset();
      RFDisjunctive_Powerset referee2 = new RFDisjunctive_Powerset();
      RFDuboisPrade_Powerset referee3 = new RFDuboisPrade_Powerset();
      RFPCR6_Powerset referee4 = new RFPCR6_Powerset();
      RFPCRSharp_Powerset referee5  =new RFPCRSharp_Powerset();
	  List<RefereeFunctionDefault<finalPowerset>> refereeList= new ArrayList<RefereeFunctionDefault<finalPowerset>>();
	  refereeList.add(referee1);refereeList.add(referee2);refereeList.add(referee3);refereeList.add(referee4);refereeList.add(referee5);
	  double testcounter=0;
	  double[] mass = {0, 0.1, 0.7, 0, 0.1, 0, 0, 0.1 };
	//  double[] mass2 = {0, 0.35, 0.4, 0.25, 0, 0, 0., 0 };

	 // double[] mass = {0, 0.2, 0.7, 0.1, 0, 0, 0, 0 };

	  RandomSet initRS = new RandomSet(frame, new MassAssignment(mass, frame));
	 // RandomSet initRS = new RandomSet(frame, new MassAssignment(frame, rngen, 4));
	  System.out.println("Initial RS");initRS.getMassAssignment().print(0);
	  
	  int i = 1;
	  List<RandomSet> rsList = new ArrayList<RandomSet>();

	  int numberOfBbas = 30;
	  for (int j=0; j<numberOfBbas; j++){
		  RandomSet debug = new RandomSet(initRS, (j*0.035), rngen, true);
		  debug.getMassAssignment().print(0);
		  System.out.println("=========");
		  rsList.add(debug);
	  }
	  
	/*  RandomSet seed = initRS;
	  for (int j=0; j<numberOfBbas; j++){
		  RandomSet debug = new RandomSet(seed, (0.06), rngen, true);
		  debug.getMassAssignment().print(0);
		  System.out.println("=========");
		  rsList.add(debug);
		  seed = debug;
	  }*/
	  
	  RandomSet randomSet1 = RandomSet.loadJson("data/NewRandomSet1.json");
	  RandomSet randomSet2 = RandomSet.loadJson("data/NewRandomSet2.json");
	  RandomSet randomSet3 = RandomSet.loadJson("data/NewRandomSet3.json");
	  RandomSet randomSet4 = RandomSet.loadJson("data/NewRandomSet4.json");
	  List<RandomSet> rsList2 = new ArrayList<RandomSet>();
	  rsList2.add(randomSet1);rsList2.add(randomSet2);rsList2.add(randomSet3);rsList2.add(randomSet4);
	  
	 // rsList = rsList2;
	  
	 // for(int i=0; i< monteCarloReps; i++)
	  for(RefereeFunctionDefault<finalPowerset> referee : refereeList){
	//	  fusionResult = new RandomSet(frame, new MassAssignment(new TreeSet<String>(Arrays.asList(set1)))); // vacuous mass function
		  
		  //number of unbiased randomly generated BBAs

		  
		  //Selection of fusion method
		  

	//	  RFDempster_Powerset referee1 = new RFDempster_Powerset();
	      
	      
	      
	      fusionResult = RandomSet.fuseRSList(rsList, referee);
	//      RandomSet store = fusionResult;
	 //     System.out.println(fusionResult.getFuser().state(1));
	//      System.out.print(fusionResult.getFuser().state(1));
	      String[] decision = fusionResult.makeDecisionDistances(true);
	      
	      
	      
	//      System.out.print("Decision:  ");System.out.print(decision);System.out.print("\n");

	      //find the key contributor
	      RandomSet plausConditionedResult = fusionResult.plausibilityMass(decision);
	      System.out.print("Using referee #");System.out.print(i);System.out.print("\n");
	      fusionResult.getMassAssignment().print(0);
	      System.out.print("Decision made: {"); for (String e: decision){System.out.print(e);};System.out.print("}\n");

	//      System.out.println("BBA conditioned on decision:");
    //	  System.out.println(plausConditionedResult.getFuser().state(1));

		  List<Double> distances = new ArrayList<Double>();
	//	  System.out.print("Contributions, qods: \n");
		  double sumDistances = 0;
		  double sumDistancesTessem = 0;
		  double sumQ = 0;
		  for (RandomSet rs1: rsList){
			  
			  double d = 1 - rs1.findDistance(plausConditionedResult);
			  distances.add(d);
		//	  System.out.println(1- rs1.findDistance(plausConditionedResult));
		//	  System.out.println(rs1.goodnessOfDecision(decision));
			  sumDistances += d;
			  double d2 = 1 - rs1.findTessemDistance(plausConditionedResult);
			  sumDistancesTessem += d2;
			  double q = rs1.goodnessOfDecision(decision);
			  sumQ +=q;
		  }
		  int index = distances.indexOf(Collections.max(distances));
	 //     System.out.print("Key contributor: rs ");System.out.print(index+1);System.out.print("\n");
		  //compute decision quality
		  double quality1 = fusionResult.goodnessOfDecision(decision);
	 //     System.out.print("Decision quality: ");System.out.print(quality1);System.out.print("\n");

		  
		  
		  // remove key contributor
		//  rsList.remove(index); 
		  /*
		   * for comparison try removing a random element instead - one of these lines needs to be commented out
		   */
		//  rsList.remove(rngen2.nextInt(rsList.size()));
		  
		  /*
		   * TRY: for each element remove it, compute contribution, see the change in quality, place back
		   */
		  //recompute fusion


		  double quality2 = 0;
		  int k = 0;
		  for (RandomSet rs : rsList){
			  List<RandomSet> shortList = new ArrayList<RandomSet>(rsList);
			  shortList.remove(rs);
			  double d = 1 - rs.findDistance(plausConditionedResult);
			  double propContrib = d / sumDistances;
			  double d2 = 1 - rs.findTessemDistance(plausConditionedResult);
			  double propContribTessem = d2 / sumDistancesTessem;
			  
			  double q = rs.goodnessOfDecision(decision);
			  double q_prop = q / sumQ;
			  //fuse all other elements
			  fusionResult = RandomSet.fuseRSList(shortList, referee);
			  quality2 = fusionResult.goodnessOfDecision(decision);
			  propContList.add(propContrib);
			  deltaQList.add(quality2 - quality1);
			  MCRunList.add(Double.valueOf(i));
			  propContListTessem.add(propContribTessem);
			  propQList.add(q_prop);
			  belList.add(rs.Bel(decision ));
			  bbanoList.add(Double.valueOf(k));
			  k++;

		  }
		  i++;
	//      System.out.println(fusionResult.getFuser().state(1));

	      //recompute the quality of decision as if the same decision had been made without the key contributor
	//	  System.out.print("Decision quality (new): ");System.out.print(quality2);System.out.print("\n");
		  //ideally a drop in quality can be seen
		//  System.out.println(quality2 - quality1);
		  


	      
		  
	 //     System.out.print(decision); System.out.print("Key contributor: random set "); System.out.print(index+1);System.out.print(" Decision quality: ");  System.out.print(fusionResult.goodnessOfDecision(decision));System.out.print("\n");
	  }
      List<String> listOfHeaders = Arrays.asList("proportionalContribution", "deltaQ", "MC run", "prop Tessem", "Q", "bel", "bba_no");
	  List<List<Double>> listOfData = new ArrayList<List<Double>>();
	  listOfData.add(propContList);listOfData.add(deltaQList);listOfData.add(MCRunList);listOfData.add(propContListTessem);listOfData.add(propQList);listOfData.add(belList);listOfData.add(bbanoList);
//	  CSVUtil.writeByColumn(listOfData, listOfHeaders, "test1.csv");
	  System.out.println("done");

	  List<List<Double>> listOfData2 = new ArrayList<List<Double>>();

	  for (int j=0; j<numberOfBbas; j++){
		  String js = String.valueOf(j);
		  RandomSet current = rsList.get(j);
		  List<Double> massList = new ArrayList<Double>();
		  massList.add(Double.valueOf(j));
		  String [][] powerset = current.getFrameOfDiscernment().getPowerSetWithoutEmptySet();
		  for (String[] prop : powerset){  
			  Boolean found = false;
			  //debug
			    Iterator<Entry<String[], Double>> it = current.getMassAssignment().getMass().entrySet().iterator();
			    String [] z = null;
			    while (it.hasNext()) {
			    	Entry<String[], Double> pair = it.next();
			    	z = pair.getKey();
			    	if (Arrays.equals(z, prop)){
			    		found = true;
			    		massList.add(pair.getValue());
			    		break;
			    	}
			    }
			    if (found==false){massList.add(Double.valueOf(0));}
		  }
		  listOfData2.add(massList);
	  }
//	  CSVUtil.writeByColumn(listOfData2, "test2.csv");

	  
	  
	}
}

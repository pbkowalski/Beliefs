package executables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

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

public class ContributionMetricComparison_AnalyzeSet {

	public static void main(String[] args) throws Exception {  	
	/*Initialization*/
//		int monteCarloReps = 10;
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
	  List<String>  newDecisionList = new ArrayList<String>();
	  List<List<Double>> listOfData2 = new ArrayList<List<Double>>();
	  List<List<Double>> listOfData3 = new ArrayList<List<Double>>();
	  List<String> listOfHeaders3 = new ArrayList<String>();
	  
	  List<Double>  bbanoList = new ArrayList<Double>();

      RFDempster_Powerset referee1 = new RFDempster_Powerset();
      RFDisjunctive_Powerset referee2 = new RFDisjunctive_Powerset();

	  List<RefereeFunctionDefault<finalPowerset>> refereeList= new ArrayList<RefereeFunctionDefault<finalPowerset>>();
	  refereeList.add(referee1);refereeList.add(referee2);

	  Options options = new Options();
	  Option initials   = Option.builder("in").required(false).desc("list of rs jsons to be analysed").hasArgs().argName("input.json").build();
		Option forceSingletons = new Option( "sd", "force singleton decisions" );

	  Option output1   = Option.builder("output1").required(false).desc("Main output (default test1.csv)").hasArg().argName("file.csv").build();
	  Option output2   = Option.builder("output2").required(false).desc("secondary output (default decisions.csv)").hasArg().argName("file.csv").build();
	  Option output3   = Option.builder("output3").required(false).desc("tertiary output (default masses.csv)").hasArg().argName("file.csv").build();
	  Option output4   = Option.builder("output4").required(false).desc("fourth output (default contributions.csv)").hasArg().argName("file.csv").build();

	  options.addOption(initials).addOption(output1).addOption(output2).addOption(forceSingletons);

	    CommandLineParser parser = new DefaultParser();
	    CommandLine cmd = null;
	    try {
	        // parse the command line arguments
	         cmd = parser.parse( options, args );
	    }
	    
	    catch( ParseException exp ) {
	        // oops, something went wrong
	        System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
	    }
	 // automatically generate the help statement
	    HelpFormatter formatter = new HelpFormatter();
	    
	    //generate initial RS
	    formatter.printHelp( "c_test", options );
	    String [] inputs = cmd.getOptionValues("in");
	    List<RandomSet> rsList = new ArrayList<RandomSet>();

		  for (String json: inputs){
			  rsList.add(RandomSet.loadJson(json));
		  }
	    
		  boolean sDec = false;
		  if(cmd.hasOption("sd")){
			  sDec = true;
		  }
	  
	  int i = 1;
	  for(RefereeFunctionDefault<finalPowerset> referee : refereeList){
	      
		  
	      fusionResult = RandomSet.fuseRSList(rsList, referee);
	      String[] decision = fusionResult.makeDecisionDistances(sDec);
	      RandomSet plausConditionedResult = fusionResult.plausibilityMass(decision);
	      System.out.print("Using referee #");System.out.print(i);System.out.print("\n");
	      fusionResult.getMassAssignment().print(0);
	      System.out.print("Decision made: {"); for (String e: decision){System.out.print(e);};System.out.print("}\n");
		  StringBuilder builder = new StringBuilder();
	      for (String s: decision){
			  builder.append(s).append(" ");
		  }
		  newDecisionList.add(builder.toString());
	//      System.out.println("BBA conditioned on decision:");
    //	  System.out.println(plausConditionedResult.getFuser().state(1));

		  
		  List<Double> distances = new ArrayList<Double>();
	//	  System.out.print("Contributions, qods: \n");
		  double sumDistances = 0;
		  double sumDistancesTessem = 0;
		  double sumQ = 0;
		  double j = 1;
		  List<Double> tempList2 = new ArrayList<Double>();
		  StringBuilder builder2 = new StringBuilder();
		  builder2.append("R:").append(i).append("F.elements");
		  listOfHeaders3.add(builder2.toString());
		  for(String e: fusionResult.getFrameOfDiscernment().getElements()){
			  RandomSet foo = new RandomSet(e);
			  foo.extendRandomSet(fusionResult.getFrameOfDiscernment());
			  double q2 = fusionResult.findDistance(foo);
			  
			  tempList2.add(q2);
		  }
		  listOfData3.add(tempList2);

		  for (RandomSet rs1: rsList){
			  
			  for (String e: rs1.getFrameOfDiscernment().getElements()){
				  List<Double> tempList = new ArrayList<Double>();

				  StringBuilder b = new StringBuilder();
				  b.append("R:").append(i).append("M:").append(j).append("e:").append(e);
				  listOfHeaders3.add(b.toString());
			      RandomSet plausRes = fusionResult.plausibilityMass(e);
				  double d = 1 - rs1.findDistance(plausRes);
				  double q = rs1.goodnessOfDecision(new String[] {e});
				  double q2 = fusionResult.goodnessOfDecision(new String[] {e});
				  tempList.add(d);tempList.add(q);tempList.add(q2);
				  listOfData3.add(tempList);
			  }
			  double d = 1 - rs1.findDistance(plausConditionedResult);
			  distances.add(d);
		//	  System.out.println(1- rs1.findDistance(plausConditionedResult));
		//	  System.out.println(rs1.goodnessOfDecision(decision));
			  sumDistances += d;
			  double d2 = 1 - rs1.findTessemDistance(plausConditionedResult);
			  sumDistancesTessem += d2;
			  double q = rs1.goodnessOfDecision(decision);
			  j++;
			  sumQ +=q;
		  }
		  double quality1 = fusionResult.goodnessOfDecision(decision);
	 //     System.out.print("Decision quality: ");System.out.print(quality1);System.out.print("\n");



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
			  String[] newDecision = fusionResult.makeDecisionDistances(sDec); //see if decision changed
			  builder = new StringBuilder();
			  for (String s: newDecision){
				  builder.append(s).append(" ");
			  }
			  newDecisionList.add(builder.toString());

			  
			  quality2 = fusionResult.goodnessOfDecision(decision);
			  propContList.add(propContrib);
			  deltaQList.add(quality2 - quality1);
			  MCRunList.add(Double.valueOf(i));
			  propContListTessem.add(propContribTessem);
			  propQList.add(q_prop);
			  belList.add(rs.Bel(decision ));
			  bbanoList.add(Double.valueOf(k));
			  k++;
			  List<Double> massList = new ArrayList<Double>();
			  massList.add(Double.valueOf(k));
			  String [][] powerset = fusionResult.getFrameOfDiscernment().getPowerSetWithoutEmptySet();
			  for (String[] prop : powerset){  
				  Boolean found = false;
				  //debug
				    Iterator<Entry<String[], Double>> it = fusionResult.getMassAssignment().getMass().entrySet().iterator();
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
		  i++;
	//      System.out.println(fusionResult.getFuser().state(1));

	      //recompute the quality of decision as if the same decision had been made without the key contributor
	//	  System.out.print("Decision quality (new): ");System.out.print(quality2);System.out.print("\n");
		  //ideally a drop in quality can be seen
		//  System.out.println(quality2 - quality1);
		  


	      
		  
	 //     System.out.print(decision); System.out.print("Key contributor: random set "); System.out.print(index+1);System.out.print(" Decision quality: ");  System.out.print(fusionResult.goodnessOfDecision(decision));System.out.print("\n");
	  }
      List<String> listOfHeaders = Arrays.asList("proportionalContribution", "deltaQ", "MC run", "prop Tessem", "Q");
	  List<List<Double>> listOfData = new ArrayList<List<Double>>();
	  listOfData.add(propContList);listOfData.add(deltaQList);listOfData.add(MCRunList);listOfData.add(propContListTessem);listOfData.add(propQList);
	  CSVUtil.writeByColumn(listOfData, listOfHeaders, cmd.getOptionValue("output1","test1.csv"));

	  CSVUtil.writeRow(newDecisionList, cmd.getOptionValue("output2","decisions.csv"));
	  CSVUtil.writeByColumn(listOfData2, cmd.getOptionValue("output3","masses.csv"));
	  CSVUtil.writeByColumn(listOfData3, listOfHeaders3, cmd.getOptionValue("output4","contributions.csv"));

	  
	  System.out.println("done");

	  
	  
	}
}

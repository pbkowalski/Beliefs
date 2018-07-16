package executables;

import java.io.FileNotFoundException;
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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

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
import beliefs.exceptions.InvalidInputException;
public class ContributionMetricComparison6_ExternalFixedMC {

	public static void main(String[] args) throws Exception {  	
	/*Initialization*/
		//arguments

		// test
		// create Options object
		Options options = new Options();

		// add t option
		Option divergent = new Option( "diverge", "use divergent perturbation of initial BBA" );
		Option forceSingletons = new Option( "sd", "force singleton decisions" );

		Option mciterations   = Option.builder("iterations").required(true).desc("Monte-Carlo iterations").hasArg().argName("integer").build();
		Option numberofBBAs   = Option.builder("bbas").required(true).desc("number of BBA's, including initial").hasArg().argName("integer").build();

		Option initial   = Option.builder("initRS").required(false).desc("Initial RS json file, generate randomly if empty (later)").hasArg().argName("file.json").build();
		Option framesize   = Option.builder("framesize").required(false).desc("If no initial RS provided, size of the frame of discernment, default = 3").hasArg().argName("integer").build();
		Option focalsets   = Option.builder("focals").required(false).desc("If no initial RS provided, number of focal sets, default = 4").hasArg().argName("integer").build();
		Option perturbance   = Option.builder("perturbance").required(false).desc("Perturbance rate (default - 0.03 if divergent, 0.2 otherwise").hasArg().argName("double").build();
		Option rseed   = Option.builder("rngseed").required(false).desc("rngseed, default 2000").hasArg().argName("int").build();

		Option output1   = Option.builder("output1").required(false).desc("Main output (default test1.csv)").hasArg().argName("file.csv").build();
		Option output2   = Option.builder("output2").required(false).desc("Main output (default test2.csv)").hasArg().argName("file.csv").build();

		options.addOption(divergent).addOption(forceSingletons).addOption(initial).addOption(mciterations).addOption(numberofBBAs).addOption(framesize).addOption(focalsets).addOption(perturbance).addOption(output1).addOption(output2).addOption(rseed);
		
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

	    Random rngen = new Random(Integer.parseInt(cmd.getOptionValue("rngseed", "2000")));
	    FrameOfDiscernment frame = null;
	    int randomInit=0;
	    int focals = 0;
    	RandomSet initRS = null;
	    if (cmd.hasOption("initRS")){
	    	//initial RS json provided
		    String intitialRSjson = cmd.getOptionValue("initRS");
		    try{
			 initRS = RandomSet.loadJson(intitialRSjson);}
		    catch(FileNotFoundException e){
		    	System.err.println("Caught FileNotFoundException: "
                        +  e.getMessage());
		    }

	    }
	    else{
	    	//generate random initial RS
	    	randomInit=1;

    		int fsize = Integer.parseInt(cmd.getOptionValue("framesize", "3"));
    		focals = Integer.parseInt(cmd.getOptionValue("focals", "4"));
	    	//check size
	    	if (Math.pow(2, fsize)-1 < focals){
	    		throw new InvalidInputException("Too many focal sets for given frame of discernment");
	    	}
	    	 frame = new FrameOfDiscernment(fsize);		
	    	 initRS = new RandomSet(frame, new MassAssignment(frame, rngen, focals));	
	    			
	    }
	    int monteCarloReps = Integer.parseInt(cmd.getOptionValue("iterations"));
	    int numberOfBbas = Integer.parseInt(cmd.getOptionValue("bbas"));
		  

	  RandomSet fusionResult;


	  List<Double>  propContList= new ArrayList<Double>();
	  List<Double>  deltaQList = new ArrayList<Double>();
	  List<Double>  MCRunList = new ArrayList<Double>();
	  List<Double>  propContListTessem = new ArrayList<Double>();
	  List<Double>  propQList = new ArrayList<Double>();
	  List<Double>  belList = new ArrayList<Double>();
	  List<Double>  bbanoList = new ArrayList<Double>();
	  List<List<Double>> listOfData2 = new ArrayList<List<Double>>();

      RFDempster_Powerset referee1 = new RFDempster_Powerset();
      RFDisjunctive_Powerset referee2 = new RFDisjunctive_Powerset();
	  List<RefereeFunctionDefault<finalPowerset>> refereeList= new ArrayList<RefereeFunctionDefault<finalPowerset>>();
	  refereeList.add(referee1);refereeList.add(referee2);//refereeList.add(referee3);refereeList.add(referee4);refereeList.add(referee5);
	  System.out.println("Initial RS");initRS.getMassAssignment().print(0);
	  
	  List<RandomSet> rsList = null;

	  double pert;

	  if(cmd.hasOption("perturbance")){
		  pert = Double.parseDouble(cmd.getOptionValue("perturbance"));
	  }
	  else{
		  if (cmd.hasOption("diverge")){
			  pert = 0.03;
		  }
		  else{
			  pert = 0.2;
		  }
	  }
	  boolean sDec = false;
	  if(cmd.hasOption("sd")){
		  sDec = true;
	  }
	 for(int mc=1; mc<= monteCarloReps; mc++){

		 rsList = new ArrayList<RandomSet>();
		  for (int j=0; j<numberOfBbas; j++){
			  if (cmd.hasOption("diverge")){
				  RandomSet debug = new RandomSet(initRS, (j*pert), rngen, true);
			//	  debug.getMassAssignment().print(0);
			//	  System.out.println("-----");
				  rsList.add(debug);
			  }
			  else{
				  rsList.add(new RandomSet(initRS, (pert), rngen, false));
			  }
		  }
		  int refereeVal = 1;
		  for(RefereeFunctionDefault<finalPowerset> referee : refereeList){    
			  
		      fusionResult = RandomSet.fuseRSList(rsList, referee);
		      String[] decision = fusionResult.makeDecisionDistances(sDec);
		      
		      
		      
		//      System.out.print("Decision:  ");System.out.print(decision);System.out.print("\n");
	
		      //find the key contributor
		      RandomSet plausConditionedResult = fusionResult.plausibilityMass(decision);
		  //    System.out.print("Using referee #");System.out.print(refereeVal);System.out.print("\n");
		   //   fusionResult.getMassAssignment().print(0);
		   //   System.out.print("Decision made: {"); for (String e: decision){System.out.print(e);};System.out.print("}\n");
	
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
				  sumDistances += d;
				  double d2 = 1 - rs1.findTessemDistance(plausConditionedResult);
				  sumDistancesTessem += d2;
				  double q = rs1.goodnessOfDecision(decision);
				  sumQ +=q;
			  }
			  int index = distances.indexOf(Collections.max(distances));
	
			  //compute decision quality
			  double quality1 = fusionResult.goodnessOfDecision(decision);
	
			  double quality2 = 0;
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
				  MCRunList.add(Double.valueOf(refereeVal));
				  propContListTessem.add(propContribTessem);
				  propQList.add(q_prop);
				  belList.add(rs.Bel(decision ));
				  bbanoList.add(Double.valueOf(mc));
	
			  }
			  refereeVal++;

		  }	
		  for (int j=0; j<numberOfBbas; j++){
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
				    	Set<String> foo1 = new HashSet<String>(Arrays.asList(z));
				    	Set<String> foo2 = new HashSet<String>(Arrays.asList(prop));
				    	
				    	if (foo1.equals(foo2)){
				    		found = true;
				    		massList.add(pair.getValue());
				    		break;
				    	}
				    }
				    if (found==false){massList.add(Double.valueOf(0));}
			  }
			  listOfData2.add(massList);
		  }
	 //     System.out.print(decision); System.out.print("Key contributor: random set "); System.out.print(index+1);System.out.print(" Decision quality: ");  System.out.print(fusionResult.goodnessOfDecision(decision));System.out.print("\n");
			 if (randomInit ==1){
				 initRS = new RandomSet(frame, new MassAssignment(frame, rngen, focals));	
			 }
	  }
      List<String> listOfHeaders = Arrays.asList("proportionalContribution", "deltaQ", "ref", "prop Tessem", "Q", "bel", "MC run");
	  List<List<Double>> listOfData = new ArrayList<List<Double>>();
	  listOfData.add(propContList);listOfData.add(deltaQList);listOfData.add(MCRunList);listOfData.add(propContListTessem);listOfData.add(propQList);listOfData.add(belList);listOfData.add(bbanoList);
	  CSVUtil.writeByColumn(listOfData, listOfHeaders, cmd.getOptionValue("output1","test1.csv"));
	  System.out.println("done");



	  CSVUtil.writeByColumn(listOfData2, cmd.getOptionValue("output2","test2.csv"));

	  
	  
	}
}

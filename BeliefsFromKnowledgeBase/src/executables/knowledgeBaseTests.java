package executables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import RefereeToolbox.RFDempster_Powerset;
import beliefs.MassAssignment;
import beliefs.RandomSet;
import knowledgebase.Evidence;
import knowledgebase.EvidenceCSVImporter;
import knowledgebase.EvidenceProperty;
import knowledgebase.EvidencePropositionMap;
import knowledgebase.EvidenceTarget;
import knowledgebase.KnowledgeBase;
import knowledgebase.PropositionCSVImporter;
import knowledgebase.evidencepropertyfunction.EvidencePropertyFunction;
import knowledgebase.evidencepropertyfunction.EPFBoolean;
import knowledgebase.evidencepropertyfunction.EPFFuzzyDirect;
import knowledgebase.evidencepropertyfunction.EPFLookup;
import knowledgebase.evidencepropertyfunction.EvidencePropertyFunctionFuzzySwitching;
import knowledgebase.evidencepropertyfunction.EvidencePropertyFunctionFuzzyThreshold;
import knowledgebase.evidencepropertyfunction.LookupTable;
import knowledgebase.proposition.Proposition;
import knowledgebase.proposition.PropositionProperty;

public class knowledgeBaseTests {

	public static void main(String[] args) throws Exception {
		
		/*Define proposition properties*/
		PropositionProperty<String> firstName = new PropositionProperty<String>("First Name", "aName");
		PropositionProperty<String> sex = new PropositionProperty<String>("Sex", "MALE");
		PropositionProperty<Boolean> handedness = new PropositionProperty<Boolean>("Handedness", true);
		PropositionProperty<Integer> height = new PropositionProperty<Integer>("Height", 181);
		PropositionProperty<Double> assessment = new PropositionProperty<Double>("Assessment", 0.5);

		
		/*Create initial proposition database*/
		Proposition suspectA = new Proposition(firstName, "Mr A");
		suspectA.add(sex, "MALE");
		suspectA.add(handedness, false);
		suspectA.add(assessment, 0.3);
		suspectA.add(height, 180);
	
		Proposition suspectB = new Proposition(firstName, "Mr B");
		suspectB.add(sex, "MALE");
		suspectB.add(handedness, true);
		suspectB.add(assessment, 0.6);
		suspectB.add(height, 184);
		
		Proposition suspectC = new Proposition(firstName, "Ms C");
		suspectC.add(sex, "MALE");
		suspectC.add(handedness, true);
		suspectC.add(assessment, 0.8);
		suspectC.add(height, 178);
		
		Proposition suspectD = new Proposition(firstName, "Ms D");
		suspectD.add(sex, "FEMALE");
		suspectD.add(handedness, true);
		suspectD.add(height, 170);	
		suspectD.add(assessment, 1.0);

		Proposition suspectE = new Proposition(firstName, "Mr E");
		suspectE.add(sex, "MALE");
		suspectE.add(handedness, true);
		suspectE.add(height, 220);			
		suspectE.add(assessment, 0.11);

		/*Create evidence  database*/
		EvidenceTarget killer = new EvidenceTarget("killer");
		EvidenceProperty isTall = new EvidenceProperty("isTall");
		EvidenceProperty isLeftHanded = new EvidenceProperty("isLeftHanded");
		EvidenceProperty isCapable = new EvidenceProperty("isCapable");

		Evidence evidenceSource1 = new Evidence();
		evidenceSource1.add(killer, isTall);
		evidenceSource1.setConfidence(0.7);
		Evidence evidenceSource2 = new Evidence();
		evidenceSource2.add(killer, isLeftHanded);
		evidenceSource2.setConfidence(0.3);
		Evidence evidenceSource3 = new Evidence();
		evidenceSource3.add(killer, isCapable);
		evidenceSource3.setConfidence(0.8);
		
		/*Create fuzzy computation function for isTall*/
		/*
		EvidencePropertyFunctionFuzzyThreshold istallfunctionMale= new EvidencePropertyFunctionFuzzyThreshold (177, 10, height);
		EvidencePropertyFunctionFuzzyThreshold istallfunctionFemale= new EvidencePropertyFunctionFuzzyThreshold (170, 8, height);
		List<String> switchingList = Arrays.asList("MALE", "FEMALE");
		List<PropositionProperty<?>> parameterList = new ArrayList<PropositionProperty<?>>();
		parameterList.add(sex);
		List<EvidencePropertyFunction> functionList = new ArrayList<EvidencePropertyFunction>();
		functionList.add(istallfunctionMale); functionList.add(istallfunctionFemale);
		EvidencePropertyFunctionFuzzySwitching isTallSwitch = new EvidencePropertyFunctionFuzzySwitching(functionList,parameterList,switchingList);
		*/	
		/*the above is not currently used, but remains in the code to show how a switching fuzzy function would work
		Create a map from evidence to functions of propositions using the switching fuzzy function*/
		/*
		EvidencePropositionMap esMap = new EvidencePropositionMap();
		esMap.add(isTall, isTallSwitch);
		KnowledgeBase kb = new KnowledgeBase(esMap);
		kb.add(suspectA).add(suspectB).add(suspectC).add(evidenceSource1).add(suspectD).add(suspectE);
		List<MassAssignment> listOfMasses = kb.getMassesFor(killer);
		*/


		/*Create a fuzzy lookup function for isTall*/
		LookupTable lt = new LookupTable("data/lookup1.csv", 2);
		List<PropositionProperty<?>> propList = new ArrayList<PropositionProperty<?>>();
		propList.add(sex);propList.add(height);
		EPFLookup isTallLookup = new EPFLookup(propList, lt);
		
		/*Create a boolean function for isLeftHanded*/
		List<PropositionProperty<?>> parameterListHandedness = new ArrayList<PropositionProperty<?>>();
		parameterListHandedness.add(handedness);		
		EPFBoolean isLeftHandedF = new EPFBoolean(parameterListHandedness, "and", "not");
		
		/*Create a direct fuzzy function for isCapable*/
		EPFFuzzyDirect isCapableF = new EPFFuzzyDirect(assessment);

		/*Import mock data (50 entries currently) from a csv file*/
		PropositionCSVImporter importer = new PropositionCSVImporter("data/mockdata.csv");
		
		/*Map evidence properties to corresponding functions*/
		EvidencePropositionMap esMap2 = new EvidencePropositionMap();
		esMap2.add(isTall, isTallLookup);
		esMap2.add(isLeftHanded, isLeftHandedF);
		esMap2.add(isCapable, isCapableF);
		
		//Build knowledge base, consisting of evidence-proposition map, list of propositions, and list of evidence
		KnowledgeBase kb2 = new KnowledgeBase(esMap2);
		//add 'hard-coded' data
		kb2.add(suspectA).add(suspectB).add(suspectC).add(evidenceSource1).add(suspectD).add(suspectE).add(evidenceSource2).add(evidenceSource3);
		//add mock-data imported from the csv file
		for (Proposition suspect: importer.getPropositions()){
			kb2.add(suspect);
		}
		/*Generate mass assignments from the bodies of evidence*/
		List<MassAssignment> listOfMasses2 = kb2.getMassesFor(killer);
		/*Print out the bodies of evidence and store them in random set form*/
		List<RandomSet> randomSets= new ArrayList<RandomSet>();
		for (MassAssignment mass: listOfMasses2){
			randomSets.add(new RandomSet(mass));
			System.out.println("\nMass assignment: \n");
			mass.print(0);
		}
		/*Perform Dempster-Shafer fusion on the list of random sets*/
	    RFDempster_Powerset RFDempster = new RFDempster_Powerset();
		RandomSet randomSet1 = randomSets.get(0);
		randomSets.remove(0);
		RandomSet[] rsArray = new RandomSet[randomSets.size()];
		rsArray = randomSets.toArray(rsArray);
	    RandomSet fusionResult = randomSet1.fuseRS(rsArray, RFDempster);
	    fusionResult.getMassAssignment().print(0);
		
	}

}

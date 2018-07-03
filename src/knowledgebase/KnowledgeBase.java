package knowledgebase;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beliefs.MassAssignment;
import knowledgebase.evidencepropertyfunction.EvidencePropertyFunction;
import knowledgebase.proposition.Proposition;

public class KnowledgeBase {
private List<Evidence> evidenceList;
private List<Proposition> suspectList;
private EvidencePropositionMap esMap;


public KnowledgeBase (){
	this.evidenceList = new ArrayList<Evidence>();
	this.suspectList = new ArrayList<Proposition>();
	this.esMap = new EvidencePropositionMap();
}

public KnowledgeBase (EvidencePropositionMap esMap){
	this.evidenceList = new ArrayList<Evidence>();
	this.suspectList = new ArrayList<Proposition>();
	this.esMap = esMap;
}

public KnowledgeBase add(Proposition suspect){
	this.suspectList.add(suspect);
	return this;
}

public KnowledgeBase add(Evidence evidence){
	this.evidenceList.add(evidence);
	return this;
}

public KnowledgeBase add(EvidenceProperty evP, EvidencePropertyFunction evPf){
	this.esMap.add(evP, evPf);
	return this;
}

public List<MassAssignment> getMassesFor(EvidenceTarget target){
	List<MassAssignment> massList= new ArrayList<MassAssignment>();
	for (Evidence evidence : evidenceList){
		switch(esMap.get(evidence.get(target)).getType()){
		
		case FUZZY:
			System.out.println(esMap.get(evidence.get(target)).getType());
			FuzzySet<String> fs = new FuzzySet<String>();
			for (Proposition suspect : suspectList){
				fs.put(suspect.getID(), esMap.get(evidence.get(target)).getSuspectValue(suspect));
				System.out.print(suspect.getID());System.out.print(esMap.get(evidence.get(target)).getSuspectValue(suspect));
				System.out.print("\n");
			}
			massList.add(new MassAssignment(fs, evidence.getConfidence()));
			break;
			
		case BOOLEAN:
			System.out.println(esMap.get(evidence.get(target)).getType());
			HashSet<String> trueSet = new HashSet<String>();
			for (Proposition suspect : suspectList){
				if (esMap.get(evidence.get(target)).getSuspectValue(suspect)==1){
					trueSet.add(suspect.getID());
					System.out.print(suspect.getID());System.out.print(" is left-handed \n");
				}
			}
			massList.add(new MassAssignment(trueSet,evidence.getConfidence()));
			break;
			
		default:
		}
	}
	return massList;
}

public EvidencePropositionMap getEsMap() {
	return esMap;
}


public void setEsMap(EvidencePropositionMap esMap) {
	this.esMap = esMap;
}


public List<Proposition> getSuspectList() {
	return suspectList;
}


public void setSuspectList(List<Proposition> suspectList) {
	this.suspectList = suspectList;
}


public List<Evidence> getEvidenceList() {
	return evidenceList;
}


public void setEvidenceList(List<Evidence> evidenceList) {
	this.evidenceList = evidenceList;
}
public void saveToGson(String filename) throws IOException{
	StringBuilder sb = new StringBuilder();
	String name = sb.append("data/").append(filename).toString();
	Writer writer = new FileWriter(name);

    Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
    gson.toJson(this, writer);

    writer.close();
}
}
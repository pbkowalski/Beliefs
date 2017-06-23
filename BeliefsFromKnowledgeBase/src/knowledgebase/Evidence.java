package knowledgebase;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Evidence extends KnowledgeMap<EvidenceTarget, EvidenceProperty>{
double confidence=1;
String evidenceSource; //placeholder
	public Evidence(){
		this.setMap(new HashMap<EvidenceTarget, EvidenceProperty>());
	}
	public void setConfidence(double newConfidence){
		this.confidence = newConfidence;
	}
	
	public double getConfidence(){
		return this.confidence;
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

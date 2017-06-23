package knowledgebase.proposition;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import knowledgebase.KnowledgeMap;
import knowledgebase.StringMap;

public class Proposition extends KnowledgeMap<PropositionProperty<?>, Object> {
private PropositionProperty<?> idProperty;
public Proposition(PropositionProperty<?> idProperty, Object idValue){
	this.idProperty = idProperty;
	this.setMap(new HashMap<PropositionProperty<?>,Object>());
	this.add(idProperty, idValue);
}

public String getID(){
	return (String) this.get(idProperty);
}

public Boolean getBooleanProperty(PropositionProperty<?> property){
	if (property.getType().isAssignableFrom(new String().getClass())){
	String prop = (String) this.getMap().get(property);
	return Boolean.getBoolean(prop);}
	else if (property.getType().isAssignableFrom(new Boolean(true).getClass())){
		return (Boolean) this.getMap().get(property); //ideally only this should happen
	}
	else
		return false;
	
	
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

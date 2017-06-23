package knowledgebase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import knowledgebase.proposition.Proposition;
import knowledgebase.proposition.PropositionProperty;
import utility.Utility;

/**
 * @author pawel
 * A class used to create several Proposition objects and corresponding PropositionProperty objectsfrom a CSV file
 * Formatted as follows: first row lists the proposition property names, subsequent row fill the data
 * with one row per proposition
 *  */
public class PropositionCSVImporter {
	List<Proposition> propositions;
	
	
	public PropositionCSVImporter(String csvFile) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
        propositions = new ArrayList<Proposition>();
		String line = "";
        String cvsSplitBy = ",";
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        List<String> propertiesAsStrings = new ArrayList<String>();
        List<PropositionProperty<?>> properties = new ArrayList<PropositionProperty<?>>(); 

        //read the first line to get the names of properties
        if( (line = br.readLine()) != null){
        	propertiesAsStrings = Arrays.asList(line.split(cvsSplitBy));
        }
        //read the second line to get the types
        
        if( (line = br.readLine()) != null){
        	String[] firstProp = line.split(cvsSplitBy);                
	        //create an list of PropositionProperty objects and fill in from the list
	        int iterator = 0;
	        for(String aProp: propertiesAsStrings){
				Class<?> clazz = Utility.getStringType(firstProp[iterator]);
				if(clazz == Integer.class){
		        	properties.add(new PropositionProperty<Integer>(aProp, Integer.parseInt(firstProp[iterator])));
				}
				else if(clazz == Double.class){
		        	properties.add(new PropositionProperty<Double>(aProp, Double.parseDouble(firstProp[iterator])));
				}
				else if(clazz == Boolean.class){
		        	properties.add(new PropositionProperty<Boolean>(aProp, Boolean.parseBoolean(firstProp[iterator])));
				}
				else{
		        	properties.add(new PropositionProperty<String>(aProp, firstProp[iterator]));
				}
				iterator++;
	        }
        Class<?> clazz = Utility.getStringType(firstProp[0]);
        Proposition aProp = new Proposition(properties.get(0), clazz.getConstructor(String.class).newInstance(firstProp[0]));
        int i=0;
	        for (String entry: firstProp){
	        	clazz = Utility.getStringType(entry);
	            aProp.add(properties.get(i), clazz.getConstructor(String.class).newInstance(entry));
	            i++;	
	        }
        propositions.add(aProp);
        }
        while ((line = br.readLine()) != null) {
            // use comma as separator
        	String[] values = line.split(cvsSplitBy);
            Class<?> clazz = Utility.getStringType(values[0]);
            Proposition aProp = new Proposition(properties.get(0), clazz.getConstructor(String.class).newInstance(values[0]));
            int i=0;
    	        for (String entry: values){
    	        	clazz = Utility.getStringType(entry);
    	            aProp.add(properties.get(i), clazz.getConstructor(String.class).newInstance(entry));
    	            i++;	
    	        }
            propositions.add(aProp);
            }
        }

	public List<Proposition> getPropositions(){
		return this.propositions;
	}
}


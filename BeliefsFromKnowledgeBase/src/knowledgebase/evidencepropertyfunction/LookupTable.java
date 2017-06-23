package knowledgebase.evidencepropertyfunction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import utility.Utility;
@SuppressWarnings("serial")
public class LookupTable extends TreeMap<LookupKey, Double>{

	
	public LookupTable(String csvFile, int args) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
        String line = "";
        String cvsSplitBy = ",";
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        List<String> listOfKeys = new ArrayList<String>();
        //read first line
        if( (line = br.readLine()) != null){
        	listOfKeys = Arrays.asList(line.split(cvsSplitBy));
        }
        while ((line = br.readLine()) != null) {

            // use comma as separator
        	String[] values = line.split(cvsSplitBy);
        	LookupKey lookupKey = new LookupKey();
        	for (int i=0; i<args;i++){ //there is one more entry in values than in keys
        		Class<?> clazz = Utility.getStringType(values[i]);
        		//clazz.getConstructor(String.class).newInstance(values[i]);
        		lookupKey.put(listOfKeys.get(i), clazz.getConstructor(String.class).newInstance(values[i]));
        	}
        	this.put(lookupKey, Double.parseDouble(values[args]));
        }
	//parse first line to create list of keys, passing number of args to make life easier
	//read listOfKeys from the first row in the csv file	
	}
	
	public double get (LookupKey lookupKey){
		if (this.containsKey(lookupKey)){
			return super.get(lookupKey);
		}
		else{
			Map<String,Object> higher = this.higherKey(lookupKey);
			Map<String,Object> lower = this.lowerKey(lookupKey);
			double higherval = this.get(higher);
			double lowerval = this.get(lower);
			return (higherval+lowerval)/2; //temporary - should do proper interpolation
		}
	}
	
	


}


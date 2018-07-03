package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class CSVUtil {

	public static void writeByColumn(List<List<Double>> listOfData, List<String> listOfHeaders, String filename) throws IOException{
        CSVWriter writer = new CSVWriter(new FileWriter(filename), ',');
        List<String[]> table = new ArrayList<String[]>();
        String[] headerArray = listOfHeaders.toArray(new String[0]);
        table.add(headerArray);
        for(int i=0; i<listOfData.get(0).size(); i++){ //for each line
        	List<String> newline = new ArrayList<String>(listOfData.size());
        	for (List<Double> column: listOfData){
        		newline.add(Double.toString(column.get(i)));
        	}
        	String[] debug = newline.toArray(new String[listOfData.size()]);
        	table.add(debug);
        }
        writer.writeAll(table, false);
        writer.close();
	}
	
	public static void writeByColumn(List<List<Double>> listOfData, String filename) throws IOException{
        CSVWriter writer = new CSVWriter(new FileWriter(filename), ',');
        List<String[]> table = new ArrayList<String[]>();
        for(int i=0; i<listOfData.get(0).size(); i++){ //for each line
        	List<String> newline = new ArrayList<String>(listOfData.size());
        	for (List<Double> column: listOfData){
        		newline.add(Double.toString(column.get(i)));
        	}
        	String[] debug = newline.toArray(new String[listOfData.size()]);
        	table.add(debug);
        }
        writer.writeAll(table, false);
        writer.close();
	}
	
	public static void writeRow(List<String> list, String filename) throws IOException{
        CSVWriter writer = new CSVWriter(new FileWriter(filename), ',');
        List<String[]> table = new ArrayList<String[]>();
        for (String entry:list){
        	List<String> newline = new ArrayList<String>();
        	newline.add(entry);
        	table.add(newline.toArray(new String[1]));
        }
        writer.writeAll(table, false);
        writer.close();
	}
	
	
	
}


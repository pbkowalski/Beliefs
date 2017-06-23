package beliefs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import RefereeToolbox.finalPowerset;


public class FrameOfDiscernment {
	Set<String> elements;
	
	public FrameOfDiscernment(int size){
		elements = new LinkedHashSet<String>(size);
		char[] alphabeticArray = populateElementsList(size);

		for (int i=0; i<size; i++){
			elements.add(Character.toString(alphabeticArray[i]));  // no string list of elements provided, A,B,C... are used
		}

	}
	
	public FrameOfDiscernment(String[] sets){
		elements = new LinkedHashSet<String>(Arrays.asList(sets));
	}
	
	public FrameOfDiscernment(Set<String> elements){
		this.elements= elements;
	}
	/**
	 * Return a new frame of discernment containing elements of <i>this</i> and <i>frame2</i>
	 * @param frame2
	 * @return
	 */
	public FrameOfDiscernment mergeFrames(FrameOfDiscernment frame2){
		Set<String> newElements = new LinkedHashSet<String>();
		newElements.addAll(this.elements);
		newElements.addAll(frame2.elements);
		FrameOfDiscernment newFrame = new FrameOfDiscernment(newElements);
		return newFrame;
	}
	
	/**
	 * Return a char array of elements A,B,C....Z (will produce gibberish for larger values but this is temporary anyway) to keep this compatible with the original version
	 * @param size
	 * @return
	 */
	char[] populateElementsList(int size){
		char[] elements = new char[size];
		elements[0]='A';
		for (int i = 1; i < elements.length; i++){
			elements[i]= (char) (elements[i-1] + 1);
		}
		return elements;
	}
	/**
	 * Getter method for elements set
	 * @return
	 */
	public Set<String> getElements(){
		return elements;
	}
	
	public int getPowersetSize(){
		return (int) Math.pow(2, elements.size());
	}
	
	/**
	 * Given the subset value return the corresponding subset as an array of strings
	 * @param subsetVal
	 * @return
	 */
	public String[] getSubset(int subsetVal){
		  HashMap<String, Integer> elementOrder = new HashMap<String, Integer>();
		  Iterator<String> itr = elements.iterator();
		  int order = 0;
		  while (itr.hasNext()){
			  elementOrder.put(itr.next(), order);
			  order++;
		  }
		  List<String> subsetAsList = new ArrayList<String>();
		  Iterator<Entry<String, Integer>> it = elementOrder.entrySet().iterator();
		  while(it.hasNext()){
			  Entry<String,Integer> current = it.next();
			  if( ( (subsetVal & ( 1 << current.getValue() ) ) !=0)){
				  subsetAsList.add(current.getKey());
			  }
		  }
		  return subsetAsList.toArray(new String[subsetAsList.size()]);
	}
	public Integer getSubsetValue(String[] subset){
		for (String element: subset){
			if(!elements.contains(element)){
				return null;
			}
		}
		int index = 0;
		 HashMap<String, Integer> elementOrder = new HashMap<String, Integer>();
		 Iterator<String> itr = elements.iterator();
		 int order = 0;
		 while (itr.hasNext()){
		  elementOrder.put(itr.next(), order);
		  order++;
		 }		
		 for (String element: subset){
			 index = (int) (index + Math.pow(2, elementOrder.get(element)));
		 }
		 return index;			
		
	}
	
	/**
	 * Return a two-dimensional string array containing the identifiers of all the members of the power set.
	 * @return
	 */
	public String[][] getPowerSet(){
		List<String[]> elementList = new ArrayList<String[]>();
		for(int i = 0; i<(int)Math.pow(2, elements.size());i++){
			elementList.add(getSubset(i));

		}
		return elementList.toArray(new String[elementList.size()][]);
	}
	/**
	 * Given the finalPowerset object return the focal set as an array of strings
	 * @param aProp
	 * @return
	 */
	public String[] getFocalSet(finalPowerset aProp){
		finalPowerset[] elements = generatePowerset();
		//int index = Arrays.asList(elements).indexOf(aProp);
		int index =0;
		for (finalPowerset element : elements){
			if (element.compareTo(aProp)==0){break;}
			index++;
		}
		if (index == elements.length /*match not found*/){
			return null;
		}
		else{
			return getSubset(index);
		}
	}
	public finalPowerset[] generatePowerset(){
		int thetaPowerSet = this.getPowersetSize();
		finalPowerset[] randomMass = new finalPowerset[thetaPowerSet];
		for (int i=0;i<thetaPowerSet;i++)
			  randomMass[i] = new finalPowerset();
		//initialise empty set value, size
		//randomMass[0].zero();
		//randomMass[0].size(cardTheta);
		int size = elements.size();
		finalPowerset temp = new finalPowerset();
		temp.size(size);
		for (int i=0;i<thetaPowerSet;i++)
		{
			  randomMass[i].size(size); //initialise and set size of every element of the randomMass finalPowerset array
			  for (int j=0; j<size;j++){
				  if( (i & (1 << j)) != 0){ //check every bit in i if it is set, note i has not more than cardTheta bits
					  temp.atomic(j);
					  randomMass[i].or(randomMass[i], temp);
					  }
			  } 	
			  	

		}
		return randomMass;
	}
	
}



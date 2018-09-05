package beliefs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import RefereeToolbox.finalPowerset;


public class FrameOfDiscernment {
	TreeSet<Proposition> elements;
	
	public FrameOfDiscernment(int size){
		elements = new TreeSet<Proposition>();
		char[] alphabeticArray = populateElementsList(size);

		for (int i=0; i<size; i++){
			elements.add(new Proposition(Character.toString(alphabeticArray[i])));  // no string list of elements provided, A,B,C... are used
		}

	}
	
	public FrameOfDiscernment(String[] sets){
		elements = new TreeSet<Proposition>();
		for (String s: sets){
			elements.add(new Proposition(s));
		}
	}


	public FrameOfDiscernment(Set<Proposition> e) {
		this.elements = new TreeSet<Proposition>(e);
	}


	/**
	 * Return a new frame of discernment containing elements of <i>this</i> and <i>frame2</i>
	 * @param frame2
	 * @return
	 */
	public FrameOfDiscernment mergeFrames(FrameOfDiscernment frame2){
		TreeSet<Proposition> newElements = new TreeSet<Proposition>();
		newElements.addAll(this.elements);
		newElements.addAll(frame2.elements);
		FrameOfDiscernment newFrame = new FrameOfDiscernment(newElements);
		return newFrame;
	}
    /**
     * Return a new frame of discernment containing union of elements of <i>this</i> and <i>frame2</i>
     * @param frame2
     * @return
     */
	public FrameOfDiscernment extendFrame(FrameOfDiscernment frame2){
        TreeSet<Proposition> newElements = new TreeSet<Proposition>();
        String separator = "|";
        String wrapper = "$";
        for (Proposition p: this.elements){
            String part1 = wrapper + p.get() + wrapper;
            for (Proposition p2: frame2.elements){
                String part2 = wrapper + p2.get() + wrapper;
                String combined = part1+separator+part2;
                newElements.add(new Proposition(combined));
            }
        }
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
	public Set<Proposition> getElements(){
		return elements;
	}
	
	public int getPowersetSize(){
		return (int) Math.pow(2, elements.size());
	}
	
	public int getElementIndex(Proposition element) {
		 return elements.contains(element)? elements.headSet(element).size(): -1;
	}
	/**
	 * Given the subset value return the corresponding subset as a HashSet<Proposition>
	 * @param subsetVal
	 * @return
	 */
	public HashSet<Proposition> getSubset(int subsetVal){
		  HashMap<Proposition, Integer> elementOrder = new HashMap<Proposition, Integer>();
		  Iterator<Proposition> itr = elements.iterator();
		  int order = 0;
		  while (itr.hasNext()){
			  elementOrder.put(itr.next(), order);
			  order++;
		  }
		  
		  HashSet<Proposition> subset = new HashSet<Proposition>();
		  Iterator<Entry<Proposition, Integer>> it = elementOrder.entrySet().iterator();
		  while(it.hasNext()){
			  Entry<Proposition, Integer> current = it.next();
			  if( ( (subsetVal & ( 1 << current.getValue() ) ) !=0)){
				  subset.add(current.getKey());
			  }
		  }
		  return subset;
	}
	public Integer getSubsetValue(Set<Proposition> subset) {
		for (Proposition element: subset){
			if(!elements.contains(element)){
				return null;
			}
		}
		int index = 0;
		 HashMap<Proposition, Integer> elementOrder = new HashMap<Proposition, Integer>();
		 Iterator<Proposition> itr = elements.iterator();
		 int order = 0;
		 while (itr.hasNext()){
		  elementOrder.put(itr.next(), order);
		  order++;
		 }		
		 for (Proposition element: subset){
			 index = (int) (index + Math.pow(2, elementOrder.get(element)));
		 }
		 return index;			
	}
	public Integer getSubsetValue(String[] subset){
		return getSubsetValue(Proposition.createFocalSet(subset));
	}
	
	/**
	 * Return a two-dimensional string array containing the identifiers of all the members of the power set.
	 * @return
	 */
	public List<HashSet<Proposition>> getPowerSet(){
		List<HashSet<Proposition>> elementList = new ArrayList<HashSet<Proposition>>();
		for(int i = 0; i<(int)Math.pow(2, elements.size());i++){
			elementList.add(getSubset(i));
		}
		return elementList;
	}
	
	/**
	 * Return a two-dimensional string array containing the identifiers of all the members of the power set without the empty set
	 * @return
	 */
	public List<HashSet<Proposition>> getPowerSetWithoutEmptySet(){
		List<HashSet<Proposition>> elementList = new ArrayList<HashSet<Proposition>>();
		for(int i = 1; i<(int)Math.pow(2, elements.size());i++){
			elementList.add(getSubset(i));
		}
		return elementList;
	}
	/**
	 * Given the finalPowerset object return the focal set 
	 * @param aProp
	 * @return
	 */
	public HashSet<Proposition> getFocalSet(finalPowerset aProp){
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



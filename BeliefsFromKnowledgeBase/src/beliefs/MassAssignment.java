package beliefs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Random;

import beliefs.exceptions.probabilityException;
import knowledgebase.FuzzySet;
import utility.Utility;
public class MassAssignment{
	HashMap<String[],Double> bba;
	double confidence=1;

	public MassAssignment(HashMap<String[], Double> bba){
		this.bba=bba;
	}
	
	public MassAssignment(String[][] keys,double probs[], double confidence){
		this(keys, probs);
		this.setConfidence(confidence);
		
	}

	/**
	 * Standard constructor taking in a 2-dimensional string array and a double array
	 * @param keys
	 * @param probs
	 */
	public MassAssignment(String[][] keys,double probs[]){
	if (keys.length != probs.length)
		throw new IllegalArgumentException("The number of keys provided is not equal to number of probability values provided.");
	this.bba = new HashMap<String[], Double>(probs.length);
	probs = probabilityTest(probs);
	for (int i=0; i<probs.length; i++)
		bba.put(keys[i], probs[i]);

	}

	/**
	 * Constructor using the old method of passing a probability array and a frame of discernment to produce a massAssignment
	 * @param probs
	 * @param frame
	 */
	public MassAssignment(double probs[], FrameOfDiscernment frame){
		if (frame.getPowerSet().length < probs.length)
			throw new IllegalArgumentException("The frame of discernment is too small to contain the probabilities array");
		this.bba = new HashMap<String[], Double>(probs.length);
		for (int i = 0; i<probs.length; i++){
			if (probs[i]>0){
				
				bba.put(frame.getSubset(i), probs[i]);
			}			
		}
		
	}
	/**
	 * Generate a mass assignment from a fuzzy set
	 * @param fs
	 */
	public MassAssignment(FuzzySet<String> fs){
		this.bba = new HashMap<String[], Double>(fs.size());
		LinkedHashMap<String, Double> sortedFS = (LinkedHashMap<String, Double>) Utility.sortByValue(fs);
		double prevVal = 0;
		double newVal;
		Iterator<Entry<String, Double>> it = sortedFS.entrySet().iterator();
		Set<String> elements = new HashSet<String>(sortedFS.keySet());
			while (it.hasNext()){
				Entry<String, Double> currentMapping = it.next();
				newVal = currentMapping.getValue() - prevVal;
				this.bba.put(elements.toArray(new String[elements.size()]), newVal);
				prevVal = currentMapping.getValue();
				elements.remove(currentMapping.getKey());
			}
	}
	
	public MassAssignment(FuzzySet<String> fs, double confidence){
		this(fs);
		this.setConfidence(confidence);
	}
	/**
	 * generate a mass assignment which assigns all the mass to a single focal set
	 * @param singleFocalSet
	 */
	public MassAssignment(Set<String> elements){
		this.bba = new HashMap<String[], Double>();
		this.bba.put(elements.toArray(new String[elements.size()]), 1.0);
	}
	
	public MassAssignment(Set<String> elements, double confidence){
		this(elements);
		this.setConfidence(confidence);
	}
	/**
	 * Generate a uniform mass assignment for <i>keys</i>
	 * @param keys
	 */
	public MassAssignment(String[][] keys){
		double probability = 1/keys.length;
		this.bba = new HashMap<String[], Double>(keys.length);
		for (String[] key : keys){
			bba.put(key, probability);
		}
	}
	/**
	 * Generate a random mass assignment <i>keys</i> using <i>rngen</i>
	 * @param keys
	 */
	public MassAssignment(String[][] keys, Random rngen){
		double[] probs = new double[keys.length];
		for (int i=0; i < keys.length; i++){
			probs[i]=rngen.nextDouble();
		}
		//normalize so that the probabilities add to 1
		probs = probabilityTest(probs);
		this.bba = new HashMap<String[], Double>(keys.length);
		int i = 0;
		for (String[] key : keys){
			bba.put(key, probs[i]);
			i++;
		}
	}
	/**
	 * Generate a random mass assignment for <i>frame</i> using <i>rngen</i>
	 * @param keys
	 */
	public MassAssignment(FrameOfDiscernment frame , Random rngen){
		
		double[] probs = new double[frame.getPowersetSize()];
		//probability of the empty set = 0
		probs[0]=0;
		for (int i=1; i < frame.getPowersetSize(); i++){
			probs[i]=rngen.nextDouble();
		}
		//normalize so that the probabilities add to 1
		probs = normalize(probs, 1.0);
		this.bba = new HashMap<String[], Double>(probs.length);
		for (int i = 0; i<probs.length; i++){
			if (probs[i]>0){
				
				bba.put(frame.getSubset(i), probs[i]);
			}			
		}
		
	}
	
	/**
	 * Generate a random mass assignment for <i>frame</i> using <i>rngen</i> biased by <i>bias</i> towards <i>proposition</i>
	 * @param keys
	 */
	//tiem constraitns - temporarily use int to denote the proposition isntead of a more appropriate class
	public MassAssignment(FrameOfDiscernment frame , Random rngen, double bias, int proposition){
		
		double[] probs = new double[frame.getPowersetSize()];
		//probability of the empty set = 0
		probs[0]=0;
		for (int i=1; i < frame.getPowersetSize(); i++){
			probs[i]=rngen.nextDouble();
		}
		//normalize so that the probabilities add to 1
		probs = normalize(probs, 1-bias);
		probs[proposition] = probs[proposition] + bias;
		this.bba = new HashMap<String[], Double>(probs.length);
		for (int i = 0; i<probs.length; i++){
			if (probs[i]>0){
				
				bba.put(frame.getSubset(i), probs[i]);
			}			
		}
		
	}
	/**

	 * Getter method for mass assignment 
	 * @return
	 */
	public HashMap<String[], Double> getMass(){
		return bba;
	}

	/**
	 * Return the confidence parameter
	 * @return confidence
	 */
	public double getConfidence(){
		return confidence;
	}

	/**
	 * Set the confidence parameter
	 * @param newConfidence - new confidence value
	 */
	public void setConfidence(double newConfidence){
		if (newConfidence < 0 || newConfidence > 1)
			throw new IllegalArgumentException("Confidence value has to be between 0 and 1. Passed confidence ="+Double.toString(newConfidence));
		this.confidence=newConfidence;
	}
/**
 * Method returning the set of elements in the mass assignment, allowing construction of a frame of discernment object from this
 * @return
 */
	public Set<String> getElements(){
		Set<String> Elements = new LinkedHashSet<String>();
		Iterator<Entry<String[], Double>> it = bba.entrySet().iterator();
		
		while (it.hasNext()){
			Entry<String[], Double> currentMapping = it.next();
			  for (String setElement : currentMapping.getKey()){ //iterate through the elements of the focal set and add them to the set of elements; HashSet guarantees there will be no repetitions
				  Elements.add(setElement);
			  }
		  }
		return Elements;
	}
	public void print(int printzeros){
		Iterator<Entry<String[], Double>> it = bba.entrySet().iterator();
		while (it.hasNext()){
			Entry<String[], Double> currentMapping = it.next();
			if (printzeros==1 || currentMapping.getValue()>0){
				for (String s : currentMapping.getKey()){
					System.out.printf(s).print(" ");;
				}
				System.out.print(": ");
				System.out.print(currentMapping.getValue());
				System.out.print("\n");
			}
		}
		System.out.print("Confidence value: ");System.out.print(this.getConfidence());System.out.print("\n");
	}
/**
 * Check if probabilities sum up to 1 and if not return a probability array with normalised probabilities
 * @param probs
 * @return
 */
	private static double[] probabilityTest(double probs[]){
		double[] fixedProbs = probs.clone();
		try{
			probabilitySum(probs);
		}
		catch (probabilityException e){
			double sum=0;
			e.printStackTrace();
			for(int i=0; i< probs.length; i++){
				sum=sum+probs[i];}
			for(int i=0; i< probs.length; i++){
				fixedProbs[i]=probs[i]/sum;}
		}
		return fixedProbs;
		
	}
	/**
	 * Check if probabilities sum up to val and if not return a probability array with normalised probabilities
	 * DO NOT throw an exception - good to use when probabilities are not expected to sum to 1 anyway
	 * can be used to normalize to a non-1 value for generation of biased random probabilities
	 * @param probs
	 * @return
	 */	
	private static double[] normalize(double probs[], double val){
		double[] fixedProbs = probs.clone();
		double sum=0;
		for(int i=0; i< probs.length; i++){
			sum=sum+probs[i];}
		if (sum< val-0.00001 || sum > val+0.00001){ //allow 5sf for double precision issues
			sum=0;
			for(int i=0; i< probs.length; i++){
				sum=sum+probs[i];}
			for(int i=0; i< probs.length; i++){
				fixedProbs[i]=(probs[i]/sum)*val;}
			return fixedProbs;
		}
		else{
			return probs;
		}
		
	}
	/**
	 * Check if probabilities sum up to 1
	 * @param probs
	 * @throws probabilityException
	 */
	private static void probabilitySum(double probs[]) throws probabilityException{
		double sum=0;
		for(int i=0; i< probs.length; i++){
			sum=sum+probs[i];}
		if (sum< 0.99999 || sum > 1.00001){ //allow 5sf for double precision issues
			throw new probabilityException("Probabilities must sum to 1, currently"+sum);

		}
	}
	
	
}
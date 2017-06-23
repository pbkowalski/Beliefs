package beliefs;
import RefereeToolbox.Assignment;
import RefereeToolbox.RefereeFunctionDefault;
import RefereeToolbox.finalPowerset;
import RefereeToolbox.finalRefereeFuserRTS_Powerset;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


public class RandomSet {
	private FrameOfDiscernment foD;
	private MassAssignment mass;
	

	public RandomSet (finalRefereeFuserRTS_Powerset theFuser, FrameOfDiscernment frame){
		ArrayList<Assignment<finalPowerset>> assignments = theFuser.toArray();
		List<String[]> elementList = new ArrayList<String[]>();
		List<Double> probList = new ArrayList<Double>();
		for (Assignment<finalPowerset> a : assignments){
			int stateVal = a.attribute.stateAsInt();
			elementList.add(frame.getSubset(stateVal));
			probList.add(a.value);
		}
		double[] probs = new double[probList.size()];
		for (int i = 0; i< probs.length; i++){
			probs[i] = probList.get(i).doubleValue();
		}
		this.mass = new MassAssignment(elementList.toArray(new String[elementList.size()][]), probs);
		this.foD = frame;
		
	}
	public RandomSet(FrameOfDiscernment initfoD, MassAssignment mass){
		foD=initfoD;
		this.mass=mass;
	}
	public RandomSet(MassAssignment mass){
		this.mass=mass;
		foD=new FrameOfDiscernment(mass.getElements());
	}
	public finalRefereeFuserRTS_Powerset getFuser(){
		  int foDPowerSet=foD.getPowersetSize();
		  
		  //make it possible to obtain the index of a set element for creation of the proposition
		  HashMap<String, Integer> elementOrder = new HashMap<String, Integer>();
		  Iterator<String> itr = foD.getElements().iterator();
		  int order = 0;
		  while (itr.hasNext()){
			  elementOrder.put(itr.next(), order);
			  order++;
		  }
		  
		  finalRefereeFuserRTS_Powerset aFuser1 = new finalRefereeFuserRTS_Powerset();
		  HashMap<String[], Double> map = mass.getMass();
		  Iterator<Entry<String[], Double>> it = map.entrySet().iterator();
		  int index=0;
		  aFuser1.maxSize(foDPowerSet);
		  finalPowerset[] subsets = foD.generatePowerset();
		  while (it.hasNext()){ //iterate through the elements of the mass assignment
			  Entry<String[], Double> currentMapping = it.next();
			  index=0;
			  for (String setElement : currentMapping.getKey()){ //iterate through the elements of the focal set to generate proposition index
				  index += Math.pow(2, elementOrder.get(setElement)); 
			  }
			  aFuser1.add(subsets[index], currentMapping.getValue()*mass.getConfidence());
			  
		  }
		  aFuser1.add(subsets[(int) Math.pow(2, elementOrder.size())-1], 1 - mass.getConfidence());
		  return aFuser1;
	}
	
	public RandomSet plausibilityMass (String[] focalSet){
		finalPowerset aProp = this.getProposition(focalSet);
		return new RandomSet(this.getFuser().plausibilityMass(aProp), this.foD);
	}
	
	public RandomSet plausibilityMass (finalPowerset aProp){
		return new RandomSet(this.getFuser().plausibilityMass(aProp), this.foD);
	}
	
	public finalPowerset getProposition(String[] focalSet){
		  finalPowerset[] subsets = foD.generatePowerset();
		  //make it possible to obtain the index of a set element for creation of the proposition
		  HashMap<String, Integer> elementOrder = new HashMap<String, Integer>();
		  Iterator<String> itr = foD.getElements().iterator();
		  int order = 0;
		  while (itr.hasNext()){
			  elementOrder.put(itr.next(), order);
			  order++;
		  }		  
		int index = 0;
		for (String setElement : focalSet){
			  index += Math.pow(2, elementOrder.get(setElement)); 
		}
		return subsets[index];
		
	}
	
	public void setConfidence(double newConfidence){
		this.mass.setConfidence(newConfidence);
	}
	/**
	 * This changes the frame of discernment of the random set by adding the elements which are present in frame 2, but in in the current frame of discernment
	 * @param frame2
	 */
	public void extendRandomSet(FrameOfDiscernment frame2){
		this.foD = foD.mergeFrames(frame2);
	}
	public FrameOfDiscernment getFrameOfDiscernment(){
		return foD;
	}
	public MassAssignment getMassAssignment(){
		return mass;
	}
	

	public RandomSet fuseRS(RandomSet rs2, RefereeFunctionDefault<finalPowerset> theRefereeFunction){
		/*
		 * Align frames of reference
		 */
		FrameOfDiscernment jointFrame = this.getFrameOfDiscernment().mergeFrames(rs2.getFrameOfDiscernment());
		RandomSet temp1 = new RandomSet(jointFrame, this.getMassAssignment());
		RandomSet temp2 = new RandomSet(jointFrame, rs2.getMassAssignment());
		finalRefereeFuserRTS_Powerset fusionOutcome = new finalRefereeFuserRTS_Powerset();
		fusionOutcome.fuse(temp1.getFuser(), temp2.getFuser(), theRefereeFunction);
		return new RandomSet(fusionOutcome,jointFrame);
	}
	
	public RandomSet fuseRS(RandomSet[] randomSets, RefereeFunctionDefault<finalPowerset> theRefereeFunction){
		
		/*
		 * Align frames of reference
		 */
		boolean firstFusion = true;
		finalRefereeFuserRTS_Powerset fusionOutcome = new finalRefereeFuserRTS_Powerset();
		FrameOfDiscernment jointFrame = this.getFrameOfDiscernment();
		for (RandomSet rs : randomSets){
			if(firstFusion){
				jointFrame = this.getFrameOfDiscernment().mergeFrames(rs.getFrameOfDiscernment());
				RandomSet temp1 = new RandomSet(jointFrame, this.getMassAssignment());
				RandomSet temp2 = new RandomSet(jointFrame, rs.getMassAssignment());
				fusionOutcome.fuse(temp1.getFuser(), temp2.getFuser(), theRefereeFunction);
				firstFusion = false;
			}
			else{
				jointFrame = jointFrame.mergeFrames(rs.getFrameOfDiscernment());	
				RandomSet temp2 = new RandomSet(jointFrame, rs.getMassAssignment());
				fusionOutcome.fuse(fusionOutcome, temp2.getFuser(), theRefereeFunction);

			}
		}
		return new RandomSet(fusionOutcome,jointFrame);
	}
	
	public double findDistance(RandomSet rs){
		return this.getFuser().findDistance(rs.getFuser());
	}
	public double Bel(String[] aProp){
		finalPowerset[] subsets = foD.generatePowerset();
		int index = foD.getSubsetValue(aProp);
		double bel = this.getFuser().Bel(subsets[index]);
		return bel;
	}
	public double Pl(String[] aProp){
		finalPowerset[] subsets = foD.generatePowerset();
		int index = foD.getSubsetValue(aProp);
		double bel = this.getFuser().Pl(subsets[index]);
		return bel;
	}
	public double Pbet(String[] aProp){
		finalPowerset[] subsets = foD.generatePowerset();
		int index = foD.getSubsetValue(aProp);
		double bel = this.getFuser().Pbet(subsets[index]);
		return bel;
	}
	public String[] makeDecisionBetP(){
		finalRefereeFuserRTS_Powerset thisFuser = this.getFuser();
		finalPowerset[] subsets = foD.generatePowerset();
		double maxpbet=0; finalPowerset decision = null;
		for (finalPowerset aProposition: subsets){
			double pbet = thisFuser.Pbet(aProposition);
			//ugly but likely fastest solution
			if (pbet>maxpbet && aProposition.cardinal()!=0){
				//do not do this for the empty set - need to double check pignistic probability calculation reg. empty sets
				maxpbet = pbet;
				decision = aProposition;
			}
		}
		if (decision == null){return null;}
		else{
		String[] result =	this.getFrameOfDiscernment().getFocalSet(decision);
		return result;
		}
	}
	
	public String[] makeDecisionPlaus(){
		finalRefereeFuserRTS_Powerset thisFuser = this.getFuser();
		finalPowerset[] subsets = foD.generatePowerset();
		double maxpbet=0; finalPowerset decision = null;
		for (finalPowerset aProposition: subsets){
			double pbet = thisFuser.Pl(aProposition);
			//ugly but likely fastest solution
			if (pbet>maxpbet && aProposition.cardinal()==1){
				//only do this for singletons
				maxpbet = pbet;
				decision = aProposition;
			}
		}
		if (decision == null){return null;}
		else{
		String[] result =	this.getFrameOfDiscernment().getFocalSet(decision);
		return result;
		}
	}
	public void saveToGson(String filename) throws IOException{
		StringBuilder sb = new StringBuilder();
		String name = sb.append("data/").append(filename).toString();
		Writer writer = new FileWriter(name);

        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
        gson.toJson(this, writer);

        writer.close();
	}
	public static RandomSet loadJson(String filepath) throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		Gson gson = new Gson();
		return gson.fromJson(new FileReader(filepath), RandomSet.class);
	}

	

}
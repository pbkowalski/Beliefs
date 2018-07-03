package executables;

import beliefs.RandomSet;
import beliefs.FrameOfDiscernment;
import beliefs.MassAssignment;



public class randomSetCreation {
	/*Create random sets as described in the murder case scenario and store them in a gson*/
	  public static void main(String[] args) throws Exception {
		  FrameOfDiscernment frameOfDisc1 = new FrameOfDiscernment(5);
		  
		  double[] probabilities1 = new double[32];
		  probabilities1[1]=1;
		  MassAssignment mass1 = new MassAssignment(probabilities1, frameOfDisc1);
		  mass1.setConfidence(0.3);
		  
		  double[] probabilities2 = new double[32];
		  probabilities2[4]=0.45;
		  probabilities2[6]=0.25;
		  probabilities2[7]=0.3;
		  MassAssignment mass2 = new MassAssignment(probabilities2, frameOfDisc1);
		  mass2.setConfidence(0.7);
		  
		  double[] probabilities4 = new double[32];
		  probabilities4[23]=0.125;
		  probabilities4[7]=0.125;
		  probabilities4[19]=0.125;
		  probabilities4[22]=0.125;
		  probabilities4[3]=0.125;
		  probabilities4[6]=0.125;
		  probabilities4[18]=0.125;
		  probabilities4[2]=0.125;
		  MassAssignment mass3 = new MassAssignment(probabilities4, frameOfDisc1);
		  mass3.setConfidence(0.8);
		  
		  double[] probabilities3 = new double[32];
		  probabilities3[8]=0.2;
		  probabilities3[24]=0.1;
		  probabilities3[25]=0.4;
		  probabilities3[31]=0.3;
		  MassAssignment mass4 = new MassAssignment(probabilities3, frameOfDisc1);
		  mass4.setConfidence(0.8);
		  
		  RandomSet randomSet1 = new RandomSet(frameOfDisc1, mass1);
		  RandomSet randomSet2 = new RandomSet(frameOfDisc1, mass2);
		  RandomSet randomSet3 = new RandomSet(frameOfDisc1, mass3);
		  RandomSet randomSet4 = new RandomSet(frameOfDisc1, mass4);

		  randomSet1.saveToGson("NewRandomSet1.json");
		  randomSet2.saveToGson("NewRandomSet2.json");
		  randomSet3.saveToGson("NewRandomSet3.json");
		  randomSet4.saveToGson("NewRandomSet4.json"); 

          System.out.println("Done!");



	  }
}

package executables;

import beliefs.RandomSet;
import beliefs.FrameOfDiscernment;
import beliefs.MassAssignment;



public class randomSetJsonBuilder {
	/*Create random sets as described in the murder case scenario and store them in a gson*/
	  public static void main(String[] args) throws Exception {
		  String[] set1 = {"A", "B", "C"};
		  FrameOfDiscernment frame = new FrameOfDiscernment(set1);
		  double[] mass = {0, 0.2, 0.6, 0.1, 0, 0, 0.1, 0 };
		  RandomSet randomSet1 = new RandomSet(frame, new MassAssignment(mass, frame));

		  randomSet1.saveToGson("initial.json");

          System.out.println("Done!");



	  }
}

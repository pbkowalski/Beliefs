package executables;

import RefereeToolbox.RFDempster_Powerset;
import beliefs.RandomSet;

public class murderCaseFusion {

	public static void main(String[] args) throws Exception {  		  
  
	  RandomSet randomSet1 = RandomSet.loadJson("data/NewRandomSet1.json");
	  RandomSet randomSet2 = RandomSet.loadJson("data/NewRandomSet2.json");
	  RandomSet randomSet3 = RandomSet.loadJson("data/NewRandomSet3.json");
	  RandomSet randomSet4 = RandomSet.loadJson("data/NewRandomSet4.json");
      RFDempster_Powerset RFDempster = new RFDempster_Powerset();
      RandomSet[] rs = {randomSet2, randomSet3, randomSet4};
      RandomSet fusionResult2 = randomSet1.fuseRS(rs, RFDempster);
      fusionResult2.getMassAssignment().print(1);
      System.out.println("---");
      fusionResult2.plausibilityMass(new String[]{"A"}).getMassAssignment().print(1);;
      System.out.print(fusionResult2.getFuser().state(1));
	}
}

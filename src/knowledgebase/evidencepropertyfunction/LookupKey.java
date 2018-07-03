package knowledgebase.evidencepropertyfunction;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;


public class LookupKey extends TreeMap<String, Object> implements Comparable<LookupKey>{
	
	LookupKeyComparator theComparator = new LookupKeyComparator();
	@Override
	public int compareTo(LookupKey o) {
		return theComparator.compare(this, o);
	}
	public class LookupKeyComparator implements Comparator<Map<String,Object>>{

		@Override
		public int compare(Map<String, Object> arg0, Map<String, Object> arg1) {
			Iterator<Entry<String, Object>> it = arg0.entrySet().iterator();
			Iterator<Entry<String, Object>> it1 = arg1.entrySet().iterator();
			while (it1.hasNext()){
				Entry<String, Object> currentMapping = it1.next();
				if (!arg0.containsKey(currentMapping.getKey())){
					throw new RuntimeException("lookup keys compared contain different element types");
				}
			}
			int strComp =0; int boolComp =0; int fltComp =0; int intComp =0;

			while (it.hasNext()){
				Entry<String, Object> currentMapping = it.next();
				//can be string, bool, double, int
				//TreeMap is sorted so all iterations should have the same order
				// First sort by strings, then by bools...ints, doubles
				if (arg1.containsKey(currentMapping.getKey())){
					if (currentMapping.getValue() instanceof String){
						String strLeft = (String) (currentMapping.getValue());
						String strRight = (String) (arg1.get(currentMapping.getKey()));
						int tmp = strLeft.compareToIgnoreCase(strRight);	//return 0 if equal, -int if left<right, +int if left>right
						if (strComp==0){
							strComp = tmp; //if more than one string is present in the lookup key only the first nonidentical one will affect ordering
						}
					}
					else if(currentMapping.getValue() instanceof Boolean){
						Boolean left = (Boolean) (currentMapping.getValue());
						Boolean right = (Boolean) (arg1.get(currentMapping.getKey()));
						int tmp = left.compareTo(right);
						if (boolComp==0){
							boolComp = tmp; //if more than one bool is present in the lookup key only the first nonidentical one will affect ordering
						}
					}
					else if(currentMapping.getValue() instanceof Integer){
						Integer left = (int) (currentMapping.getValue());
						Integer right = (int) (arg1.get(currentMapping.getKey()));
						int tmp = left.compareTo(right);
						if (intComp==0){
							intComp = tmp; //if more than one int is present in the lookup key only the first nonidentical one will affect ordering
						}
					}
					else if(currentMapping.getValue() instanceof Double){
						Double left = (double) (currentMapping.getValue());
						Double right = (double) (arg1.get(currentMapping.getKey()));
						int tmp = left.compareTo(right);
						if (fltComp==0){
							fltComp = tmp; //if more than one double is present in the lookup key only the first nonidentical one will affect ordering
						}
					}
					else{
						throw new RuntimeException("Unexpected lookup value");
					}
				}
				else{
					throw new RuntimeException("lookup keys compared contain different element types");
				}
			}
			if (strComp!=0)
				return strComp;
			else if (boolComp!=0)
				return boolComp;
			else if (intComp!=0)
				return intComp;
			else
				return fltComp;
		}
}

	
}
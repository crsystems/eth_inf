package u8a1;

import java.util.List;

public class BinarySearch<Key extends Comparable<Key>, Value> implements IBinarySearch<Key, Value>, IMeasure {
	
	public BinarySearch() {}
	
	public Value find(List<Unit<Key, Value>> haystack, Key needle){
		if(haystack.size() == 0){
			return null;
		}else if(haystack.size() > 1){

			List<Unit<Key, Value>> left = haystack.subList(0, (int) (haystack.size()/2));
			List<Unit<Key, Value>> right = haystack.subList((int) (haystack.size()/2), haystack.size());

			if(needle.compareTo(left.get(left.size()-1).key) <= 0){
				return this.find(left, needle);
			}else{
				return this.find(right, needle);
			}
		}else{
			if(needle.compareTo(haystack.get(0).key) == 0){
				return haystack.get(0).value;
			}else{
				return null;
			}
		}
	}


	public void setFactor(int factor){


	}


	public int getNumberofCalls(){

		return 0;
	}

}

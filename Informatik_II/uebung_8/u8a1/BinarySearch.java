package u8a1;

import java.util.List;

public class BinarySearch<Key extends Comparable<Key>, Value> implements IBinarySearch<Key, Value>, IMeasure {

	private int factor = 2;
	private int calls = 0;

	public BinarySearch() {}
	
	public Value find(List<Unit<Key, Value>> haystack, Key needle){
		
		this.calls++;

		if(factor == 2){
			if(haystack.size() == 0){
				return null;
			}else if(haystack.size() > 1){
				List<Unit<Key, Value>> left, right;
			
				left = haystack.subList(0, (int) (haystack.size()/2));
				right = haystack.subList((int) (haystack.size()/2), haystack.size());

				if(left.size() != 0 && needle.compareTo(left.get(left.size()-1).key) <= 0){
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
		}else if(factor == 3){
			if(haystack == null){
				return null;
			}else if(haystack.size() > 2){
				List<Unit<Key, Value>> left, right;

				left = haystack.subList(0, (int) (haystack.size()/3));
				right = haystack.subList((int) (haystack.size()/3), haystack.size());

				if(left.size() != 0 && needle.compareTo(left.get(left.size()-1).key) <= 0){
					return this.find(left, needle);
				}else{
					return this.find(right, needle);
				}
			}else{
				if(needle.compareTo(haystack.get(0).key) == 0){
					return haystack.get(0).value;
				}else if(haystack.size() == 2 && needle.compareTo(haystack.get(1).key) == 0){
					return haystack.get(1).value;
				}else{
					return null;
				}
			}
		}
		return null;
	}


	public void setFactor(int factor){
		this.factor = factor;

	}


	public int getNumberofCalls(){
		return calls;
	}

}

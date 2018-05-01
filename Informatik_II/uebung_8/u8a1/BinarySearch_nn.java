package u8a1;

import java.util.List;

public class BinarySearch<Key extends Comparable<Key>, Value> implements IBinarySearch<Key, Value>, IMeasure {
    
    int factor=2;
    int calls=0;
		    
		    /**
	 * Performs a binary search on the vector of {@link Unit}s.
	 * 
	 * @param needle
	 *            We are looking for something whose key equals this needle with
	 *            respect to the {@link Comparable} interface.
	 * @param haystack
	 *            The vector of {@link Unit}s where we are searching for the
	 *            needle. This vector must be sorted ascending with respect to
	 *            the keys and the {@link Comparable} interface.
	 * @return A thing from the haystack whose key equals the needle or null
	 *         if no such thing is in the haystack.
	 */
	public Value find(List<Unit<Key, Value>> haystack, Key needle){
	    
	   if(haystack.size()==0){
	       return null;
	   }
	   if(needle.compareTo(haystack.get(0).key) < 0 || needle.compareTo((haystack.get(haystack.size()-1)).key) > 0){
	       return null;
	   }
	   else{
	       Unit<Key, Value> thing = haystack.get((int)(haystack.size()/factor));
	       if(needle.compareTo(thing.key)==0){
	           calls++;
	           return thing.value;
	       }
	       else if(needle.compareTo(thing.key)<0){
	           List<Unit<Key, Value>> newlist = haystack.subList(0, (int)(haystack.size()/factor));
	           calls++;
	           return find(newlist,needle);
	       }
	       else{
               List<Unit<Key, Value>> newlist = haystack.subList((int)(haystack.size()/factor)+1,haystack.size());
               calls++;
	           return find(newlist,needle);
	       }
	   }
	}
	
	
	
	
	
	
		/** 
	 * Set the factor for the binary search.
	 * 
	 * A factor of two means that the search space is split into half-half in each recursion step.
	 * A factor of three means that the search space is split into one third and two thirds in each recursion step.
	 * In each case integer division is assumed, which means fractions are rounded down.
	 * 
	 * This method is called first after instantiation.
	 * 
	 * @param factor
	 *            an integer value
	 */
	public void setFactor(int factor){
	    factor = this.factor;
	}

	/**
	 * Retrieve the statistics.
	 * 
	 * @return the number of recursive calls of the binary search algorithm
	 *         performed since instantiation.
	 */
	public int getNumberofCalls(){
	    return calls;
	}
}

package u5a3;

import list.List;
import u5a1.Lists;
import u5a2.MutableLists;

public class SortedLists {
	/**
	 * Inserts a value into a sorted list so that the resulting list is still sorted.
	 * The sort order is ascending.
	 * 
	 * @param list a sorted list. 
	 * After the operation the state of this list is undefined.
	 * Don't use it any more. Use the returned list instead.
	 * @param value the value which is inserted into the list
	 * @return
	 */
	public static List insertSorted(List list, int value){
		if(list == null){
			return new List(value, null);
		}else{
			for(int i = 0; i < Lists.size(list); i++){
				if(
	}
	
	/**
	 * Sorts a list in ascending order.
	 * 
	 * @param list the list which is sorted.
	 * After the operation the state of this list is undefined.
	 * Don't use it any more. Use the returned list instead.
	 * @return the sorted variant of the given list
	 */
	public static List sort(List list)
	{
	}
}

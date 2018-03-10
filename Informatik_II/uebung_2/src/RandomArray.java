/**
 * An array with random values.
 */

import java.util.Random;
public class RandomArray {
	/**
	 * internal storage of the numbers
	 */
    private  int numbers[];

    /**
     * Create a new array with random values between 0 (inclusive) and 1000 (exclusive).
     * 
     * @param length the length of the new array.
     */
    public RandomArray(int length){
	Random rand = new Random();

	numbers = new int[length];
	for(int i = 0; i < length; i++){
		numbers[i] = rand.nextInt(1000);
	}
    }

    /**
     * Return string-representation of array.
     * 
     * Example: the string-representation of int array[] = {1,2,3} is '[1, 2, 3]'
     * 
     * @return a list of the values, separated by a comma and a space, and enclosed in squared brackets.
     */
    public String toString(){
	    String str_arr = new String("[");
	    
	    for(int i = 0; i < numbers.length; i++){
		    str_arr = str_arr + numbers[i];
		    if(i < (numbers.length - 1)){
			    str_arr = str_arr + ", ";
		    }
	    }
	    str_arr = str_arr + "]";
	    return str_arr;
    }

    /**
     * recursive sort of the array in descending order in place.
     * 
     * @param until value between 0 and length (inclusive) of {@link RandomArray#numbers}. <br>
     * When the function returns the <em>until</em> largest values of {@link RandomArray#numbers} 
     * are placed in the first <em>until</em> positions of the array and sorted in descending order.
     */
    private void recursiveSort(int until){
	    if(numbers.length < 1){
		    return;
	    }

	    if(until > 1){
		    recursiveSort(until-1);
	    }

	    int max_index = until-1;
	    for(int i = until; i < numbers.length; i++){
		    if(numbers[i] > numbers[until-1] && numbers[i] > numbers[max_index]){
			    max_index = i;
		    }
	    }

	    int swap = numbers[max_index];
	    numbers[max_index] = numbers[until-1];
	    numbers[until-1] = swap;
    }

    /**
     * sort the array in descending order in place.
     */
    public void sort()
    {
    	recursiveSort(numbers.length);        
    }
}


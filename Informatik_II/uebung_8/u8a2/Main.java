import u8a2.*;
import java.util.ArrayList;

public class Main{


	public static void main(String[] args){
		ArrayList<Integer> vals = new ArrayList<Integer>();
		ArrayList<Integer> wghts = new ArrayList<Integer>();

		int[] values = {19839, 27392, 9718, 32386, 8611, 6008, 10445, 11465, 20456, 23949, 
                                   23633, 19221, 2294, 31024, 28707, 28685, 3239, 2038, 42, 529};

		int[] weights = {2347, 26305, 18656, 12066, 25924, 27267, 18074, 3601, 5965, 5762,
                                                     27550, 29598, 24984, 29845, 27854, 20923, 25762, 31094, 22961, 25805};


		for(int i = 0; i < values.length; i++){
			vals.add(values[i]);
			wghts.add(weights[i]);
		}

		IRucksack back = new BacktrackingRucksack();
		IRucksack brute = new BruteForceRucksack();

		System.out.println(brute.findBest(vals, wghts, 100000).toString());
		System.out.println(back.findBest(vals, wghts, 100000).toString());
	}
}

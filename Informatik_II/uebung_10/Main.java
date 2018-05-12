import u10a1.*;
import java.util.ArrayList;
import java.util.Random;

//Time needed for a few List sizes
//
//100		0.030326206 ms
//200		0.061527858 ms
//400		0.135509079 ms
//800		0.298728124 ms
//1600		0.673013768 ms
//3200		1.620392625 ms
//6400		3.931939389 ms
//12800		10.81897016 ms
//23600		33.39323356 ms
//51200		117.2747327 ms



public class Main {
	public static void main(String[] args){
		MergeSort<Integer> sort = new MergeSort<Integer>();

		Random rand = new Random();
                int size;
                ArrayList<Integer> in;
		ArrayList<Long> times;
		ArrayList<ArrayList<Long>> test = new ArrayList<ArrayList<Long>>();
                ArrayList<Double> avg = new ArrayList<Double>();

		Long before, after;

		for(int i = 0; i < 1000; i++){
			times = new ArrayList<Long>();

			for(size = 100; size < 102400; size = size*2){
				in = new ArrayList<Integer>();
				for (int l = 0; l < size; l++) {
                		        in.add(rand.nextInt());
                		}
				before = System.nanoTime();
                		ArrayList<Integer> result = sort.sort(in);
				after = System.nanoTime();
				times.add(after-before);
			}
			test.add(times);
		}

		//as we calculate the average of 1000 values ignoring the biggest and smallest should not make a difference
		for(int k = 0; k < test.get(0).size(); k++){
			
			Long tmp = (long) 0;

			for(int j = 0; j < test.size(); j++){
				tmp += test.get(j).get(k);
			}

			avg.add(((Double) (tmp/((double) test.size())))/1000000.0);
		}





		for(int i = 0; i < avg.size(); i++){
			System.out.println(avg.get(i));
		}
	}

}


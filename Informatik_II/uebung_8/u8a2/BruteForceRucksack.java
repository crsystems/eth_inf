package u8a2;

import java.util.ArrayList;

public class BruteForceRucksack implements IRucksack{
	public BruteForceRucksack() {};

	public Selection findBest(ArrayList<Integer> values, ArrayList<Integer> weights, int maxWeight){
		Selection best = new Selection(values.size());
		Selection cur = new Selection(values.size());

		int max_weight = maxWeight, max_value = 0;


		int max_bits = (int)Math.pow(2, values.size()) - 1;

		for(int i = 0; i < max_bits; i++){
			cur.setBits(i);

			int cur_weight = 0, cur_value = 0;

			for(int k = 0; k < cur.size(); k++){
				if(cur.get(k)){
					cur_weight += weights.get(k);
					cur_value += values.get(k);
				}
			}

			if(cur_weight <= maxWeight && cur_value > max_value){
				max_value = cur_value;
				max_weight = cur_weight;
				best.setBits(i);
			}
		}
		return best;
	}


}

package u8a2;

import java.util.ArrayList;

public class BacktrackingRucksack implements IRucksack{
	public BacktrackingRucksack() {};

	public Selection findBest(ArrayList<Integer> values, ArrayList<Integer> weights, int maxWeight){
		
		int best_index = -1, best_value = 0, weight = 0;
		for(int i = 0; i < values.size(); i++){
			if(values.get(i) > best_value && values.get(i) <= maxWeight){
				best_index = i;
				best_value = values.get(i);
				weight = weights.get(i);
			}
		}

		if(best_index == -1){
			return new Selection(values.size());
		}

		values.remove(best_index);
		weights.remove(best_index);

		Selection select = this.findBest(values, weights, maxWeight - weight);
		Selection best = new Selection(select.size() + 1);
		for(int k = 0; k < select.size() + 1; k++){
			if(k < best_index){
				best.set(k, select.get(k));
			}else if( k == best_index){
				best.set(k, true);
			}else{
				best.set(k, select.get(k-1));
			}
		}	
		return best;
	}
}

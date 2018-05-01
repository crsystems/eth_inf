//Zu Aufgabe 4.)
//Der Backtracking Algorithmus brauch viel länger, als Bruteforcing. Das hängt damit zusammen, dass es kein eindeutiges 
//Entscheidungskriterium gibt, ob eine gefundene Möglichkeit gut ist oder nicht. 
//Das bedeutet, dass der Backtracking Algorithmus auch alle Möglichkeiten vergleichen muss und daher auch ein wenig "brute forcet"
//Allerdings findet der Backtracking Algorithmus auch immer die optimale Lösung. Er vergleicht die gefundenen Wege ja auch, und wählt
//die beste.

package u8a2;

import java.util.ArrayList;

public class BacktrackingRucksack implements IRucksack{
	public BacktrackingRucksack() {};

	public Selection findBest(ArrayList<Integer> values, ArrayList<Integer> weights, int maxWeight){
		if(values.size() == 1){
			Selection one = new Selection(1);
			if(weights.get(0) <= maxWeight){
				one.set(0, true);
			}
			return one;
		}

		Selection select = null;
		int index = 0, best_val = 0;
		for(int i = 0; i < values.size(); i++){
			if(weights.get(i) > maxWeight){
				continue;
			}
			
			ArrayList<Integer> cur_vals = new ArrayList<Integer>(values);
			ArrayList<Integer> cur_wghts = new ArrayList<Integer>(weights);
			cur_vals.remove(i);
			cur_wghts.remove(i);
		
			
			Selection tmp = this.findBest(cur_vals, cur_wghts, (maxWeight - weights.get(i))); 
			
			if(best_val < (tmp.sum(cur_vals) + values.get(i))){
				best_val = tmp.sum(cur_vals) + values.get(i);
				index = i;
				select = tmp;
			}
		}

		//if backtracking didn't find anything and there are more than 1 values we end up here with select == null
		//that means, that the maxWeight is to small alltogether, so we return a selection with all bits set to zero
		if(select == null){
			return new Selection(values.size());
		}


		Selection best = new Selection(select.size() + 1);

		for(int k = 0; k < best.size(); k++){
			if(k < index){
				best.set(k, select.get(k));
			}else if(k == index){
				best.set(k, true);
			}else{
				best.set(k, select.get(k-1));
			}
		}

		return best;
	}
}

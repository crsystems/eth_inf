package u12a2;

import java.util.ArrayList;

public class MergeSortParallel<T extends Comparable<T>> extends Thread{

	ArrayList<T> sorted = new ArrayList<T>();
	ArrayList<T> unsorted = new ArrayList<T>();

	public MergeSort(ArrayList<T> unsorted){
		this.unsorted = unsorted;
	}
	
	public ArrayList<T> getSorted() {
		return this.sorted;
	}
	
	public ArrayList<T> sort(ArrayList<T> items){
		if(items == null){
			return null;
		}

		if(items.size() == 1 || items.size() == 0){
			return items;
		}

		ArrayList<T> left = new ArrayList<T>();
		left.addAll(items.subList(0, ((int) (items.size() / 2))));
		
		ArrayList<T> right = new ArrayList<T>();
		right.addAll(items.subList(((int) (items.size() / 2)), items.size()));
		
		return this.merge(this.sort(left), this.sort(right));
	}


	private ArrayList<T> merge(ArrayList<T> left, ArrayList<T> right){
		ArrayList<T> merged = new ArrayList<T>();

		while(left.size() != 0 || right.size() != 0){
			if(left.size() != 0 && right.size() != 0){
				if(left.get(0).compareTo(right.get(0)) > 0){
					merged.add(right.get(0));
					right.remove(0);
				}else{
					merged.add(left.get(0));
					left.remove(0);
				}
			}else if(left.size() == 0) {
				merged.add(right.get(0));
				right.remove(0);
			}else {
				merged.add(left.get(0));
				left.remove(0);
			}
		}
		return merged;
	}

	public void run(){
		this.sorted = this.sort(this.unsorted);
	}
}



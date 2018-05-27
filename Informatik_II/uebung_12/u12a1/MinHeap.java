package u12a1;

import java.util.ArrayList;

public class MinHeap<T extends Comparable<T>> {

	private ArrayList<T> heap;

	public MinHeap(){
		this.heap = new ArrayList<T>();
		this.heap.add(null);
	}

	public void heapify(ArrayList<T> array){
		heap.addAll(array);

		T tmp = heap.get(1);
		int tmp_id = 1;

		for(int i = 1; i < heap.size(); i++){
			if(heap.get(i).compareTo(tmp) < 0){
				tmp = heap.get(i);
				tmp_id = i;
			}
		}

		heap.set(tmp_id, heap.get(1));
		heap.set(1, tmp);

		for(int i = 2; i < heap.size(); i++){
			if(heap.get(i).compareTo(heap.get(i/2)) < 0){
				int k = i;
				while(heap.get(k).compareTo(heap.get(k/2)) < 0){
					tmp = heap.get(k);

					heap.set(k, heap.get(k/2));
					heap.set(k/2, tmp);
					
					k = k/2;
				}
			}
		}
	}

	public void insert(T value){



	}
	//DELETE!!
	public ArrayList<T> getHeap(){
		return heap;
	}

	public int getSize(){
		return heap.size()-1;
	}

	public T removeRoot(){
		
		T tmp = null;
		T root = heap.set(1, heap.get(heap.size()-1));
		
		heap.remove(heap.size()-1);
		
		for(int i = 2; i < heap.size(); i++){
			if(heap.get(i).compareTo(heap.get(i/2)) < 0){
				int k = i;
				while(k > 1 && heap.get(k).compareTo(heap.get(k/2)) < 0){
					tmp = heap.get(k);

					heap.set(k, heap.get(k/2));
					heap.set(k/2, tmp);
					
					k = k/2;
				}
			}

		}
		return root;
	}
}

package u12a1;

import java.util.ArrayList;

public class HeapSort<T extends Comparable<T>> implements ISort<T>{

	public void sort(ArrayList<T> items){
		MinHeap<T> heap = new MinHeap<T>();

		if(items == null || items.size() == 0){
			return;
		}

		heap.heapify(items);

		for(int i = 0; i < items.size(); i++){
			items.set(i, heap.removeRoot());
		}
	}
}

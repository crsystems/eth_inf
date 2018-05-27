import java.util.ArrayList;
import u12a1.*;

public class Main{
	public static void main(String[] args){
		MinHeap<Integer> heap = new MinHeap<Integer>();

		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(4);
		arr.add(1);
		arr.add(6);
		arr.add(5);
		arr.add(3);
		arr.add(2);

		heap.heapify(arr);

		ArrayList<Integer> sort = heap.getHeap();

		for(int i = 0; i < sort.size(); i++){
			System.out.println("- " + sort.get(i));
		}

		System.out.println("\n\n\n");

		while(heap.getSize() > 0){
			System.out.println("+ " + heap.removeRoot());
		}

		System.out.println("\n\n\n");

		sort = heap.getHeap();

		for(int i = 0; i < sort.size(); i++){
			System.out.println("- " + sort.get(i));
		}

	}
}

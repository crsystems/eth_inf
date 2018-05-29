package u12a2;

import java.util.ArrayList;
import java.util.Collection;

public class MergeSort implements ISort{

	private int numThreads = 1;

	public MergeSort(int numThreads){
		this.numThreads = numThreads;
	}

	public int[] sort(int[] items){
		ArrayList<Integer> unsorted = new ArrayList<Integer>();

		for(int i = 0; i < items.length; i++){
			unsorted.add(items[i]);
		}

		if(numThreads > unsorted.size()){
			while(numThreads > unsorted.size()){
				numThreads--;
			}
		}

		ArrayList<ArrayList<Integer>> sublists = new ArrayList<ArrayList<Integer>>();

		ArrayList<ArrayList<Integer>> sorted_sublists = new ArrayList<ArrayList<Integer>>();

		ArrayList<MergeSortParallel<Integer>> threads = new ArrayList<MergeSortParallel<Integer>>();

		if(unsorted.size()%numThreads == 0){
			for(int i = 0; i < numThreads; i++){
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				tmp.addAll((Collection<Integer>) unsorted.subList(i*(unsorted.size()/numThreads), (i+1)*(unsorted.size()/numThreads)));
				sublists.add(tmp);
			}
		}else{
			for(int i = 0; i < numThreads-1; i++){
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				tmp.addAll((Collection<Integer>) unsorted.subList(i*(unsorted.size()/numThreads), (i+1)*(unsorted.size()/numThreads)));
				sublists.add(tmp);
			}
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp.addAll((Collection<Integer>) unsorted.subList((numThreads-1)*(unsorted.size()/numThreads), unsorted.size()));
			sublists.add(tmp);
		}


		for(int i = 0; i < numThreads; i++){
			MergeSortParallel<Integer> tmp = new MergeSortParallel<Integer>();
			tmp.setUnsorted(sublists.get(i));
			threads.add(tmp);
		}

		for(int i = 0; i < numThreads; i++){
			threads.get(i).start();
		}

		for(int i = 0; i < numThreads; i++){
			try{
				threads.get(i).join();
			}catch(Exception e){
				return null;
			}
		}

		for(int i = 0; i < numThreads; i++){
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp.addAll((Collection) threads.get(i).getSorted());
			sorted_sublists.add(tmp);
		}

		while(sorted_sublists.size() > 1){
			sorted_sublists.set(0, threads.get(0).merge(sorted_sublists.get(0), sorted_sublists.get(1)));
			sorted_sublists.remove(1);
		}

		
		int srt[] = new int[sorted_sublists.get(0).size()];
	       	for(int i = 0; i < srt.length; i++){
			srt[i] = sorted_sublists.get(0).get(i);
		}

		return srt;	
	}
}

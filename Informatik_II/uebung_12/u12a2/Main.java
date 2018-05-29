import java.util.ArrayList;
import java.util.Random;
import u12a2.*;

/*

1 Thread: 	51974.507062 	ms
2 Threads: 	39071.715082 	ms
3 Threads: 	46928.310669 	ms
4 Threads: 	67378.21482	ms
5 Threads: 	74843.277646	ms
6 Threads: 	91517.513799	ms
7 Threads: 	106367.298233	ms
8 Threads: 	123096.098781	ms

Der Zeitbedarf wird zunächst weniger, steigt dann aber wieder. Das kann ich mir daher erklären, dass nur das eigentliche Sortieren,
der aufgespaltenen Listen parallel ausgeführt wird. Das Zusammenführen zu einer großen wird wieder seriell von einem Thread 
übernommen. Je mehr Threads man hat, desto größer ist die Zusammenführarbeit, da mehr Sublisten erstellt werden. Der Sweetspot für 
eine Million werte scheint auf meinem System bei 2 Threads zu liegen.

*/




public class Main {
	public static void main(String[] args) throws InterruptedException{
		
		ArrayList<Long> times = new ArrayList<Long>();

		Long before, after;

		for(int i = 1; i <= 8; i++){

			before = System.nanoTime();
			
			MergeSort sort = new MergeSort(i);

			int arr[] = new int[1000000];

			Random rand = new Random();
	
			for(int k = 0; k < 1000000; k++){
				arr[k] = rand.nextInt(100000);
			}

			arr = sort.sort(arr);

			after = System.nanoTime();

			times.add(after-before);
		}

		for(int i = 0; i < times.size(); i++){
			System.out.println(i+1 + " Threads: " + (double) times.get(i)/1000000.0);
		}
	}
}

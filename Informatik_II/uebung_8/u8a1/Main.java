package u8a1;

import java.util.ArrayList;


public class Main {
	/**
	 * Die beste Strategie ist abhängig vom key, nach dem gesucht wird.
	 * Wenn dieser im unteren Drittel liegt, ist die ein drittel / zwei drittel Teilung besser.
	 * Andernfalls ist halbieren schneller oder gleich schnell.
	 */
	public static void main(String[] args){

		ArrayList<Unit<Integer, String>> test = new ArrayList<Unit<Integer, String>>();

		int keys[] = {3, 7, 17, 25, 33, 47, 56, 62, 65, 66, 68, 70, 78, 89, 92};
		for(int i = 0; i < keys.length; i++){
			test.add(new Unit<Integer, String>(keys[i], Integer.toString(keys[i])));
		}

		BinarySearch<Integer, String> two = new BinarySearch<Integer, String>();
		two.setFactor(2);

		BinarySearch<Integer, String> three = new BinarySearch<Integer, String>();
		three.setFactor(3);


		for(int i = 0; i < test.size(); i++){
			two.find(test, test.get(i).key);
			three.find(test, test.get(i).key);
		}

		System.out.println("All numbers in array:\nHalfs: " + two.getNumberofCalls()/test.size() + "\nThirds: " + three.getNumberofCalls()/test.size() + "\n\n");


		two = new BinarySearch<Integer, String>();
		two.setFactor(2);

		three = new BinarySearch<Integer, String>();
		three.setFactor(3);

		for(int i = 0; i < 100; i++){
			two.find(test, i);
			three.find(test, i);
		}

		System.out.println("All numbers from 0 to 99\nHalfs: " + two.getNumberofCalls()/100 + "\nThirds: " + three.getNumberofCalls()/100 + "\n\n");


		two = new BinarySearch<Integer, String>();
		two.setFactor(2);

		three = new BinarySearch<Integer, String>();
		three.setFactor(3);

		for(int i = 0; i < 10; i++){
			two.find(test, i);
			three.find(test, i);
		}
		
		System.out.println("All numbers from 0 to 9\nHalfs: " + two.getNumberofCalls()/10 + "\nThirds: " + three.getNumberofCalls()/10 + "\n\n"); 
	}
}

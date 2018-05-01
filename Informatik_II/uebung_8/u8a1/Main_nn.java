import u8a1.*;

import java.util.List;
import java.util.ArrayList;

/**
 * Main class of the Java program. 
 * 
 * 
 * In diesen Beispielen unterscheiden sich die beiden Strategien garnicht, also ist der generelle Unterschied auch eher klein. 
 * Die Faktor 3 Strategie ist sinnvoller, falls das gesucht im 1. Drittel der List vorkommt, andernfalls wird es genau so lang oder manchmal sogar noch laegjnger dauern als die Faktor 2 Strategie.
 */

public class Main {

    public static void main(String[] args) {
        
        // we print a heading and make it bigger using HTML formatting
        System.out.println("<h4>-- Binaere Suche --</h4>");
        
        ArrayList<Unit<Integer, String>> test = new ArrayList<Unit<Integer, String>>();

		int keys[] = {3, 7, 17, 25, 33, 47, 56, 62, 65, 66, 68, 70, 78, 89, 92};
		
		for(int i = 0; i < keys.length; i++){
			test.add(new Unit<Integer, String>(keys[i], Integer.toString(keys[i])));
		}
		
		
		BinarySearch<Integer, String> searchf2 = new BinarySearch<Integer, String>();
		BinarySearch<Integer, String> searchf3 = new BinarySearch<Integer, String>();
		searchf3.setFactor(3);
		
		for(int i = 0; i < keys.length; i++){
		    searchf2.find(test,keys[i]);
		    searchf3.find(test,keys[i]);
		}
		int antwort1 = (int)(searchf2.getNumberofCalls()/keys.length);
		int antwort2 = (int)(searchf3.getNumberofCalls()/keys.length);
		
		searchf2 = new BinarySearch<Integer, String>();
		searchf3 = new BinarySearch<Integer, String>();
		
		for(int i = 0; i < 100; i++){
		    searchf2.find(test,i);
		    searchf3.find(test,i);
		}
		int antwort3 = (int)(searchf2.getNumberofCalls()/100);
		int antwort4 = (int)(searchf3.getNumberofCalls()/100);
		
		searchf2 = new BinarySearch<Integer, String>();
		searchf3 = new BinarySearch<Integer, String>();
        
        for(int i = 0; i < 10; i++){
		    searchf2.find(test,i);
		    searchf3.find(test,i);
		}
		int antwort5 = (int)(searchf2.getNumberofCalls()/10);
		int antwort6 = (int)(searchf3.getNumberofCalls()/10);
		
        System.out.println("Wie gross ist jeweils die mittlere Anzahl von rekursiven Aufrufen fuer die Strategien aus 2. und 3., wenn");
        System.out.println("   - man nach allen vorhandenen Zahlen sucht? Faktor 2:  " + antwort1 + "  Faktor 3:  " + antwort2);
        System.out.println("   - man nach allen Zahlen von 0 bis 99 sucht? Faktor 2:  " + antwort3 + "  Faktor 3:  " + antwort4);
        System.out.println("   - man nach allen Zahlen von 0 bis 9 sucht? Faktor 2:  " + antwort5 + "  Faktor 3:  " + antwort6);
    }
}

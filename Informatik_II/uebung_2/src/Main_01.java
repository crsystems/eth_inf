/**
 * Main class of the Java program. 
 * 
 */

public class Main_01 {

    public static void main(String[] args) {
        
        // we print a heading and make it bigger using HTML formatting
        System.out.println("<h4>-- RandomArray.java --</h4>");
	RandomArray rand = new RandomArray(30);
	System.out.println(rand.toString());
	rand.sort();
	System.out.println(rand.toString());
    }
}


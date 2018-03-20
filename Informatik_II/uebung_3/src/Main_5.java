/**
 * Main class of the Java program. 
 * 
 */
public class Main_5 {
    public static void main(String[] args) throws ParseException{
        System.out.println("-- Syntaxchecker --");
        /* you can make function calls from here*/

	String test1 = "A";
	String test2 = "-";
	String test3 = "A(B(C,-),-))";
	String test4 = "A(B(C))";

	System.out.println("A");
	KD.parse(test1);

	System.out.println("\n\nA(-,-)");
	KD.parse("A(-,-)");

	System.out.println("\n\nA(B(C,-),-))");
	KD.parse(test3);

	System.out.println("\n\nA(B(C))");
	KD.parse(test4);

	System.out.println("\n\n-");
	KD.parse(test2);

    }
}


/**
 * Main class of the Java program. 
 * 
 */

//import Mult.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("-- Ancient Egyptian multiplication --");
        System.out.println("Valid 2 * 2 = " + Mult.mult(2,2));
	System.out.println("Invalid 22 * (-2) = " + Mult.mult(22,-2));
    }
}


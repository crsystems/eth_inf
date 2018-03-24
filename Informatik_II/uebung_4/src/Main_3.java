/**
 * Main class of the Java program. 
 * 
 */

public class Main_3 {

    public static void main(String[] args) {
        
	if(Integer.parseInt(args[0]) == 0){
        	System.out.println(new IterativeAckermann().A(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
	}else if(Integer.parseInt(args[0]) == 1){
		System.out.println(new RecursiveAckermann().A(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
	}
    }
}


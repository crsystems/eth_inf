/**
 * Ancient Egyptian multiplication
 * @author Alexander Bernauer (bernauer@inf.ethz.ch)
 * @version 1.0
 */
public class Mult {
    private static int f(int a, int b) {
        if (b==1) return a;
        if (b%2 == 0) return f(2*a, b/2);
        	else return a + f(2*a, (b-1)/2);
    }
    
/**
 * This function implements the ancient Egyptian multiplication.
 *
 * @param a	Positive and non zero integer argument one
 * @param b	Positive and non zero integer argument two
 * @return a*b  The return value is the product of a and b
 * @throws IllegalArgumentException If a or b do not match the requirements
 */
    public static int mult(int a, int b) {
	if(a < 1 || b < 1){
		throw new IllegalArgumentException();
	}else{
        	return f(a, b);
	}
    }
}

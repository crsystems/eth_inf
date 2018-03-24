/**
 * Public tests.
 * 
 * All tests in the folder "test" are executed 
 * when the "Test" action is invoked.
 * 
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for the iterative Ackermann implementation.
 * 
 * Compares results with recursive implementation.
 */
public class PublicTest_3 {

    public static void testCase(int n, int m)
	{
		Assert.assertEquals(new RecursiveAckermann().A(n,m), new IterativeAckermann().A(n,m));
	}
	
	@Test public void t00() { testCase(0,0); }
	@Test public void t10() { testCase(1,0); }
	@Test public void t01() { testCase(0,1); }
	@Test public void t11() { testCase(1,1); }
	@Test public void t12() { testCase(1,2); }
	@Test public void t22() { testCase(2,2); }
}


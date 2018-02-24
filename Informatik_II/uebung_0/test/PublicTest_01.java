/**
 * Public tests.
 * 
 * All tests in the folder "test" are executed 
 * when the "Test" action is invoked.
 * 
 */

import org.junit.Assert;
import org.junit.Test;


public class PublicTest_01 {

      @Test
    public final void test1() {
        Assert.assertEquals(-1, Signum.signum(-10));
    }
    
    // Create more tests in new test methods.
    
    @Test
    public final void test2() {
        Assert.assertEquals(1, Signum.signum(33)); 
    }
}

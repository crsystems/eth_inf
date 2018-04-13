package u6a4;

import org.junit.Test;
import u6a2.IStack;

/**
 * Runtime tests for the two implemented dynamically growing stacks.
 */
public class Tests_03 {
	
	/**
	* Tests whether chunks are removed correctly. 
	*/
	@Test public void numberOfChunks()
	{
		ChunkedStack stack = new u6a4.ChunkedStack();
		Assert.assertEquals("number of chunks is wrong", 1, stack.numberOfChunks());
		for (int i=0; i<200; i++) {
			stack.push(i);
		}
		Assert.assertEquals("size is wrong", 200, stack.size());
		Assert.assertEquals("number of chunks is wrong", 1, stack.numberOfChunks());
		stack.push(200);
		Assert.assertEquals("size is wrong", 201, stack.size());
		Assert.assertEquals("number of chunks is wrong", 2, stack.numberOfChunks());
		stack.pop();
		Assert.assertEquals("size is wrong", 200, stack.size());
		Assert.assertEquals("number of chunks is wrong", 1, stack.numberOfChunks());
		for (int i=0; i<200; i++) {
			stack.pop();
		}
		Assert.assertEquals("size is wrong", 0, stack.size());
		Assert.assertEquals("number of chunks is wrong", 1, stack.numberOfChunks());
	}

	/**
	 * Compares and prints runtimes for push- and pop-Operations on u6a2.Stack and u6a4.ChunkedStack.
	 */
	@Test public void runtimes()
	{
		final int START = 10000;
		final int MAX = 2000000;

		IStack listStack = new u6a2.ListStack();
		IStack chunkedStack = new u6a4.ChunkedStack();

		long listTotal = 0;
		long chunkedTotal = 0;
		
		System.out.println("Size\tListStack\tChunkedStack");
		
		for (int max = START; max < MAX; max += 10000) {
			long time1 = System.currentTimeMillis();
			
			int sum = 0;
			for (int i = 0; i < max; i++) listStack.push(1);
			while (!listStack.empty()) sum += listStack.pop();
			
			long time2 = System.currentTimeMillis();

			sum = 0;
			for (int i = 0; i < max; i++) chunkedStack.push(1);
			while (!chunkedStack.empty()) sum += chunkedStack.pop();
			
			long time3 = System.currentTimeMillis();

			System.out.println(max + "\t" + (time2 - time1) + "\t" + (time3 - time2));
			
			listTotal += (time2 - time1);
			chunkedTotal += (time3 - time2);
		}		

		System.out.println("Total ListStack: " + listTotal + "ms.");
		System.out.println("Total ChunkedStack: " + chunkedTotal + "ms.");
	}
}

public class IterativeAckermann {
	/**
	 * Stack-based iterative implementation of the Ackermann function.
	 * 
	 * @param n parameter n
	 * @param m parameter m
	 * @return Ackermann(n,m)
	 */
	public int A(int n, int m){

		Stack stack = new Stack(2);

		stack.push(n);
		stack.push(m);

		while(stack.size() >= 2){
			//System.out.println("Before : " + stack.toString());
			int m_new = stack.pop();
			int n_new = stack.pop();
			

			if(n_new == 0){
				stack.push(m_new+1);
			}else if(m_new == 0){
				stack.push(n_new-1);
				stack.push(1);
			}else{
				stack.push(n_new-1);
				stack.push(n_new);
				stack.push(m_new-1);
			}

			//System.out.println("After: " + stack.toString());
		}

		return stack.pop();
	}
}

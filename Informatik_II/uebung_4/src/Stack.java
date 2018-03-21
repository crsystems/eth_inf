import java.util.EmptyStackException;

/**
 * Dynamically growing stack.
 */
public class Stack {
	private int[] buffer;
	private int size;
	private int capacity;

	/**
	 * Creates a new stack
	 * 
	 * @param capacity the initial capacity of the stack
	 */
	public Stack(int capacity){
		if(capacity <= 0){
			return;
		}

		this.buffer = new int[capacity];
		this.capacity = capacity;
		this.size = 0;
	}

    	/**
	 * Converts stack to a string representation.
	 * 
	 * @return A ", "-separated list of the numbers, enclosed in square brackets. Bottom numbers come first. 
	 */
	public String toString(){
		StringBuffer str = new StringBuffer();
		str.append('[');
		for(int i = 0; i < size; i++){
			str.append(buffer[i]);

			if((i+1) < size){
				str.append(", ");
			}
		}
		str.append(']');
		return str.toString();
	}
	
	/**
	 * Doubles the capacity of the stack.
	 * 
	 * Copies all objects to a new buffer of doubled size.
	 */
	private void grow(){
		int[] swap = new int[capacity*2];
		for(int i = 0; i < size; i++){
			swap[i] = buffer[i];
		}

		this.buffer = swap;
		capacity = capacity*2;
	}
	
	/**
	 * Pushes a number onto the top of this stack.
	 * 
	 * Grows the stack if necessary.
	 * 
	 * @param number the number to be pushed onto this stack. 
	 */
	public void push(int number){
		if(this.size < this.capacity){
			buffer[size] = number;
			size++;
		}else{
			this.grow();
			buffer[size] = number;
			size++;
		}
	}
	
	/**
	 * Removes the number at the top of this stack and returns it as the value of this function. 
	 * 
	 * @return The number at the top of this stack
	 * @throws EmptyStackException if this stack is empty
	 */
	public int pop() throws EmptyStackException{
		if(size == 0){
			throw new EmptyStackException();
		}
		
		int val = buffer[size-1];
		int[] buf = new int[capacity];

		for(int i = 0; i < (size-1); i++){
			buf[i] = buffer[i];
		}

		this.buffer = buf;
		size--;
		return val;
	}
	
	/**
	 * Looks at the number at the top of this stack without removing it from the stack. 
	 * 
	 * @return the number at the top of this stack
	 * @throws EmptyStackException if this stack is empty
	 */
	public int peek() throws EmptyStackException{
		if(size > 0){
			return buffer[size-1];
		}else{
			throw new EmptyStackException();
		}
	}

	/**
	 * Tests if this stack is empty. 
	 * 
	 * @return true if and only if this stack contains no items; false otherwise.
	 */
	public boolean empty(){
		if(size == 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Get the size of this stack.
	 * 
	 * @return the current number of items on this stack
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Get the capacity of this stack.
	 *    
	 * @return the maximum number of items this stack can hold without having to grow
	 */
	public int capacity(){
		return this.capacity;
	}
}

package u6a4;

import java.util.EmptyStackException;

public class ChunkedStack implements u6a2.IStack {
	/**
	 * Linked list of chunks
	 */
	private ChunkList chunks;
	
	/**
	 * the number of slots of the last chunk which are used. 
	 */
	private int used;
	
	public String toString()
	{
		if (empty()) return "[]";
		
		// First Chunk, may not be full
		StringBuffer buf = new StringBuffer("[");
		for(int i = used; i>0;i--)
			buf.append(chunks.buffer[i-1]).append(", ");
		
		// Following full Chunks
		for(ChunkList iter = chunks.next; iter != null; iter = iter.next )
		{
			for (int i=ChunkList.chunkSize; i>0; i--) {
				buf.append(iter.buffer[i-1]).append(", ");
			}
		}
		
		buf.delete(buf.length()-2, buf.length());
		buf.append("]");
		return buf.toString();
	}
		
	public ChunkedStack()
	{
		chunks = new ChunkList();
		used = 0;
	}

	public boolean empty() 
	{
		// TODO
		return false;
	}

	public int peek() throws EmptyStackException 
	{
		// TODO
		return -1;
	}

	public int pop() throws EmptyStackException 
	{
		// TODO
		return -1;
	}

	public void push(int number) 
	{		
		// TODO
		return;
	}

	public int size() 
	{
		// TODO
		return -1;
	}
	
	/**
    * Return the number of chunks in the {@link ChunkList}
    */
	public int numberOfChunks() {
		//todo
		return -1;
	}
}

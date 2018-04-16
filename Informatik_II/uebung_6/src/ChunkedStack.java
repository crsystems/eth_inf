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
		if(used == 0 && this.numberOfChunks() == 1){
		    return true;
		}
		return false;
	}

	public int peek() throws EmptyStackException 
	{
	    if(this.empty())
	        throw new EmptyStackException();
	    return chunks.buffer[used-1];
	}

	public int pop() throws EmptyStackException 
	{
	    if(this.empty())
	        throw new EmptyStackException();
	    if(used == 1){
	        int tmp = chunks.buffer[0];
	        if(this.numberOfChunks() > 1){
	            chunks = chunks.removeChunk();
	            used = chunks.chunkSize;
	        }else{
	            used--;
	        }
	        return tmp;
	    }else{
	        used--;
	        return chunks.buffer[used];
	    }
	}

	public void push(int number) 
	{		
		if(used < chunks.chunkSize){
		    chunks.buffer[used] = number;
		    used++;
		}else{
		    chunks = chunks.addChunk();
		    used = 1;
		    chunks.buffer[0] = number;
		}
	}

	public int size() 
	{
	    if(chunks.size() > 1){
		    return (((chunks.size()-1)*chunks.chunkSize) + used);
	    }else{
	        return used;
	    }
	}
	
	/**
    * Return the number of chunks in the {@link ChunkList}
    */
	public int numberOfChunks() {
		return chunks.size();
	}
}


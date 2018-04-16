package u6a4;

/**
 * Linked list of int-arrays.
 */
public class ChunkList {
	/**
	 * The size of each chunk.
	 */
	public final static int chunkSize = 20;
	
	/**
	 * The buffer of this chunk.
	 */
	public int buffer[];
	
	/**
	 * Reference to the rest of the list.
	 * 
	 * The empty list is encoded as null reference.
	 */
	public ChunkList next;
	
	public int position;	
	/**
	 * Creates a new ChunkList with no follower.
	 */
	public ChunkList()
	{
		buffer = new int[chunkSize];
		next = null;
		position = 1;
	}
	
	public ChunkList addChunk() {
		ChunkList newList = new ChunkList();
		newList.next = this;
		newList.position = this.position + 1;
		return newList;
	}
	
	public ChunkList removeChunk() {
		return this.next;
	}
	
	public int size() {
		return position;
	}
}

package u11a4;

/**
 * A postion on a chess board
 */
public class Position implements Comparable<Position> {
	/**
	 * The row on the chess board
	 */
	public int x;

	/**
	 * The column on the chess board
	 */
	public int y;
	
	/**
	 * Create a new position object
	 * @param x the row on the chess board
	 * @param y the column on the chess board
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * ArrayList-addition of two positions
	 * @param pos vector to add to this position
	 * @return return a new position which is the sum of this and the given position
	 */
	public Position add(Position pos)
	{
		Position newPos = new Position(x, y);
		newPos.x += pos.x;
		newPos.y += pos.y;
		return newPos;
	}
	
	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}

	@Override
	public int compareTo(Position lhs) {
		if (x == lhs.x) {
			return y - lhs.y;
		} 
		return x - lhs.x;
	}
	
	@Override 
	public boolean equals(Object that)
	{
		if (this == that) return true;
		if (! (that instanceof Position)) return false;
		Position lhs = (Position) that;
		return x == lhs.x && y == lhs.y;
	}
}

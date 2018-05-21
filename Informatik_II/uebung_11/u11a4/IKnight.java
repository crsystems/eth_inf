package u11a4;

import java.util.ArrayList;

public interface IKnight {
	/**
	 * The size of the chess board.
	 * 
	 * Valid positions go from 0 (inclusive) to boardSize (exclusive)
	 */
	final int boardSize = 8;

	/**
	 * Determines the set of fields which are reachable by the knight.
	 * 
	 * @param pos
	 *            the start position of the knight
	 * @param numberofMoves
	 *            the number of moves available for the knight.
	 * @return the set of fields which can be reached by the knight within at
	 *         most numberofMoves moves starting at the given position.
	 */
	public ArrayList<Position> getReachableSet(Position pos, int numberofMoves);

	/**
	 * Determines a complete possible path through the chess board
	 * 
	 * A path is a duplicate-free series of positions.
	 * A complete path consists of all positions of the chess board.
	 * 
	 * @param pos
	 *            the starting position of the knight
	 * @return a complete path starting at the given position or null if no such path exists
	 */
	public ArrayList<Position> findCompletePath(Position pos);
}

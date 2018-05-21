package u11a4;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;

public class Tests {
	private IKnight knight;

	@Before
	public void setup() {
		knight = KnightFactory.create();
	}
	
	private String printSet(ArrayList<Position> positions)
	{
		StringBuffer s = new StringBuffer("\n  ");
		for (int x=0; x<IKnight.boardSize; x++) {
			s.append(x);
		}
		s.append("\n  ");
		for (int x=0; x<IKnight.boardSize; x++) {
			s.append("-");
		}
		s.append("\n");

		for (int y=0; y<IKnight.boardSize; y++) {
			s.append(y).append("|");
			for (int x=0; x<IKnight.boardSize; x++) {
				if (positions.contains(new Position(x, y))) {
					s.append("X");
				} else {
					s.append(" ");
				}
			}
			s.append("\n");
		}
		return s.toString();
	}

	private void compare(ArrayList<Position> result, int[]... expected) {
		Collections.sort(result);

		ArrayList<Position> expectedVec = new ArrayList<Position>(expected.length);
		for (int[] pos : expected) {
			expectedVec.add(new Position(pos[0], pos[1]));
		}
		Collections.sort(expectedVec);

		Assert.assertEquals(printSet(result), expectedVec.toString(), result.toString());
	}

	@Test
	public void noHop() {
		ArrayList<Position> result = knight.getReachableSet(new Position(4, 4), 0);
		int expected[][] = { { 4, 4 } };
		compare(result, expected);
	}

	@Test
	public void oneHop() {
		ArrayList<Position> result = knight.getReachableSet(new Position(4, 4), 1);
		int expected[][] = { { 4, 4 }, { 5, 6 }, { 6, 5 }, { 6, 3 }, { 5, 2 },
				{ 3, 2 }, { 2, 3 }, { 2, 5 }, { 3, 6 } };
		compare(result, expected);
	}

	@Test
	public void oneHopCorner() {
		ArrayList<Position> result = knight.getReachableSet(new Position(0, 0), 1);
		int expected[][] = { { 0, 0 }, { 1, 2 }, { 2, 1 } };
		compare(result, expected);
	}

	@Test
	public void twoHopsCorner() {
		ArrayList<Position> result = knight.getReachableSet(new Position(0, 0), 2);
		int expected[][] = { { 0, 0 }, { 0, 2 }, { 0, 4 }, { 1, 2 }, { 1, 3 },
				{ 2, 0 }, { 2, 1 }, { 2, 4 }, { 3, 1 }, { 3, 3 }, { 4, 0 },
				{ 4, 2 } };
		compare(result, expected);
	}

	@Test
	public void threeHops() {
		ArrayList<Position> result = knight.getReachableSet(new Position(4, 4), 3);
		int expected[][] = {{ 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 },
				{ 0, 5 }, { 0, 6 }, { 0, 7 }, { 1, 0 }, { 1, 1 }, { 1, 2 },
				{ 1, 3 }, { 1, 4 }, { 1, 5 }, { 1, 6 }, { 1, 7 }, { 2, 0 },
				{ 2, 1 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, 
				{ 2, 7 }, { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 3 }, { 3, 4 },
				{ 3, 5 }, { 3, 6 }, { 3, 7 }, { 4, 0 }, { 4, 1 }, { 4, 2 },
				{ 4, 3 }, { 4, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 5, 0 },
				{ 5, 1 }, { 5, 2 }, { 5, 3 }, { 5, 4 }, { 5, 5 }, { 5, 6 },
				{ 5, 7 }, { 6, 0 }, { 6, 1 }, { 6, 3 }, { 6, 4 },
				{ 6, 5 }, { 6, 7 }, { 7, 0 }, { 7, 1 }, { 7, 2 },
				{ 7, 3 }, { 7, 4 }, { 7, 5 }, { 7, 6 }, { 7, 7 }, };
		compare(result, expected);
	}
	
	@Test
	public void fourHops() {
		ArrayList<Position> result = knight.getReachableSet(new Position(4, 4), 4);
		int expected[][] = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 },
				{ 0, 5 }, { 0, 6 }, { 0, 7 }, { 1, 0 }, { 1, 1 }, { 1, 2 },
				{ 1, 3 }, { 1, 4 }, { 1, 5 }, { 1, 6 }, { 1, 7 }, { 2, 0 },
				{ 2, 1 }, { 2, 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, { 2, 6 },
				{ 2, 7 }, { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 3 }, { 3, 4 },
				{ 3, 5 }, { 3, 6 }, { 3, 7 }, { 4, 0 }, { 4, 1 }, { 4, 2 },
				{ 4, 3 }, { 4, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 5, 0 },
				{ 5, 1 }, { 5, 2 }, { 5, 3 }, { 5, 4 }, { 5, 5 }, { 5, 6 },
				{ 5, 7 }, { 6, 0 }, { 6, 1 }, { 6, 2 }, { 6, 3 }, { 6, 4 },
				{ 6, 5 }, { 6, 6 }, { 6, 7 }, { 7, 0 }, { 7, 1 }, { 7, 2 },
				{ 7, 3 }, { 7, 4 }, { 7, 5 }, { 7, 6 }, { 7, 7 }, };
		compare(result, expected);
	}
	
	@Test
	public void path()
	{
		ArrayList<Position> result = knight.findCompletePath(new Position(0, 0));

		// check the size
		Assert.assertEquals(64, result.size());
		
		// check the path
		for (int i=0; i<63; i++) {
			final int dx = result.get(i+1).x - result.get(i).x;
			final int dy = result.get(i+1).y - result.get(i).y;
			Assert.assertTrue(
					dx ==  1 && dy ==  2 
				||  dx ==  2 && dy ==  1
				||  dx ==  2 && dy == -1
				||  dx ==  1 && dy == -2
				||  dx == -1 && dy == -2
				||  dx == -2 && dy == -1
				||  dx == -2 && dy ==  1
				||  dx == -1 && dy ==  2
			);
		}
		
		// check the uniqueness
		Collections.sort(result);
		for (int i=0; i<63; i++) {
			Assert.assertFalse(result.get(i+1).equals(result.get(i)));
		}
	}
}
import java.util.NoSuchElementException;
import list.List;
import org.junit.Test;
import org.junit.Assert;
import u5a3.SortedLists;

public class PublicTest extends list.Tests { 	
	private void checkInsertSorted(List list, int value, String expected)
	{
		check(SortedLists.insertSorted(list, value), expected);
	}
	
	@Test
	public void insertSorted()
	{
		checkInsertSorted(null, 1, "1, null");
		checkInsertSorted(create(1), 2, "1, 2, null");
		checkInsertSorted(create(2), 1, "1, 2, null");
		checkInsertSorted(create(3,1), 2, "1, 2, 3, null");
		checkInsertSorted(create(2,1), 3, "1, 2, 3, null");
		checkInsertSorted(create(3,2), 1, "1, 2, 3, null");
		checkInsertSorted(create(3,2,1), 2, "1, 2, 2, 3, null");
	}
	
	private void checkSort(List list, String expected)
	{
		check(SortedLists.sort(list), expected);
	}
	
	@Test
	public void sort()
	{
		checkSort(null, "null");
		checkSort(create(1), "1, null");
		checkSort(create(1,2), "1, 2, null");
		checkSort(create(2,1), "1, 2, null");
		checkSort(create(1,2,3), "1, 2, 3, null");
		checkSort(create(3,2,1), "1, 2, 3, null");
		checkSort(create(5,3,7,9,2), "2, 3, 5, 7, 9, null");
	}
	
}


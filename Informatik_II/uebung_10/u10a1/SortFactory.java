package u10a1;

public class SortFactory {
	public static ISort<Integer> create() {
		ISort<Integer> sort = new MergeSort<Integer>();
		return sort;
	}
}

package u12a1;

public class SortFactory {
	public static ISort<Integer> create() {
		ISort<Integer> tmp = new HeapSort<Integer>();
		return tmp;
	}
}

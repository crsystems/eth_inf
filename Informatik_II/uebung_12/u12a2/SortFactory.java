package u12a2;

public class SortFactory {
	public static ISort create() {
		ISort srt = new MergeSort(2);
		return srt;
	}
}

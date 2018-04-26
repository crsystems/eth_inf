package u8a1;

public class BinarySearchFactory {
	public static IBinarySearch<Integer, String> create()
	{
		IBinarySearch<Integer, String> obj = new BinarySearch<Integer, String>();
		return obj;
	}
}

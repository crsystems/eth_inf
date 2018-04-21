package u7a3;

public class UtilsFactory {
	public static IBinarySearchTreeUtils<String> create()
	{
		IBinarySearchTreeUtils<String> util = new BinarySearchTreeUtils<String>(); 
		return util;
	}
}

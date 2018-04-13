package u6a2;

public class StackFactory {
	public static IStack create()
	{
        	IStack stack = new ListStack();
		return stack;
	}
}

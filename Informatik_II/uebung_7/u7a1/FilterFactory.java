package u7a1;

/**
 * Factory for "Testat" filters 
 */
public class FilterFactory {
	/**
	 * Create a "Testat" filter
	 * @return a "Testat" filter
	 */
	public static IFilter create()
	{
		IFilter f = new Filter();
		return f;
	}
}

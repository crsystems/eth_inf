package u11a4;

public class KnightFactory {
	public static IKnight create(){
		IKnight k = new Knight();
		return k;
	}
}

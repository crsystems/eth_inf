package u8a2;

public class RucksackFactory {
	public static IRucksack create() {

		//IRucksack sack = new BruteForceRucksack();
		IRucksack sack = new BacktrackingRucksack();

		return sack;
	}
}

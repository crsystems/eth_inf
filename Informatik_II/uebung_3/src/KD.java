public class KD {
	/**
	 * Parse a "Klammerdarstellung" (KD) of a tree.
	 * 
	 * <ul>
	 * <li>An empty tree is encoded as '-'.</li>
	 * <li>A node is encoded as an uppercase letter, i.e. everything accepted by {@link Character#isUpperCase(char)}.</li>
	 * <li>Children are appended to the father as a ','-separated list of nodes enclosed in round brackets.</li>
	 * </ul> 
	 * 
	 * @param kd Tree encoded in KD
	 * @throws ParseException if the given String is not a valid KD of a tree.
	 */
	public static void parse(String kd) throws ParseException{
	}

	private int index = 0;

	private static int parse_tree(String kd){
		int parsed = 0;
		int cur = 0;


		cur = parse_node(String);
		if(cur == -1){
			return -1;
		}else{
			parsed += cur;
		}

		if(index < kd.length){
			parsed++;

			if(kd.charAt(index) != '('){
				return -1;
			}
			index++;

			cur = parse_subtree();
			if(cur == -1){
				return -1;
			}else{
				parsed += cur;
			}

			parsed++;
			if(kd.charAt(index) != ')'){
				return -1;
			}
			index++;
		}

		return parsed;


	}

	private static int parse_subtree(String kd){


	}


	private static int parse_node(String kd){
		if(


	}
}


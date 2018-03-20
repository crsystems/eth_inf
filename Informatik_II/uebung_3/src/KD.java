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
		
		check_offset(kd, 0);
		int offset = parse_tree(kd, 0);
		
		if(offset < kd.length()){
			throw new ParseException("Unexpected end of tree!", offset);
		}

		return;
	}

	private static void check_offset(String kd, int offset) throws ParseException{
		if((offset < kd.length()) == false){
			throw new ParseException("Unexpected end of tree!", offset);
		}

	}

	private static int parse_tree(String kd, int offset) throws ParseException{

		offset = parse_node(kd, offset);
		
		if(offset < kd.length() && kd.charAt(offset) == '('){

			if(kd.charAt(offset-1) == '-'){
				throw new ParseException("Missing root for subtree!", offset-1);
			}

			offset++;

			check_offset(kd, offset);

			offset = parse_subtree(kd, offset);

			check_offset(kd, offset);

			if(kd.charAt(offset) == ')'){
				offset++;
				return offset;
			}else{
				throw new ParseException("Expecting ')' but got: " + kd.charAt(offset) + " !", offset);
			}
		}

		return offset;

	}

	private static int parse_subtree(String kd, int offset) throws ParseException{

		offset = parse_tree(kd, offset);

		check_offset(kd, offset);

		while(kd.charAt(offset) == ','){
			offset++;
			
			check_offset(kd, offset);

			offset = parse_tree(kd, offset);

			check_offset(kd, offset);
		}

		return offset;
	}


	private static int parse_node(String kd, int offset) throws ParseException{
		if(Character.isUpperCase(kd.charAt(offset)) == false && kd.charAt(offset) != '-'){
			throw new ParseException("Not a valid node: " + kd.charAt(offset) + " !", offset);
		}
		return ++offset; 	

	}
}


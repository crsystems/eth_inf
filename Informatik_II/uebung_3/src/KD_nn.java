public class KD_nn {
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
	    
	    Baum(kd,0);
	    
	}
	
	private static int Baum(String str, int ort) throws ParseException{
	    
	    int offset=ort;
	    
	    check_offset(str,offset);
	    
	    if(str.length()==1){
	        return offset;
	    }
	    
	    offset++;
	    check_offset(str,offset);
	    
	    if(str.charAt(offset)=='('){
	        offset++;
	        offset+=Unterbaum(str,offset);
	        offset++;
	        check_offset(str,offset);
	        if(str.charAt(offset)==')'){
	            return offset;
	        }
	    }
	    
	    
	   throw new ParseException(str,ort);

	}

    private static int Unterbaum(String str, int ort) throws ParseException{
        
        check_offset(str,ort);
        int offset=Baum(str,ort);
	    while(str.charAt(offset)==','){
	        check_offset(str,offset);
	        offset++;
	        offset = Baum(str,offset);
	        check_offset(str,offset);
	    }
	    return offset;
	}
	
	private static int Knoten(String str, int ort) throws ParseException{
	    
	    check_offset(str,ort);
	    if(Character.isUpperCase(str.charAt(ort)) || str.charAt(ort)=='-'){
	        return ort++;
	    }
	    else {
	        throw new ParseException(str,ort);
	    }
	    
	}
	
	private static void check_offset(String str, int ort) throws ParseException{
	    if(ort>=str.length()){
	        throw new ParseException(str,ort);
	    }
	}
	
}


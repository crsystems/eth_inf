public class CaesarChiffre {

	/**
	 * Encrypts text using CaesarChiffre (i.e., adding 3 to the ASCII code of
	 * each character). The encryption employs Strings.
	 * 
	 * @param s
	 *            plaintext to be encrypted
	 */
	public static String encrypt(String s) {
		String ret = "";
		for (int i = 0; i != s.length(); ++i) {
			ret = ret + (char) (s.charAt(i) + 3);
		}
		return ret;
	}

	/**
	 * Decrypts input text based on the CaesarChiffre (i.e., removing 3 from the
	 * ASCII code of each character). The decryption employs StringBuffers
	 * (instead of Strings).
	 * 
	 * @param s
	 *            ciphertext to be decrypted
	 */
	public static String decrypt(String s) {
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < s.length(); i++){
			int c = s.charAt(i) - 3;
			buf.append((char) c);
		}
		return buf.toString();
	}
}


import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for CaesarChiffre class
 */
public class PublicTest_2 {

	/**
	 * Tests encryption.
	 */
	@Test
	public void testCaesarEncryption() {
		String plainText = "Xaver5";
		String cipherText = CaesarChiffre.encrypt(plainText);
		Assert.assertEquals("[dyhu8", cipherText);
	}

	/**
	 * Tests decryption.
	 */
	@Test
	public void testCaesarDecryption() {
		String cypherText = "[dyhu8";
		String plainText = CaesarChiffre.decrypt(cypherText);
		Assert.assertEquals("Xaver5", plainText);
	}
}


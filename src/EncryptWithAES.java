import javax.crypto.*;

public class EncryptWithAES {
	public static void main(String[] args) throws Exception{
	//Says that we need to generate a Key for AES Encryption
		KeyGenerator KeyGen = KeyGenerator.getInstance("AES");
	//Clarifies that it will be 128 bit AES Encryption
		KeyGen.init(128);
	//Creates the Key that we will be using
		SecretKey key = KeyGen.generateKey();
	//Call the encrypt() method in the Encrypt Class that will do the encryption
		Encrypt.encrypt(key);
	//Call the decrypt() method in the Decrypt Class that will do the decryption
		Decrypt.decrypt(key);
	}
}
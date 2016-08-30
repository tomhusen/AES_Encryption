import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.JFileChooser;
import java.awt.Frame;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Encrypt {
	public static byte[] encrypt(SecretKey key) throws Exception{
	//Creates the input window to select the file you want to encrypt
		System.out.println("Select the file that you want to encrypt: ");
		Frame window = null;
		JFileChooser chooser = new JFileChooser();
		@SuppressWarnings("unused")
		int returnVal = chooser.showOpenDialog(window);
		File file = chooser.getSelectedFile();
		Scanner sc = new Scanner(file);
		String textToEncrypt = "";
		while(sc.hasNext()) {
			String word = sc.next() + " ";
			textToEncrypt += word;
		}
		sc.close();
	//Creates the AES Cipher that we'll be using
		Cipher c = Cipher.getInstance("AES");
	//Takes the contents of the Text we want to encrypt, and puts everything into a byte array
		byte[] textBytes = textToEncrypt.getBytes();
	//Initializes the cipher as an 'encryption' cipher
		c.init(Cipher.ENCRYPT_MODE, key);
	//Takes the textBytes byte array and does the encryption on it, then creates one big byte array
		byte[] cipherText = c.update(textBytes);
		byte[] cipherText1 = c.doFinal();
		byte[] encryptedBytes = new byte[cipherText.length + cipherText1.length];
	//Copies the two arrays into one
		System.arraycopy(cipherText, 0, encryptedBytes, 0, cipherText.length);
		System.arraycopy(cipherText1, 0, encryptedBytes, cipherText.length, cipherText1.length);
		FileOutputStream output = new FileOutputStream(file + "_ENCRYPTED.txt");
		output.write(encryptedBytes);
		output.close();
		System.out.println("********************");
		System.out.println("Successfully encrypted file.");
		return encryptedBytes;
	}
}
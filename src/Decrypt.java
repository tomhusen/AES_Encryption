import java.awt.Frame;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.*;
import javax.swing.JFileChooser;

public class Decrypt {
	public static byte[] decrypt( SecretKey key) throws Exception{
		
	//Creates the input window to select the file you want to decrypt
		System.out.println("********************");
		System.out.println("Select the file that you want to decrypt: ");
		Frame window = null;
		JFileChooser chooser = new JFileChooser();
		@SuppressWarnings("unused")
		int returnVal = chooser.showOpenDialog(window);
		File file = chooser.getSelectedFile();
		String pathString = file.getAbsolutePath();
		byte[] textBytes = Files.readAllBytes(Paths.get(pathString));
		
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] plainText = c.update(textBytes);
		byte[] plainText1 = c.doFinal();
		byte[] decryptedBytes = new byte[plainText.length + plainText1.length];
		System.arraycopy(plainText, 0, decryptedBytes, 0, plainText.length);
		System.arraycopy(plainText1, 0, decryptedBytes, plainText.length, plainText1.length);
		
		FileOutputStream output = new FileOutputStream(pathString + "_DECRYPTED");
		output.write(decryptedBytes);
		output.close();
		System.out.println("********************");
		System.out.println("Successfully decrypted file.");
		return decryptedBytes;
	}
}

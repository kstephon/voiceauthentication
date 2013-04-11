package EncryptDcrypt;

import java.io.File;

/**
 *
 * @author Adam Earley
 */
public class EcDcMain extends DecryptOrNew{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		try {
                        setKey();
                        boolean match = true;
                        File nFile = new File("Adam.txt");
                        String name = "Adam";
                        encryptNew(name, nFile);
                        File ReturnDecrypted = ReturnDecrypted(match, name);
                        //cleanUp(name); //deletes original file and decrypted file
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}

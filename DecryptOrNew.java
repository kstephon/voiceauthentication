package EncryptDcrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adam Earley
 */
public class DecryptOrNew extends EncryptDecrypt {
    public static void encryptNew(String name, File newFile)throws FileNotFoundException, IOException, Throwable{
        FileInputStream fis = new FileInputStream(newFile);
        FileOutputStream fos = new FileOutputStream(name + "E.txt");
        //The encrypted file will have an "E" on the end of the file name
        encrypt(getKey(), fis, fos);
        //File nFile = new File(name + ".txt");
        //nFile.delete();
        //cleanUp(name);
        fis.close();
        fos.close();
        }
    
    public static File ReturnDecrypted(boolean match, String name) throws FileNotFoundException, IOException
    {
        FileInputStream fis2 = new FileInputStream(name + "E.txt");
	FileOutputStream fos2 = new FileOutputStream("decryptedFile.txt");
        if (match == true)
        {
            try {              
                decrypt(getKey(), fis2, fos2);
            } catch (Throwable ex) {
                Logger.getLogger(EncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
            }
        fis2.close();
        fos2.close();
        }
        File decryptedFile = new File("decryptedFile.txt");
        return decryptedFile;
    }
        
        public static void cleanUp(String name){
            File oFile = new File(name + ".txt");
            File dFile = new File("decryptedFile.txt");
            oFile.delete();
            dFile.delete();
        }
}

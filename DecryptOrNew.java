package VoiceAuthentication;


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
    /**
     *
     * @param name
     * @param newFile
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Throwable
     */
    public static void encryptNew(String name, File newFile)throws FileNotFoundException, IOException, Throwable{
        FileInputStream fis = new FileInputStream(newFile);
        FileOutputStream fos = new FileOutputStream(name + "E.txt");
        //The encrypted file will have an "E" on the end of the file name
        encrypt(key, fis, fos);
        fis.close();
        fos.close();
        cleanUpFirstTxt(name);
        }
    
    public static void encryptNewAudio(String name, File newFile)throws FileNotFoundException, IOException, Throwable{
        FileInputStream fis = new FileInputStream(newFile);
        FileOutputStream fos = new FileOutputStream(name + "E.wav");
        //The encrypted file will have an "E" on the end of the file name
        encrypt(key, fis, fos);
        fis.close();
        fos.close();
        }
    
    /**
     * Returns a decrypted file from an encrypted file
     * @param match
     * @param name
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static File ReturnDecrypted(boolean match, String name) throws FileNotFoundException, IOException
    {
        FileInputStream fis2 = new FileInputStream(name + "E.txt");
        FileOutputStream fos2 = new FileOutputStream("decrypted.txt");
        if (match == true)
        {
            try {
                decrypt(key, fis2, fos2);
            } catch (Throwable ex) {
                Logger.getLogger(EncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
            }
        fis2.close();
        fos2.close();
        }
        File decryptedFile = new File("decrypted.txt");
        cleanUp(name);
        return decryptedFile;
    }
    
    public static void DecryptAudio(String name) throws FileNotFoundException, IOException
    {
        FileInputStream fis2 = new FileInputStream(name + "E.wav");
        FileOutputStream fos2 = new FileOutputStream("decryptedAudio.wav");
        try {
                decrypt(key, fis2, fos2);
            } catch (Throwable ex) {
                Logger.getLogger(EncryptDecrypt.class.getName()).log(Level.SEVERE, null, ex);
            }
        fis2.close();
        fos2.close();
        }
        
    /**
     * Deletes un-encrypted files
     * @param name
     */
    public static void cleanUpFirstTxt(String name){           
            File newPassFile = new File(name + ".txt");
            newPassFile.delete();
        }
    
    public static void cleanUpFirstAudio(String name){
            File newSample = new File(name + ".wav");
            File newSampleFiltered = new File(name + "filtered.wav");
            newSample.delete();
            newSampleFiltered.delete();
        }
    
     public static void cleanUp(String name){
            File decrypted = new File("decrypted.txt");            
            File originalSample = new File("lastSample.wav");
            File filteredSample = new File("lastSampleFiltered.wav");  
            File decryptedAudio = new File("decryptedAudio.wav");
            originalSample.delete();           
            filteredSample.delete();
            decryptedAudio.delete();
            decrypted.deleteOnExit();
        }
}
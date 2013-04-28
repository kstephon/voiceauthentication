package VoiceAuthentication;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author DudeManDeuce
 */
public class EncryptDecrypt{

    protected static String key = "AdamKenOwenCIS111B";

    /**
     *
     * @param key
     * @param is
     * @param os
     * @throws Throwable
     */
    public static void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
    encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
}

    /**
     *
     * @param key
     * @param is
     * @param os
     * @throws Throwable
     */
    public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
    encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
}

    /**
     *
     * @param key
     * @param mode
     * @param is
     * @param os
     * @throws Throwable
     */
    public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

    DESKeySpec dks = new DESKeySpec(key.getBytes());
    SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
    SecretKey desKey = skf.generateSecret(dks);
    Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

    if (mode == Cipher.ENCRYPT_MODE) {
    cipher.init(Cipher.ENCRYPT_MODE, desKey);
    CipherInputStream cis = new CipherInputStream(is, cipher);
    doCopy(cis, os);
    } else if (mode == Cipher.DECRYPT_MODE) {
    cipher.init(Cipher.DECRYPT_MODE, desKey);
    CipherOutputStream cos = new CipherOutputStream(os, cipher);
    doCopy(is, cos);
    }
}

    /**
     *
     * @param is
     * @param os
     * @throws IOException
     */
    public static void doCopy(InputStream is, OutputStream os) throws IOException {
    byte[] bytes = new byte[64];
    int numBytes;
    while ((numBytes = is.read(bytes)) != -1) {
    os.write(bytes, 0, numBytes);
    }
    os.flush();
    os.close();
    is.close();
  }

}
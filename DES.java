import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
public class DES {
private static final String UNICODE_FORMAT = "UTF8";
private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
private Cipher cipher;
private SecretKey key;
private String myEncryptionKey = "ThisIsSecretEncryptionKey";
public DES() throws Exception {
byte[] keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
DESedeKeySpec myKeySpec = new DESedeKeySpec(keyAsBytes);
SecretKeyFactory mySecretKeyFactory = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
key = mySecretKeyFactory.generateSecret(myKeySpec);
}
public String encrypt(String unencryptedString) {
try {
cipher.init(Cipher.ENCRYPT_MODE, key);
byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
byte[] encryptedText = cipher.doFinal(plainText);
return Base64.getEncoder().encodeToString(encryptedText);
} catch (Exception e) {
e.printStackTrace();
return null;
}
}
public String decrypt(String encryptedString) {
try {
cipher.init(Cipher.DECRYPT_MODE, key);
byte[] encryptedText = Base64.getDecoder().decode(encryptedString);
byte[] plainText = cipher.doFinal(encryptedText);
return new String(plainText, UNICODE_FORMAT);
} catch (Exception e) {
e.printStackTrace();
return null;
}
}
public static void main(String args[]) throws Exception {
DES myEncryptor = new DES();
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
System.out.print("Enter the string to encrypt: ");
String stringToEncrypt = br.readLine();
String encryptedString = myEncryptor.encrypt(stringToEncrypt);
System.out.println("Encrypted String: " + encryptedString);
String decryptedString = myEncryptor.decrypt(encryptedString);
System.out.println("Decrypted String: " + decryptedString);
}
}
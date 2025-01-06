import java.io.*;
import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

public class BlowFish {
    public static void main(String[] args) {
        try {
            // Generate Blowfish Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
            keyGenerator.init(128);
            Key secretKey = keyGenerator.generateKey();

            // Initialize Cipher for Encryption
            Cipher cipherOut = Cipher.getInstance("Blowfish/CFB/NoPadding");
            cipherOut.init(Cipher.ENCRYPT_MODE, secretKey);

            // Print IV
            byte[] iv = cipherOut.getIV();
            if (iv != null) {
                System.out.println("Initialization Vector (Base64): " + Base64.getEncoder().encodeToString(iv));
            }

            // Open File Streams for Encryption
            FileInputStream fin = new FileInputStream("inputFile.txt");
            FileOutputStream fout = new FileOutputStream("outputFile.txt");
            CipherOutputStream cout = new CipherOutputStream(fout, cipherOut);

            // Read and Encrypt File
            int input;
            while ((input = fin.read()) != -1) {
                cout.write(input);
            }

            // Close Streams after Encryption
            fin.close();
            cout.close();

            System.out.println("File Encrypted Successfully!");

            // Initialize Cipher for Decryption
            Cipher cipherIn = Cipher.getInstance("Blowfish/CFB/NoPadding");
            cipherIn.init(Cipher.DECRYPT_MODE, secretKey, cipherOut.getParameters());

            // Open File Streams for Decryption
            FileInputStream encryptedFile = new FileInputStream("outputFile.txt");
            CipherInputStream cin = new CipherInputStream(encryptedFile, cipherIn);
            ByteArrayOutputStream decryptedOutput = new ByteArrayOutputStream();

            // Read and Decrypt File
            while ((input = cin.read()) != -1) {
                decryptedOutput.write(input);
            }

            // Close Streams after Decryption
            cin.close();
            decryptedOutput.close();

            // Print Decrypted Text
            String decryptedText = decryptedOutput.toString();
            System.out.println("Decrypted Text: ");
            System.out.println(decryptedText);

        } catch (FileNotFoundException e) {
            System.err.println("File not found. Ensure inputFile.txt exists.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
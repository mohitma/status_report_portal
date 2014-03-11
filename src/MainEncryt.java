/*import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;

public class MainEncryt {
  static String algorithm = "DESede";
  static Key key = KeyGenerator.getInstance(algorithm).generateKey();
  static Cipher cipher = Cipher.getInstance(algorithm);

  public static void main(String[] args) throws Exception {
    byte[] encryptionBytes = encrypt("input");
    System.out.println("Recovered: " + decrypt(encryptionBytes));
  }

  private static byte[] encrypt(String input) throws InvalidKeyException, BadPaddingException,
      IllegalBlockSizeException {
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] inputBytes = input.getBytes();
    return cipher.doFinal(inputBytes);
  }

  private static String decrypt(byte[] encryptionBytes) throws InvalidKeyException,
      BadPaddingException, IllegalBlockSizeException {
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] recoveredBytes = cipher.doFinal(encryptionBytes);
    String recovered = new String(recoveredBytes);
    return recovered;
  }
}*/
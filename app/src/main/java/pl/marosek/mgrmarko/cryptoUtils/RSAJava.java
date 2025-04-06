package pl.marosek.mgrmarko.cryptoUtils;

import android.util.Base64;
import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import javax.crypto.Cipher;

public class RSAJava {
    private static final String TAG = "RSAJava";

    public String encryptRSA(String password, KeyPair keypair) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keypair.getPublic());
            byte[] encrypted = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeToString(encrypted, Base64.DEFAULT);
        } catch (Exception e) {
            Log.e(TAG, "Encryption error", e);
            return null;
        }
    }

//    public String decryptRSA(String password, KeyPair keypair) {
//        try {
//            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//            cipher.init(Cipher.DECRYPT_MODE, keypair.getPrivate());
//            byte[] decrypted = cipher.doFinal(Base64.decode(password, Base64.DEFAULT));
//            return new String(decrypted, StandardCharsets.UTF_8);
//        } catch (Exception e) {
//            Log.e(TAG, "Decryption error", e);
//            return null;
//        }
//    }

    public KeyPair generateKeysRSA(int size) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(size, new SecureRandom());
            return generator.generateKeyPair();
        } catch (Exception e) {
            Log.e(TAG, "Key generation error", e);
            return null;
        }
    }

//    public String getPublicKey(KeyPair keypair) {
//        return Base64.encodeToString(keypair.getPublic().getEncoded(), Base64.DEFAULT);
//    }
//
//    public String getPrivateKey(KeyPair keypair) {
//        return Base64.encodeToString(keypair.getPrivate().getEncoded(), Base64.DEFAULT);
//    }
}
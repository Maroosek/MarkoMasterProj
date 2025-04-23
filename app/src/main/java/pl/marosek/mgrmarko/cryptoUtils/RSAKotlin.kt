package pl.marosek.mgrmarko.cryptoUtils

import android.util.Base64
import android.util.Log
import java.nio.charset.StandardCharsets
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.SecureRandom
import javax.crypto.Cipher

class RSAKotlin {
    companion object {
        const val TAG = "RSAKotlin"
    }

    fun encryptRSA(password: String, keypair: KeyPair): String? {
        return try {
            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.ENCRYPT_MODE, keypair.public)
            val encrypted = cipher.doFinal(password.toByteArray(StandardCharsets.UTF_8))
            Base64.encodeToString(encrypted, Base64.DEFAULT)
        } catch (e: Exception) {
            Log.e(TAG, "Encryption error", e)
            null
        }
    }

    inline fun encryptRSAInline(password: String, keypair: KeyPair): String? {
        return try {
            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.ENCRYPT_MODE, keypair.public)
            val encrypted = cipher.doFinal(password.toByteArray(StandardCharsets.UTF_8))
            Base64.encodeToString(encrypted, Base64.DEFAULT)
        } catch (e: Exception) {
            Log.e(TAG, "Encryption error", e)
            null
        }
    }

    fun generateKeysRSA(size: Int): KeyPair? {
        return try {
            val generator = KeyPairGenerator.getInstance("RSA")
            generator.initialize(size, SecureRandom())
            generator.generateKeyPair()
        } catch (e: Exception) {
            Log.e(TAG, "Key generation error", e)
            null
        }
    }

    inline fun generateKeysRSAInline(size: Int): KeyPair? {
        return try {
            val generator = KeyPairGenerator.getInstance("RSA")
            generator.initialize(size, SecureRandom())
            generator.generateKeyPair()
        } catch (e: Exception) {
            Log.e(TAG, "Key generation error", e)
            null
        }
    }
}
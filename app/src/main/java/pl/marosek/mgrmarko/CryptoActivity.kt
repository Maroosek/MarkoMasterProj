package pl.marosek.mgrmarko

import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.marosek.mgrmarko.fileManager.FileManager
import pl.marosek.mgrmarko.cryptoUtils.RSAKotlin
import java.text.DecimalFormat
import java.util.Random

class CryptoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crypto)

        val numberPickerCrypto = findViewById<NumberPicker>(R.id.NumberPickerCrypto)
        val cryptoTextView = findViewById<TextView>(R.id.CryptoText)
        val cryptoButton = findViewById<Button>(R.id.CryptoButton)

        val rsaKt = RSAKotlin()
        val rsaJava = pl.marosek.mgrmarko.cryptoUtils.RSAJava()

        numberPickerCrypto.minValue = 5
        numberPickerCrypto.maxValue = 500

        cryptoButton.setOnClickListener {
            val passwordAmount = numberPickerCrypto.value
            val df = DecimalFormat("#.###")
            cryptoTextView.text = "Generating"

            lifecycleScope.launch {
                val cryptoTimeKotlin = withContext(Dispatchers.Default) {
                    val start = System.currentTimeMillis()
                    for (i in 0 until passwordAmount) {
                        val password = generatePassword()
                        val keyPair = rsaKt.generateKeysRSA(2048)
                        val encryptedPassword = rsaKt.encryptRSA(password, keyPair!!)
                    }
                    val end = System.currentTimeMillis()
                    df.format((end - start) * 0.001).toString()
                }

                val cyptoTimeKotlinInline = withContext(Dispatchers.Default) {
                    val start = System.currentTimeMillis()
                    for (i in 0 until passwordAmount) {
                        val password = generatePassword()
                        val keyPair = rsaKt.generateKeysRSAInline(2048)
                        val encryptedPassword = rsaKt.encryptRSAinline(password, keyPair!!)
                    }
                    val end = System.currentTimeMillis()
                    df.format((end - start) * 0.001).toString()
                }

                val cryptoTimeJava = withContext(Dispatchers.Default) {
                    val start = System.currentTimeMillis()
                    for (i in 0 until passwordAmount) {
                        val password = generatePassword()
                        val keyPair = rsaJava.generateKeysRSA(2048)
                        val encryptedPassword = rsaJava.encryptRSA(password, keyPair!!)
                    }
                    val end = System.currentTimeMillis()
                    df.format((end - start) * 0.001).toString()
                }

                val combinedTimes = "Kotlin: $cryptoTimeKotlin s\n" +
                        "Kotlin inline: $cyptoTimeKotlinInline s\n" +
                        "Java: $cryptoTimeJava s\n"

                FileManager().saveDataToFile(
                    this@CryptoActivity,
                    "Crypto",
                    "Passwords: $passwordAmount\n$combinedTimes"
                )

                cryptoTextView.text = "$passwordAmount passwords\n$combinedTimes"
            }
        }
    }

    fun generatePassword(): String {
        val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val passwordLength = Random().nextInt(20) + 8
        val password = StringBuilder()
        val random = Random()

        repeat(passwordLength) {
            val randomIndex = random.nextInt(chars.length)
            password.append(chars[randomIndex])
        }
        return password.toString()
    }

}
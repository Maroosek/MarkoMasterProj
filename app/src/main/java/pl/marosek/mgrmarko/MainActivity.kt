package pl.marosek.mgrmarko

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnMatrix = findViewById<Button>(R.id.MatrixButton)
        val btnJava = findViewById<Button>(R.id.JavaButton)
        val btnCrypto = findViewById<Button>(R.id.CryptoButton)

        btnMatrix.setOnClickListener {
            val intent = Intent(this, MatrixActivity::class.java)
            startActivity(intent)
        }
        btnJava.setOnClickListener {
            val intent = Intent(this, JavaActivity::class.java)
            startActivity(intent)
        }
        btnCrypto.setOnClickListener {
            val intent = Intent(this, CryptoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart(){
        super.onStart()
        Log.d("LIFECYCLE", "onStart") //debugging
    }
    override fun onResume(){
        super.onResume()
        Log.d("LIFECYCLE", "onResume") //debugging
    }
    override fun onPause(){
        super.onPause()
        Log.d("LIFECYCLE", "onPause") //debugging
    }
    override fun onStop(){
        super.onStop()
        Log.d("LIFECYCLE", "onStop") //debugging
    }
    override fun onRestart(){
        super.onRestart()
        Log.d("LIFECYCLE", "onRestart") //debugging
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d("LIFECYCLE", "onDestroy") //debugging
    }
}
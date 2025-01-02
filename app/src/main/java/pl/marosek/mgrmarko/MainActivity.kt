package pl.marosek.mgrmarko

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAES = findViewById<Button>(R.id.btnKt)
        val btnRSA = findViewById<Button>(R.id.btnJava)

        btnRSA.setOnClickListener {
            val intent = Intent(this, KotlinActivity::class.java)
            startActivity(intent)
        }
        btnAES.setOnClickListener {
            val intent = Intent(this, JavaActivity::class.java)
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
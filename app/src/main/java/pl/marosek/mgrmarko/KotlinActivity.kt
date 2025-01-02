package pl.marosek.mgrmarko

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)


        val textViewKotlin = findViewById<TextView>(R.id.KotlinText)
        val buttonKotlin = findViewById<Button>(R.id.KotlinButton)

    }
}
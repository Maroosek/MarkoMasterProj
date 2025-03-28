package pl.marosek.mgrmarko

import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class StringActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_string)

        val numberPickerString = findViewById<NumberPicker>(R.id.NumberPickerString)
        val stringTextView = findViewById<TextView>(R.id.StringText)
        val stringButton = findViewById<Button>(R.id.StringButton)

        }


}

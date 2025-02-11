package pl.marosek.mgrmarko

import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        //val gaussJordanInversion = GaussJordanInversion()

        val numberPickerKotlin = findViewById<NumberPicker>(R.id.NumberPickerKotlin)
        val textViewKotlin = findViewById<TextView>(R.id.KotlinText)
        val buttonKotlin = findViewById<Button>(R.id.KotlinButton)

        numberPickerKotlin.minValue = 2
        numberPickerKotlin.maxValue = 2000

        //TODO Clean this up :)

        buttonKotlin.setOnClickListener {
            val matrixSize = numberPickerKotlin.value
            val matrix = generateMatrix(matrixSize)
            var startTime = System.currentTimeMillis()
            val invertedMatrixIdiomatic = invertMatrixIdiomatic(matrix)
            var endTime = System.currentTimeMillis()
            val matrixIdiom = ((endTime - startTime) * 0.001).toString()
            var startTime2 = System.currentTimeMillis()
            val invertedMatrix = invertMatrix(matrix)
            var endTime2 = System.currentTimeMillis()
            textViewKotlin.text = ((endTime2 - startTime2) * 0.001).toString()

            //Check if matrices are equal
//            for (i in 0 until matrixSize) {
//                for (j in 0 until matrixSize) {
//                    if (invertedMatrixIdiomatic!![i][j] != invertedMatrix!![i][j]) {
//                        Toast.makeText(this, "Matrices are not equal", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }

            textViewKotlin.text = ((endTime - startTime) * 0.001).toString()
            Toast.makeText(this, "inverted", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "$matrixIdiom", Toast.LENGTH_SHORT).show()
        }



        //var matrixSize = numberPickerKotlin.value

        //var matrix = generateMatrix(matrixSize)
        //var invertedMatrix = invertMatrix(matrix)

    }

    private fun invertMatrix(matrix: Array<DoubleArray>): Array<DoubleArray> {
        return GaussJordanInversion.invert(matrix)
    }
    private fun invertMatrixIdiomatic(matrix: Array<DoubleArray>): Array<DoubleArray>? {
        return GaussJordanInversionIdiomatic.invert(matrix)
    }

    private fun generateMatrix(n: Int): Array<DoubleArray> {
        val matrix = Array(n) { DoubleArray(n) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                matrix[i][j] = Random.nextDouble(1.0, 100.0)
            }
        }
        return matrix
    }
}
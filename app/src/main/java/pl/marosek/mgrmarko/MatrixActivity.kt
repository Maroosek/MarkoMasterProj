package pl.marosek.mgrmarko

import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pl.marosek.mgrmarko.matrixUtil.GaussJordanInversion
import pl.marosek.mgrmarko.matrixUtil.GaussJordanInversionInline
import pl.marosek.mgrmarko.matrixUtil.GaussJordanInversionJava
import java.text.DecimalFormat
import kotlin.random.Random

class MatrixActivity : AppCompatActivity() {

    //TODO recheck if all 3 methods are working the same and can be measured without any doubt
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matrix)

        val numberPickerKotlin = findViewById<NumberPicker>(R.id.NumberPickerKotlin)
        val textViewKotlin = findViewById<TextView>(R.id.MatrixText)
        val buttonKotlin = findViewById<Button>(R.id.MatrixButton)

        numberPickerKotlin.minValue = 50
        numberPickerKotlin.maxValue = 2000

        buttonKotlin.setOnClickListener {
            val matrixSize = numberPickerKotlin.value
            val matrix = generateMatrix(matrixSize)
            val df = DecimalFormat("#.###")

            var startTime = System.currentTimeMillis()
            val invertedMatrixInline = GaussJordanInversionInline.invert(matrix)
            var endTime = System.currentTimeMillis()
            val matrixTimeInline = df.format((endTime - startTime) * 0.001).toString()

            startTime = System.currentTimeMillis()
            val invertedMatrix = GaussJordanInversion.invert(matrix)
            endTime = System.currentTimeMillis()
            val matrixTimePure = df.format((endTime - startTime) * 0.001).toString()

            startTime = System.currentTimeMillis()
            val invertedMatrixJava = GaussJordanInversionJava.invert(matrix)
            endTime = System.currentTimeMillis()
            val matrixTimeJava = df.format((endTime - startTime) * 0.001).toString()

            val combinedTimes = "Kotlin inline: $matrixTimeInline s\n" +
                    "Kotlin: $matrixTimePure s\n" +
                    "Java: $matrixTimeJava s\n"

            textViewKotlin.text = matrixSize.toString() + " Matrix size\n" +
                    combinedTimes

//            if (!checkMatrixEquality(invertedMatrix, invertedMatrixInline!!) || !checkMatrixEquality(invertedMatrix, invertedMatrixJava)) {
//                Toast.makeText(this, "Matrices are not equal", Toast.LENGTH_SHORT).show()
//                textViewKotlin.text = "Matrices are not equal"
//                return@setOnClickListener
//            }
        }

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

    private fun checkMatrixEquality(matrix1: Array<DoubleArray>, matrix2: Array<DoubleArray>): Boolean {
        for (i in matrix1.indices) {
            for (j in matrix1[i].indices) {
                if (matrix1[i][j] != matrix2[i][j]) {
                    return false
                }
            }
        }
        return true
    }
}
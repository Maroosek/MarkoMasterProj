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
import pl.marosek.mgrmarko.matrixUtil.GaussJordanInversion
import pl.marosek.mgrmarko.matrixUtil.GaussJordanInversionInline
import pl.marosek.mgrmarko.matrixUtil.GaussJordanInversionJava
import java.text.DecimalFormat
import kotlin.random.Random

class MatrixActivity : AppCompatActivity() {

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
            val df = DecimalFormat("#.###")
            textViewKotlin.text = "Generating"

            lifecycleScope.launch {
                val matrix = withContext(Dispatchers.Default) { generateMatrix(matrixSize) }

                val (invertedMatrixInline, matrixTimeInline) = withContext(Dispatchers.Default) {
                    val start = System.currentTimeMillis()
                    val result = GaussJordanInversionInline().invert(matrix)
                    val end = System.currentTimeMillis()
                    result to df.format((end - start) * 0.001).toString()
                }

                val (invertedMatrix, matrixTimePure) = withContext(Dispatchers.Default) {
                    val start = System.currentTimeMillis()
                    val result = GaussJordanInversion().invert(matrix)
                    val end = System.currentTimeMillis()
                    result to df.format((end - start) * 0.001).toString()
                }

                val (invertedMatrixJava, matrixTimeJava) = withContext(Dispatchers.Default) {
                    val start = System.currentTimeMillis()
                    val result = GaussJordanInversionJava().invert(matrix)
                    val end = System.currentTimeMillis()
                    result to df.format((end - start) * 0.001).toString()
                }

                val combinedTimes = "Kotlin inline: $matrixTimeInline s\n" +
                        "Kotlin: $matrixTimePure s\n" +
                        "Java: $matrixTimeJava s\n"

                FileManager().saveDataToFile(
                    this@MatrixActivity,
                    "Matrix",
                    "Matrix size: $matrixSize \n$combinedTimes"
                )

                textViewKotlin.text = "Matrix size: $matrixSize \n$combinedTimes"
            }
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
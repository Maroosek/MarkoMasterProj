package pl.marosek.mgrmarko

class GaussJordanInversion {
    companion object {
        fun invert(matrix: Array<DoubleArray>): Array<DoubleArray> {
            val n = matrix.size
            val result = Array(n) { DoubleArray(n) }
            for (i in 0 until n) {
                for (j in 0 until n) {
                    result[i][j] = if (i == j) 1.0 else 0.0
                }
            }
            for (i in 0 until n) {
                val pivot = matrix[i][i]
                for (j in 0 until n) {
                    matrix[i][j] /= pivot
                    result[i][j] /= pivot
                }
                for (j in 0 until n) {
                    if (i != j) {
                        val factor = matrix[j][i]
                        for (k in 0 until n) {
                            matrix[j][k] -= factor * matrix[i][k]
                            result[j][k] -= factor * result[i][k]
                        }
                    }
                }
            }
            return result
        }
    }
}
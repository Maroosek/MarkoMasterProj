package pl.marosek.mgrmarko.matrixUtil

class GaussJordanInversion {

    fun invert(matrix: Array<DoubleArray>): Array<DoubleArray> {
        val n = matrix.size
        val augmentedMatrix = Array(n) { DoubleArray(2 * n) }

        for (i in 0 until n) {
            for (j in 0 until n) {
                augmentedMatrix[i][j] = matrix[i][j]
                augmentedMatrix[i][j + n] = if (i == j) 1.0 else 0.0
            }
        }

        for (i in 0 until n) {
            val pivot = augmentedMatrix[i][i]
            for (j in 0 until 2 * n) {
                augmentedMatrix[i][j] /= pivot
            }

            for (k in 0 until n) {
                if (k != i) {
                    val factor = augmentedMatrix[k][i]
                    for (j in 0 until 2 * n) {
                        augmentedMatrix[k][j] -= factor * augmentedMatrix[i][j]
                    }
                }
            }
        }

        val invertedMatrix = Array(n) { DoubleArray(n) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                invertedMatrix[i][j] = augmentedMatrix[i][j + n]
            }
        }

        return invertedMatrix
    }
}

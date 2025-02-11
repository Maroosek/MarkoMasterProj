package pl.marosek.mgrmarko

class GaussJordanInversionIdiomatic {
    companion object {
        //TODO double check this by comparing to gaussJordanInversion
        fun invert(matrix: Array<DoubleArray>): Array<DoubleArray>? {
            val n = matrix.size

            val matCopy = matrix.map { it.copyOf() }.toTypedArray()
            val result = Array(n) { i -> DoubleArray(n) { j -> if (i == j) 1.0 else 0.0 } }

            for (i in 0 until n) {
                val pivot = matCopy[i][i]
                if (pivot == 0.0) return null

                matCopy[i].indices.forEach { j ->
                    matCopy[i][j] /= pivot
                    result[i][j] /= pivot
                }

                (0 until n).filter { it != i }.forEach { j ->
                    val factor = matCopy[j][i]
                    matCopy[j].indices.forEach { k ->
                        matCopy[j][k] -= factor * matCopy[i][k]
                        result[j][k] -= factor * result[i][k]
                    }
                }
            }
            return result
        }
    }
}
package pl.marosek.mgrmarko;

public class GaussJordanInversionJava {
    public static double[][] invert(double[][] matrix) {
        int n = matrix.length;
        double[][] augmentedMatrix = new double[n][2 * n];
        double[][] invertedMatrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
                augmentedMatrix[i][j + n] = i == j ? 1 : 0;
            }
        }

        for (int i = 0; i < n; i++) {
            double pivot = augmentedMatrix[i][i];
            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix[i][j] /= pivot;
            }

            for (int k = 0; k < n; k++) {
                if (k == i) {
                    continue;
                }

                double factor = augmentedMatrix[k][i];
                for (int j = 0; j < 2 * n; j++) {
                    augmentedMatrix[k][j] -= factor * augmentedMatrix[i][j];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                invertedMatrix[i][j] = augmentedMatrix[i][j + n];
            }
        }

        return invertedMatrix;
    }
}

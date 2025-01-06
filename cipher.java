
class cipher {

    // Function to generate the key matrix
    static void getKeyMatrix(String key, int keyMatrix[][]) {
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                keyMatrix[i][j] = key.charAt(k) - 'A';
                k++;
            }
        }
    }

    // Function to find the inverse of the key matrix (mod 26)
    static void inverseKeyMatrix(int keyMatrix[][], int inverseKey[][]) {
        int determinant = keyMatrix[0][0] * (keyMatrix[1][1] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][1])
                - keyMatrix[0][1] * (keyMatrix[1][0] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][0])
                + keyMatrix[0][2] * (keyMatrix[1][0] * keyMatrix[2][1] - keyMatrix[1][1] * keyMatrix[2][0]);

        determinant = (determinant % 26 + 26) % 26; // Ensure positive determinant
        int determinantInverse = -1;

        for (int i = 0; i < 26; i++) {
            if ((determinant * i) % 26 == 1) {
                determinantInverse = i;
                break;
            }
        }

        if (determinantInverse == -1) {
            throw new IllegalArgumentException("Key matrix is not invertible under mod 26");
        }

        int[][] adjoint = new int[3][3];
        adjoint[0][0] = keyMatrix[1][1] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][1];
        adjoint[0][1] = -(keyMatrix[0][1] * keyMatrix[2][2] - keyMatrix[0][2] * keyMatrix[2][1]);
        adjoint[0][2] = keyMatrix[0][1] * keyMatrix[1][2] - keyMatrix[0][2] * keyMatrix[1][1];
        adjoint[1][0] = -(keyMatrix[1][0] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][0]);
        adjoint[1][1] = keyMatrix[0][0] * keyMatrix[2][2] - keyMatrix[0][2] * keyMatrix[2][0];
        adjoint[1][2] = -(keyMatrix[0][0] * keyMatrix[1][2] - keyMatrix[0][2] * keyMatrix[1][0]);
        adjoint[2][0] = keyMatrix[1][0] * keyMatrix[2][1] - keyMatrix[1][1] * keyMatrix[2][0];
        adjoint[2][1] = -(keyMatrix[0][0] * keyMatrix[2][1] - keyMatrix[0][1] * keyMatrix[2][0]);
        adjoint[2][2] = keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inverseKey[i][j] = (adjoint[i][j] * determinantInverse % 26 + 26) % 26;
            }
        }
    }

    // Function to perform matrix multiplication for encryption/decryption
    static void matrixMultiply(int result[][], int matrix1[][], int matrix2[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                result[i][j] = 0;
                for (int x = 0; x < 3; x++) {
                    result[i][j] += matrix1[i][x] * matrix2[x][j];
                }
                result[i][j] %= 26;
            }
        }
    }

    // Function to encrypt or decrypt the message
    static String processMessage(String message, int keyMatrix[][]) {
        int[][] messageVector = new int[3][1];
        for (int i = 0; i < 3; i++) {
            messageVector[i][0] = message.charAt(i) - 'A';
        }

        int[][] resultMatrix = new int[3][1];
        matrixMultiply(resultMatrix, keyMatrix, messageVector);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            result.append((char) (resultMatrix[i][0] + 'A'));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String message = "PAY";
        String key = "RRFVSVCCT";

        // Key matrix generation
        int[][] keyMatrix = new int[3][3];
        getKeyMatrix(key, keyMatrix);

        // Encrypt the message
        String cipherText = processMessage(message, keyMatrix);
        System.out.println("Ciphertext: " + cipherText);

        // Decrypt the ciphertext
        int[][] inverseKey = new int[3][3];
        inverseKeyMatrix(keyMatrix, inverseKey);
        String decryptedText = processMessage(cipherText, inverseKey);
        System.out.println("Decrypted text: " + decryptedText);
    }
}

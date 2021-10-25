import java.util.Random;
import java.util.Scanner;
import static java.lang.Math.min;

public class rref {
    public static void main(String[] args) {

        //read in input from file for now because I dont want to type in a matrix every single time ha
        Random rand = new Random();
        Scanner in = new Scanner(System.in);


        System.out.println("Enter number of Rows");
        int numRows = in.nextInt();
        System.out.println("Enter number of Columns");
        int numColumns = in.nextInt();

        double[][] matrix = new double[numRows][numColumns];

<<<<<<< HEAD
        populateMatrix(rand, numRows, numColumns, matrix);
        /*for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                matrix[i][j] = in.nextInt();
            }
        }*/
=======
        //populateMatrix(rand, numRows, numColumns, matrix);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
>>>>>>> 958e57479125c7624b2cc20ebf552b8365ffec20
        printMatrix(numRows, numColumns, matrix);

        int current = 0;
        int i;
        double curVal;

        for(int k = 0; k < numRows; k++){
            if(numColumns <= current){
                break;
            }
            i = k;
            while(matrix[i][current] == 0){
                i++;
                if(numRows == i){
                    i = k;
                    current++;
                    if(numColumns == current){
                        break;
                    }
                }
            }
            if (i != k)
                matrix = rowSwap(matrix, i, k, numRows, numColumns);
            if (current < numColumns) {
<<<<<<< HEAD
                curVal = matrix[k][current];
                if (matrix[k][current] != 0) {
                    matrix = rowScale(matrix, k, numRows, numColumns, (1 / curVal));
=======
                if (matrix[k][current] != 0) {
                    matrix = rowScale(matrix, k, numRows, numColumns, (1 / matrix[k][current]));
>>>>>>> 958e57479125c7624b2cc20ebf552b8365ffec20
                }
                for (i = 0; i < numRows; i++) {
                    if (i != k) {
                        matrix = rowAddScale(matrix, k, i, numRows, numColumns, ((-1) * matrix[i][current]));
                    }
                }
<<<<<<< HEAD
                //matrix = rowScale(matrix, k, numRows, numColumns, (curVal / 1)); //for not rref
                current++;
            }
            printMatrix(numRows, numColumns, matrix);
=======
                current++;
            }
            //printMatrix(numRows, numColumns, matrix);
>>>>>>> 958e57479125c7624b2cc20ebf552b8365ffec20
        }
        System.out.println();
        printMatrix(numRows, numColumns, matrix);
    }

    static void populateMatrix(Random rand, int r, int c, double matrix[][]) {
        for(int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix[i][j] = rand.nextInt(10);
            }
        }
    }

    static void printMatrix(int r, int c, double matrix[][]) {
        for(int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == -0) {
                    Math.abs(matrix[i][j]);
                }
                System.out.printf("%5.2f ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    static double[][] rowSwap(double [][] matrix, int r1, int r2, int r, int c) {
        double temp;
        System.out.println("swapping " + r1+1 + " and " + r2+1);
        for (int i = 0; i < c; i++) {
            temp = matrix[r2][i];
            matrix[r2][i] = matrix[r1][i];
            matrix[r1][i] = temp;
        }
        //printMatrix(r, c, matrix);
        return matrix;
    }

    static double[][] rowScale(double[][] matrix, int r1,int r, int c, double scale) {
        System.out.printf("scaling %d by %.2f%n", r1+1, scale);
        for (int i = 0; i < c; i++) {
            matrix[r1][i] *= scale;
        }
        //printMatrix(r, c, matrix);
        return matrix;
    }

    static double[][] rowAddScale(double[][] matrix, int r1, int r2,int r, int c, double scale) {
        System.out.printf("scaling %d by %.2f and adding it to %d%n", r1+1, scale, r2+1);
        //System.out.println("scaling " + r1 + " by " + scale +  " and adding it to " + r2);
        for (int i = 0; i < c; i++) {
            matrix[r2][i] += (matrix[r1][i] * scale);
        }
        //printMatrix(r, c, matrix);
        return matrix;
    }
}

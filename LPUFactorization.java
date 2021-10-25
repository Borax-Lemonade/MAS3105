import java.util.Random;
import java.util.Scanner;

public class LPUFactorization {
    static Scanner in = new Scanner(System.in);

    static class matrix{
        private int rows;
        private int cols;
        private double data[][];

        public matrix(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            data = setDataToZero(rows, cols); //do a set function to get them all to be 0

        }
    }

    public static double[][] setDataToZero(int r, int c) {
        double[][] data = new double[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                data[i][j] = 0;
            }
        }
        return data;
    }

    public static matrix identityMatrix(int r) {
        matrix LI = new matrix(r, r);
        LI.cols = r;
        LI.rows = r;

        for (int i = 0; i < r; i++) {
            LI.data[i][i] = 1;
        }
        return LI;
    }

    public static void printMatrix(matrix m){
        for(int r = 0; r < m.rows; r++) {
            for(int c = 0; c < m.cols; c++) {
                System.out.printf("%5.2f ",m.data[r][c]);
            }
            System.out.println();
        }
    }

    public static double[][] populateMatrixA(matrix m) {
        Random rand = new Random();
        double[][] A = new double[m.rows][m.cols];
        for (int i = 0; i < m.rows; i++) {
            for(int j = 0; j < m.cols; j++) {
                A[i][j] = in.nextInt();
                //A[i][j] = rand.nextInt(10);
            }
        }
        return A;
    }

    public static matrix swap(matrix m, int r1, int r2) {
        double temp;
        //System.out.println("swapping " + (r1+1) + " and " + (r2+1));
        for (int i = 0; i < m.cols; i++) {
            temp = m.data[r2][i];
            m.data[r2][i] = m.data[r1][i];
            m.data[r1][i] = temp;
        }
        //printMatrix(r, c, matrix);
        return m;
    }

    public static void main(String[] args) {

        int r = 3;
        int c = 3;

        matrix matrix = new matrix(r, c);
        matrix.data = populateMatrixA(matrix);
        printMatrix(matrix);
        matrix L;
        L = identityMatrix(r);
        matrix P;
        P = identityMatrix(r);

        int pivot = 0;
        double curScale = 0;
        for (int i = pivot; i < r - 1; i++) {
            for (int j = i + 1; j < r; j++) {
                if (matrix.data[i][pivot] == 0) {
                    System.out.println("There is a 0 in a pivot position. Have to use permutations.");
                    //swap two rows if possible
                    for (int curRow = i; curRow < r; curRow++) {
                        if(matrix.data[curRow][pivot]!=0) {
                            matrix = swap(matrix, i, curRow);
                            P = swap(P, i, curRow);
                            System.out.println("swapping row " + (i+1) + " and row " + (curRow+1));
                            break;
                        }
                        if(curRow==r-1 && matrix.data[curRow][pivot]!=0) {
                            System.out.println("unable to do LPU factorization.");
                            System.exit(0);
                        }
                    }
                    //swap permutations
                }
                curScale = matrix.data[j][pivot] / matrix.data[i][pivot];
                if ((matrix.data[j][pivot] < 0 && matrix.data[i][pivot] < 0) || (matrix.data[j][pivot] > 0 && matrix.data[i][pivot] > 0))
                    curScale *= -1;
                //System.out.println("scale row " + i+1 + " by " + curScale + " and add to " + j+1);
                System.out.printf("scale row %d by %5.2f and add to row %d\n", i + 1, curScale, j + 1);
                for (int subc = 0; subc < c; subc++) {
                    matrix.data[j][subc] = matrix.data[j][subc] + curScale * matrix.data[i][subc];
                }
                L.data[j][pivot] = -1 * curScale;
                printMatrix(matrix);
            }
            pivot++;
        }
        //step one get matrix
        //step two set L matrix to identity matrix
        //for row 0 through end-1; subtract below to get 0
        //multiply that fraction into the L matrix

        System.out.println("L Matrix:");
        printMatrix(L);
        System.out.println("U Matrix:");
        printMatrix(matrix);
        System.out.println("P Matrix: ");
        printMatrix(P);
    }
}
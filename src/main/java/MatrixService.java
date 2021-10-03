import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class MatrixService {

    /**
     * Iterates over each column and calculates sum of elements
     */
    private static class ColumnSummator implements Callable<Integer> {

        private int fromColumn;
        private int toColumn;
        private int[][] matrix;

        /**
         * Constructor
         *
         * @param fromColumn - column index start with
         * @param toColumn   - to column index. You should process columns strong before column with index toColumn
         * @param matrix     - matrix
         */
        public ColumnSummator(int fromColumn, int toColumn, int[][] matrix) {
            // should be implemented
        }

        @Override
        public Integer call() {
            // should be implemented
        }
    }

    /**
     * Get sum of matrix elements. You should parallel work between several threads
     *
     * @param matrix   - matrix
     * @param nthreads - threads count. It is guarantee that number of matrix column is greater than nthreads.
     * @return sum of matrix elements
     */
    public int sum(int[][] matrix, int nthreads) {

        ExecutorService executorService = ...;

        // create threads and divide work between them
        // should be implemented

        int sum = 0;
        return sum;
    }
}

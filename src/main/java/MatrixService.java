import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
            this.fromColumn = fromColumn;
            this.toColumn = toColumn;
            this.matrix = matrix;
        }

        @Override
        public Integer call() {
            Integer result = 0;
            while (fromColumn <= toColumn) {
                for(int i = 0; i < matrix.length; i++) {
                    result += matrix[i][fromColumn];
                }
                ++fromColumn;
            }
            return result;
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

        if (matrix.length == 0) {
            return 0;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(nthreads);
        List<Future> futureList = new ArrayList<>();
        int fromColumn = 0;

        int partsCount;
        if (nthreads > matrix[0].length) {
            partsCount = matrix[0].length;
        } else {
            partsCount = matrix[0].length / nthreads;
        }

        int toColumn = fromColumn + partsCount - 1;

        for (int i = 0; i < nthreads; i++) {
            ColumnSummator columnSummator = new ColumnSummator(fromColumn, toColumn, matrix);
            futureList.add(executorService.submit(columnSummator));

            fromColumn = toColumn + 1;

            if (matrix[0].length <= toColumn + matrix.length / partsCount) {
                toColumn = matrix[0].length - 1;
            } else {
                toColumn = toColumn + matrix.length / partsCount;
            };
        }

        int sum = 0;
        for (Future future: futureList) {
            try {
                sum += (Integer) future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();

        return sum;
    }
}

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatrixServiceTests {

    MatrixService matrixService;
    int[][] testMatrix1;
    int[][] testMatrix2;
    int[][] testMatrix3;

    /**
     * Подготовка данных для тестов.
     */
    @BeforeEach
    void init() {
        matrixService = new MatrixService();
        testMatrix1 = new int[][]{
            {1, 1},
            {2, 2},
            {3, 3},
            {4, 4},
            {10, 10}
        };
        testMatrix2 = new int[][]{
                {1, -1, 1},
                {2, -2, 2},
                {-3, 3, 3},
                {-4, 4, 4},
                {-10, 11, 5}
        };
        testMatrix3 = new int[0][0];
    }

    /**
     * Очистка объектов после тестов.
     */
    @AfterEach
    void clean() {
        matrixService = null;
        testMatrix1 = null;
        testMatrix2 = null;
        testMatrix3 = null;
    }

    /**
     * Проверяет метод sum(). Количество потоков - 1, ожидается результат 40.
     */
    @Test
    void sum_test_40_1() throws InterruptedException {
        Assert.assertEquals(40, matrixService.sum(testMatrix1, 1));
    }

    /**
     * Проверяет метод sum(). Количество потоков - 2, ожидается результат 40.
     */
    @Test
    void sum_test_40_2() throws InterruptedException {
        Assert.assertEquals(40, matrixService.sum(testMatrix1, 2));
    }

    /**
     * Проверяет метод sum(). Количество потоков - 1, ожидается результат 16.
     */
    @Test
    void sum_test_16_1() throws InterruptedException {
        Assert.assertEquals(16, matrixService.sum(testMatrix2, 1));
    }

    /**
     * Проверяет метод sum(). Количество потоков - 1, ожидается результат 16.
     */
    @Test
    void sum_test_16_2() throws InterruptedException {
        Assert.assertEquals(16, matrixService.sum(testMatrix2, 2));
    }

    /**
     * Проверяет метод sum(). Количество потоков - 4, ожидается результат 16.
     */
    @Test
    void sum_test_16_4() throws InterruptedException {
        Assert.assertEquals(16, matrixService.sum(testMatrix2, 4));
    }

    /**
     * Проверяет метод sum(). Количество потоков - 1, ожидается результат 0.
     */
    @Test
    void sum_test_0_1() throws InterruptedException {
        Assert.assertEquals(0, matrixService.sum(testMatrix3, 1));
    }

}

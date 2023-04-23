import java.util.*;
import java.util.concurrent.*;

public class SudokuValidator {
    
    private static final int NUM_THREADS = 27;
    private static final int ROW_SIZE = 9;
    private static final int COL_SIZE = 9;
    private static final int SUBGRID_SIZE = 3;
    private static final int VALID_SUM = 45;

    private static final ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

    private static final int[][] sudokuGrid = new int[ROW_SIZE][COL_SIZE];

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Ask user to input 9*9 Sudoku solution
        System.out.println("Please enter the 9*9 Sudoku solution:");
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                sudokuGrid[i][j] = scanner.nextInt();
            }
        }

        // Create tasks to validate each row, column and sub-grid
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for (int i = 0; i < ROW_SIZE; i++) {
            tasks.add(new RowValidator(i));
        }
        for (int j = 0; j < COL_SIZE; j++) {
            tasks.add(new ColumnValidator(j));
        }
        for (int i = 0; i < ROW_SIZE; i += SUBGRID_SIZE) {
            for (int j = 0; j < COL_SIZE; j += SUBGRID_SIZE) {
                tasks.add(new SubGridValidator(i, j));
            }
        }

        // Invoke all tasks and wait for results
        try {
            boolean isValid = true;
            List<Future<Boolean>> results = executor.invokeAll(tasks);
            for (Future<Boolean> result : results) {
                if (!result.get()) {
                    isValid = false;
                    break;
                }
            }
            System.out.println("The Sudoku solution is " + (isValid ? "valid" : "invalid") + ".");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Shutdown executor
        executor.shutdown();

    }

    private static class RowValidator implements Callable<Boolean> {
        private int row;

        public RowValidator(int row) {
            this.row = row;
        }

        @Override
        public Boolean call() {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < COL_SIZE; j++) {
                int num = sudokuGrid[row][j];
                if (num < 1 || num > 9 || set.contains(num)) {
                    return false;
                }
                set.add(num);
            }
            return true;
        }
    }

    private static class ColumnValidator implements Callable<Boolean> {
        private int col;

        public ColumnValidator(int col) {
            this.col = col;
        }

        @Override
        public Boolean call() {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < ROW_SIZE; i++) {
                int num = sudokuGrid[i][col];
                if (num < 1 || num > 9 || set.contains(num)) {
                    return false;
                }
                set.add(num);
            }
            return true;
        }
    }

    private static class SubGridValidator implements Callable<Boolean> {
        private int row;
        private int col;
        public SubGridValidator(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public Boolean call() {
            Set<Integer> set = new HashSet<>();
            for (int i = row; i < row + SUBGRID_SIZE; i++) {
                for (int j = col; j < col + SUBGRID_SIZE; j++) {
                    int num = sudokuGrid[i][j];
                    if (num < 1 || num > 9 || set.contains(num)) {
                        return false;
                    }
                    set.add(num);
                }
            }
            return true;
        }
    }
}
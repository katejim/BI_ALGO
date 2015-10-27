/**
 * Created by kate on
 * 05.10.15.
 */
public class Matrix {
    private final Cell matrix[][];

    private int rows;
    private int columns;

    Matrix(int m, int n) {
        rows = m;
        columns = n;

        matrix = new Cell[m][n];
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Cell[] getRow(int idx) {
        if (idx < rows) {
            return matrix[idx];
        }
        return null;
    }

    public Cell[] getColumn(int idx) {
        if (idx < rows) {
            return matrix[idx];
        }
        return null;
    }

    public Cell getMax() {
        Cell max = new Cell(Integer.MIN_VALUE, null, 0, 0);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j].compareTo(max) > 0) {
                    max = matrix[i][j];
                }
            }
        }
        return max;
    }
}

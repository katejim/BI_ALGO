import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kate on
 * 05.10.15.
 */
public class GlobalAlignment implements IAlignment {
    final int eqWeight;
    final int notEqWeight;
    final int gap;

    public GlobalAlignment(int eqWeight, int notEqWeight, int gap) {
        this.eqWeight = eqWeight;
        this.notEqWeight = notEqWeight;
        this.gap = gap;
    }

    @Override
    public Pair<String, String> getPath(String string1, String string2) {
        Matrix matrix = generateMatrix(string1.length(), string2.length(), string1, string2);
        int rows = matrix.getRows();
        int columns = matrix.getColumns();

        List<Character> resultString1 = new ArrayList<Character>();
        List<Character> resultString2 = new ArrayList<Character>();
        Cell currentCell = matrix.getMatrix()[rows - 1][columns - 1];
        while (currentCell.getPreviousCell() != null) {
            if (currentCell.getType() == Cell.DIAG_STEP) {
                resultString1.add(string1.charAt(currentCell.getxCoordinate() - 1));
                resultString2.add(string2.charAt(currentCell.getyCoordinate() - 1));
            } else if (currentCell.getType() == Cell.LEFT_STEP) {
                resultString1.add('-');
                resultString2.add(string2.charAt(currentCell.getyCoordinate() - 1));
            } else if (currentCell.getType() == Cell.BOTTOM_STEP) {
                resultString1.add(string1.charAt(currentCell.getxCoordinate() - 1));
                resultString2.add('-');
            }

            currentCell = currentCell.getPreviousCell();
        }
        Collections.reverse(resultString1);
        Collections.reverse(resultString2);
        return new Pair<String, String>(resultString1.toString(), resultString2.toString());
    }

    @Override
    public void initalize(Matrix inMatrix, int type, double gap) {
        int rows = inMatrix.getRows();
        int columns = inMatrix.getColumns();

        if (type == IAlignment.GLOBAL_ALIGNEMENT) {
            int initalizer = 0;

            for (int i = 0; i < rows; i++) {
                inMatrix.getMatrix()[i][0] = new Cell(initalizer, null, i, 0);
                initalizer -= gap;
            }

            initalizer = 0;
            for (int i = 1; i < columns; i++) {
                inMatrix.getMatrix()[0][i] = new Cell(initalizer, null, 0, i);
                initalizer -= gap;
            }
        }
    }

    @Override
    public Matrix generateMatrix(int rows, int columns, String str1, String str2) {
        Matrix mmatrix = new Matrix(rows + 1, columns + 1);
        initalize(mmatrix, IAlignment.GLOBAL_ALIGNEMENT, gap);
        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < columns + 1; j++) {
                Pair<Double, Cell> first = new Pair<Double, Cell>(mmatrix.getMatrix()[i - 1][j - 1].getValue() +
                        Util.isEquals(str1.charAt(i - 1), str2.charAt(j - 1), eqWeight, notEqWeight), mmatrix.getMatrix()[i - 1][j - 1]);

                Pair<Double, Cell> second = new Pair<Double, Cell>(mmatrix.getMatrix()[i - 1][j].getValue() - gap, mmatrix.getMatrix()[i - 1][j]);
                Pair<Double, Cell> third = new Pair<Double, Cell>(mmatrix.getMatrix()[i][j - 1].getValue() - gap, mmatrix.getMatrix()[i][j - 1]);


                Pair<Double, Cell> result = Util.getMax(first, second, third);

                mmatrix.getMatrix()[i][j] = new Cell(result.getKey(), result.getValue(), i, j);

            }
        }
        return mmatrix;
    }
}

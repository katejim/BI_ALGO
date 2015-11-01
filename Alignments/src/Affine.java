import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kate on
 * 01.11.15.
 */
public class Affine extends Alignment {
    Matrix M;
    Matrix I;
    Matrix D;

    final int gapOpen;
    final int gapExtend;

    public Affine(int eqWeight, int notEqWeight, int gapOpen, int gapExtend) {
        super(eqWeight, notEqWeight, gapOpen);
        this.gapOpen = gapOpen;
        this.gapExtend = gapExtend;
    }

    @Override
    Pair<String, String> getPath(String string1, String string2) {
        int rows = string1.length() + 1;
        int columns = string2.length() + 1;

        initalize(rows, columns);
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                Cell iCell = getMax(I.getMatrix()[i][j - 1].updateValue(-gapExtend), M.getMatrix()[i][j - 1].updateValue(-gapOpen));
                I.getMatrix()[i][j] = new Cell(iCell.getValue(), iCell, i, j, Cell.HORIZONTAL);
                Cell dCell = getMax(D.getMatrix()[i - 1][j].updateValue(-gapExtend), M.getMatrix()[i - 1][j].updateValue(-gapOpen));
                D.getMatrix()[i][j] = new Cell(dCell.getValue(), dCell, i, j, Cell.VERTICAL);
                Cell getIDmax = getMax(I.getMatrix()[i][j], D.getMatrix()[i][j]);
                Cell mCell = getMax(getIDmax, M.getMatrix()[i - 1][j - 1].updateValue(-isEquals(string1.charAt(i - 1), string2.charAt(j - 1))));
                M.getMatrix()[i][j] = new Cell(mCell.getValue(), mCell, i, j, Cell.DIAGONAL);
            }
        }


        List<Character> resultString1 = new ArrayList<Character>();
        List<Character> resultString2 = new ArrayList<Character>();

        Cell currentCell = M.getMatrix()[rows - 1][columns - 1];
        //previously print diag type
        int matrixType = currentCell.getPreviousCell().getMatrixType();
        int curX = currentCell.getPreviousCell().getxCoordinate();
        int curY = currentCell.getPreviousCell().getyCoordinate();

        while (currentCell.getPreviousCell() != null) {
            if (matrixType == Cell.DIAGONAL) {
                resultString1.add(string1.charAt(curX - 1));
                resultString2.add(string2.charAt(curY - 1));
                curX -= 1;
                curY -= 1;
            } else if (matrixType == Cell.HORIZONTAL) {
                resultString1.add('-');
                resultString2.add(string2.charAt(curY - 1));
                curY -= 1;
            } else if (matrixType == Cell.VERTICAL) {
                resultString1.add(string1.charAt(curX - 1));
                resultString2.add('-');
                curX -= 1;
            }

            if (currentCell.getMatrixType() != matrixType) {
                currentCell = currentCell.getPreviousCell();
            }

            currentCell = currentCell.getPreviousCell();
            matrixType = currentCell.getPreviousCell().getMatrixType();
            curX = currentCell.getPreviousCell().getxCoordinate();
            curY = currentCell.getPreviousCell().getyCoordinate();
        }

        Collections.reverse(resultString1);
        Collections.reverse(resultString2);

        return new Pair<String, String>(resultString1.toString(), resultString2.toString());
    }

    private Cell getMax(Cell c1, Cell c2) {
        if (c1.getValue() > c2.getValue()) {
            return c1;
        } else {
            return c2;
        }
    }

    @Override
    Matrix initalize(int rows, int columns) {
        M = new Matrix(rows, columns);
        I = new Matrix(rows, columns);
        D = new Matrix(rows, columns);

        int initalizer = Integer.MIN_VALUE;
        Cell prev = new Cell(initalizer, null, 0, 0, Cell.DIAGONAL);
        for (int i = 0; i < rows; i++) {
            M.getMatrix()[i][0] = new Cell(initalizer, prev, i, 0, Cell.DIAGONAL);
            prev = new Cell(initalizer, prev, i, 0);
        }

        initalizer = 0;
        prev = new Cell(0, null, 0, 0, Cell.DIAGONAL);
        for (int i = 1; i < columns; i++) {
            M.getMatrix()[0][i] = new Cell(initalizer, prev, 0, i, Cell.DIAGONAL);
            prev = new Cell(initalizer, prev, 0, i);
        }

        prev = new Cell(0, null, 0, 0, Cell.VERTICAL);
        initalizer = 0;
        for (int i = 0; i < rows; i++) {
            D.getMatrix()[i][0] = new Cell(initalizer, prev, i, 0, Cell.VERTICAL);
            prev = new Cell(initalizer, prev, i, 0);
        }


        prev = new Cell(initalizer, null, 0, 0, Cell.VERTICAL);
        for (int i = 1; i < columns; i++) {
            initalizer = gapOpen + i * gapExtend;
            D.getMatrix()[0][i] = new Cell(initalizer, prev, 0, i, Cell.VERTICAL);
            prev = new Cell(initalizer, prev, 0, i);
        }

        initalizer = 0;
        prev = new Cell(initalizer, null, 0, 0, Cell.HORIZONTAL);
        for (int i = 0; i < rows; i++) {
            I.getMatrix()[i][0] = new Cell(initalizer, prev, i, 0, Cell.HORIZONTAL);
            prev = new Cell(initalizer, prev, i, 0);
        }

        prev = new Cell(0, null, 0, 0, Cell.HORIZONTAL);
        for (int i = 1; i < columns; i++) {
            initalizer = gapOpen + i * gapExtend;
            M.getMatrix()[0][i] = new Cell(initalizer, prev, 0, i, Cell.HORIZONTAL);
            prev = new Cell(initalizer, prev, 0, i);
        }


        return M;
    }
}

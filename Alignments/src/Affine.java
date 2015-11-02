import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
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


                Pair<Double, Cell> iCell = Util.getMax(
                        new Pair<Double, Cell>(M.getMatrix()[i][j - 1].getValue() + gapOpen, M.getMatrix()[i][j - 1]),
                        new Pair<Double, Cell>(D.getMatrix()[i][j - 1].getValue() + gapExtend, D.getMatrix()[i][j - 1]));


                D.getMatrix()[i][j] = new Cell(iCell.getKey(), iCell.getValue(), i, j, Cell.HORIZONTAL);


                Pair<Double, Cell> dCell = Util.getMax(
                        new Pair<Double, Cell>(M.getMatrix()[i - 1][j].getValue() + gapOpen, M.getMatrix()[i - 1][j]),
                        new Pair<Double, Cell>(I.getMatrix()[i - 1][j].getValue() + gapExtend, I.getMatrix()[i - 1][j]));

                I.getMatrix()[i][j] = new Cell(dCell.getKey(), dCell.getValue(), i, j, Cell.VERTICAL);

                Pair<Double, Cell> mCell = Util.getMax(
                        new Pair<Double, Cell>(M.getMatrix()[i - 1][j - 1].getValue() + isEquals(string1.charAt(i - 1), string2.charAt(j - 1)), M.getMatrix()[i - 1][j - 1]),
                        new Pair<Double, Cell>(I.getMatrix()[i][j].getValue(), I.getMatrix()[i][j]),
                        new Pair<Double, Cell>(D.getMatrix()[i][j].getValue(), D.getMatrix()[i][j]));

                M.getMatrix()[i][j] = new Cell(mCell.getKey(), mCell.getValue(), i, j, Cell.DIAGONAL);

            }
        }


        List<Character> resultString1 = new ArrayList<Character>();
        List<Character> resultString2 = new ArrayList<Character>();


        int i = string1.length();
        int j = string2.length();

        while (i > 0 || j > 0) {
            if (i > 0 && j > 0 &&
                    M.getMatrix()[i][j].getValue() == M.getMatrix()[i - 1][j - 1].getValue() + isEquals(string1.charAt(i - 1), string2.charAt(j - 1))) {
                resultString1.add(string1.charAt(i - 1));
                resultString2.add(string2.charAt(j - 1));
                i -= 1;
                j -= 1;
            } else if (j > 0 && M.getMatrix()[i][j].getValue() == I.getMatrix()[i][j].getValue()) {
                resultString1.add('-');
                resultString2.add(string2.charAt(j - 1));
                j -= 1;
            } else if (i > 0 && M.getMatrix()[i][j].getValue() == D.getMatrix()[i][j].getValue()) {
                resultString1.add(string1.charAt(i - 1));
                resultString2.add('-');
                i -= 1;
            }
        }


        Collections.reverse(resultString1);
        Collections.reverse(resultString2);

        return new Pair<String, String>(getStringRepresentation(resultString1), getStringRepresentation(resultString2));
    }

    private Cell getMax(Cell c1, Cell c2) {
        if (c1.getValue() >= c2.getValue()) {
            return c1;
        } else {
            return c2;
        }
    }


    private Cell getMax(Cell c1, Cell c2, Cell c3) {
        Cell temp = getMax(c1, c2);
        return getMax(temp, c3);
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

        initalizer = Integer.MIN_VALUE;
        prev = new Cell(0, null, 0, 0, Cell.DIAGONAL);
        for (int i = 1; i < columns; i++) {
            M.getMatrix()[0][i] = new Cell(initalizer, prev, 0, i, Cell.DIAGONAL);
            prev = new Cell(initalizer, prev, 0, i);
        }

        initalizer = Integer.MIN_VALUE;
        prev = new Cell(initalizer, null, 0, 0, Cell.VERTICAL);
        for (int i = 0; i < rows; i++) {
            D.getMatrix()[i][0] = new Cell(initalizer, prev, i, 0, Cell.VERTICAL);
            prev = new Cell(initalizer, prev, i, 0);
        }


        prev = new Cell(0, null, 0, 0, Cell.VERTICAL);
        for (int i = 1; i < columns; i++) {
            initalizer = gapOpen + i * gapExtend;
            D.getMatrix()[0][i] = new Cell(initalizer, prev, 0, i, Cell.VERTICAL);
            prev = new Cell(initalizer, prev, 0, i);
        }


        prev = new Cell(0, null, 0, 0, Cell.HORIZONTAL);
        for (int i = 0; i < rows; i++) {
            initalizer = gapOpen + i * gapExtend;
            I.getMatrix()[i][0] = new Cell(initalizer, prev, i, 0, Cell.HORIZONTAL);
            prev = new Cell(initalizer, prev, i, 0);
        }

        initalizer = Integer.MIN_VALUE;
        prev = new Cell(initalizer, null, 0, 0, Cell.HORIZONTAL);
        for (int i = 1; i < columns; i++) {
            I.getMatrix()[0][i] = new Cell(initalizer, prev, 0, i, Cell.HORIZONTAL);
            prev = new Cell(initalizer, prev, 0, i);
        }


        return M;
    }
}

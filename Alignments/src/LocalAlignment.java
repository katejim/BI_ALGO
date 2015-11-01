import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by kate on
 * 18.10.15.
 */
public class LocalAlignment extends Alignment {
    public LocalAlignment(int eqWeight, int notEqWeight, int gap) {
        super(eqWeight, notEqWeight, gap);
    }

    @Override
    public Pair<String, String> getPath(String string1, String string2) {
        Matrix matrix = generateMatrix(string1.length(), string2.length(), string1, string2);

        List<Character> resultString1 = new ArrayList<Character>();
        List<Character> resultString2 = new ArrayList<Character>();
        Cell currentCell = matrix.getMax();

        String firstEnd = string1.substring(currentCell.getxCoordinate());
        String secondEnd = string2.substring(currentCell.getyCoordinate());

        String nWhitespace = whiteSpaceString(Math.abs(firstEnd.length() - secondEnd.length()));

        if (firstEnd.length() > secondEnd.length()) {
            secondEnd += nWhitespace;
        } else {
            firstEnd += nWhitespace;
        }

        while ((currentCell.getxCoordinate() > 0) && (currentCell.getyCoordinate() > 0)) {
            if (currentCell.getType() == Cell.DIAGONAL) {
                resultString1.add(string1.charAt(currentCell.getxCoordinate() - 1));
                resultString2.add(string2.charAt(currentCell.getyCoordinate() - 1));
            } else if (currentCell.getType() == Cell.HORIZONTAL) {
                resultString1.add('-');
                resultString2.add(string2.charAt(currentCell.getyCoordinate() - 1));
            } else if (currentCell.getType() == Cell.VERTICAL) {
                resultString1.add(string1.charAt(currentCell.getxCoordinate() - 1));
                resultString2.add('-');
            }

            currentCell = currentCell.getPreviousCell();
        }

        String firstBegin = string1.substring(0, currentCell.getxCoordinate());
        String secondBegin = string2.substring(0, currentCell.getyCoordinate());

        nWhitespace = whiteSpaceString(Math.abs(firstBegin.length() - secondBegin.length()));

        if (firstBegin.length() > secondBegin.length()) {
            secondBegin += nWhitespace;
        } else {
            firstBegin += nWhitespace;
        }

        Collections.reverse(resultString1);
        Collections.reverse(resultString2);

        return new Pair<String, String>(firstBegin + getStringRepresentation(resultString1) + firstEnd,
                secondBegin + getStringRepresentation(resultString2) + secondEnd);
    }

    private String whiteSpaceString(int length) {
        char[] charArray = new char[length];
        Arrays.fill(charArray, ' ');
        return String.copyValueOf(charArray);
    }

    @Override
    public Matrix initalize(int rows, int columns) {
        Matrix matrix = new Matrix(rows, columns);

        int initalizer = 0;
        Cell prev = new Cell(0, null, 0, 0);
        for (int i = 0; i < rows; i++) {
            matrix.getMatrix()[i][0] = new Cell(initalizer, prev, i, 0);
            prev = new Cell(initalizer, prev, i, 0);
        }

        prev = new Cell(0, null, 0, 0);
        for (int i = 1; i < columns; i++) {
            matrix.getMatrix()[0][i] = new Cell(initalizer, prev, 0, i);
            prev = new Cell(initalizer, prev, 0, i);
        }

        return matrix;
    }
}

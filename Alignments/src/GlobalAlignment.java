import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kate on
 * 05.10.15.
 */
public class GlobalAlignment extends Alignment {

    public GlobalAlignment(int gap){
        super(gap);
    }
    public GlobalAlignment(int eqWeight, int notEqWeight, int gap) {
        super(eqWeight, notEqWeight, gap);
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
        Collections.reverse(resultString1);
        Collections.reverse(resultString2);
        return new Pair<String, String>(getStringRepresentation(resultString1), getStringRepresentation(resultString2));
    }

    @Override
    public Matrix initalize(int rows, int columns) {
        Matrix matrix = new Matrix(rows, columns);

        int initalizer = 0;


        Cell prev = new Cell(0, null, 0, 0);
        for (int i = 0; i < rows; i++) {
            matrix.getMatrix()[i][0] = new Cell(initalizer, prev, i, 0);
            prev = new Cell(initalizer, prev, i, 0);
            initalizer -= gap;
        }

        initalizer = 0;
        prev = new Cell(0, null, 0, 0);
        for (int i = 1; i < columns; i++) {
            matrix.getMatrix()[0][i] = new Cell(initalizer, prev, 0, i);
            prev = new Cell(initalizer, prev, 0, i);
            initalizer -= gap;
        }

        return matrix;
    }
}

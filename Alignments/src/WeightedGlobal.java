import javafx.util.Pair;

/**
 * Created by kate on
 * 01.11.15.
 */
public class WeightedGlobal extends GlobalAlignment {
    Matrix equalityMatrix;

    public WeightedGlobal(int gap) {
        super(gap);
        equalityMatrix = generateCompareMatrix();
    }

    @Override
    public int isEquals(char i, char j) {
        return (int) equalityMatrix.getMatrix()[getPosInMatrix(i)][getPosInMatrix(j)].getValue();
    }

    private int getPosInMatrix(char c) {
        String str = String.valueOf(c).toUpperCase();
        if (str.equals("A")) {
            return 0;
        }

        if (str.equals("C")) {
            return 1;
        }
        if (str.equals("G")) {
            return 2;
        }
        if (str.equals("T")) {
            return 3;
        }
        throw new IllegalAccessError();
    }

    // 0 - A
    // 1 - C
    // 2 - G
    // 3 - T
    private Matrix generateCompareMatrix() {
        Matrix mm = new Matrix(4, 4);
        mm.getMatrix()[0][0] = new Cell(5, null, 0, 0);
        mm.getMatrix()[0][1] = new Cell(-2, null, 0, 1);
        mm.getMatrix()[0][2] = new Cell(-1, null, 0, 2);
        mm.getMatrix()[0][3] = new Cell(-1, null, 0, 3);

        mm.getMatrix()[1][0] = new Cell(-2, null, 1, 0);
        mm.getMatrix()[1][1] = new Cell(7, null, 1, 1);
        mm.getMatrix()[1][2] = new Cell(-1, null, 1, 2);
        mm.getMatrix()[1][3] = new Cell(3, null, 1, 3);

        mm.getMatrix()[2][0] = new Cell(-1, null, 2, 0);
        mm.getMatrix()[2][1] = new Cell(-1, null, 2, 1);
        mm.getMatrix()[2][2] = new Cell(7, null, 2, 2);
        mm.getMatrix()[2][3] = new Cell(0, null, 2, 3);

        mm.getMatrix()[3][0] = new Cell(-1, null, 3, 0);
        mm.getMatrix()[3][1] = new Cell(3, null, 3, 1);
        mm.getMatrix()[3][2] = new Cell(0, null, 3, 2);
        mm.getMatrix()[3][3] = new Cell(6, null, 3, 3);

        return mm;
    }
}

/**
 * Created by kate on
 * 06.10.15.
 */
/*
represent cell in matrix, contains value and previous cell
 */
public class Cell implements Comparable<Cell> {
    private double value;
    private Cell previous;
    private final int xCoordinate;
    private final int yCoordinate;
    public final static int DIAGONAL = 0;
    public final static int HORIZONTAL = 1;
    public final static int VERTICAL = 2;
    private int matrixType;


    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public double getValue() {
        return value;
    }

    public Cell getPreviousCell() {
        return previous;
    }

    public void setValue(double value) {
        this.value = value;
    }
    public Cell updateValue(double value) {
        this.value += value;
        return this;
    }

    public void setPrevious(Cell previous) {
        this.previous = previous;
    }

    public Cell(double value, Cell previous, int xCoordinate, int yCoordinate) {
        this.value = value;
        this.previous = previous;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Cell(double value, Cell previous, int xCoordinate, int yCoordinate, int matrixType) {
        this(value, previous, xCoordinate, yCoordinate);
        this.matrixType = matrixType;
    }

    public int getType() {
        Cell previous = this.previous;
        if (previous != null) {
            if (((xCoordinate - 1) == previous.xCoordinate)
                    && ((yCoordinate - 1) == previous.yCoordinate)) {
                return DIAGONAL;
            } else if ((xCoordinate == previous.xCoordinate)
                    && ((yCoordinate - 1) == previous.yCoordinate)) {
                return HORIZONTAL;
            } else if (((xCoordinate - 1) == previous.xCoordinate)
                    && (yCoordinate  == previous.yCoordinate)) {
                return VERTICAL;
            }
        }
        return  -1;
    }

    public int getMatrixType() {
        return matrixType;
    }

    public void setMatrixType(int matrixType) {
        this.matrixType = matrixType;
    }

    @Override
    public int compareTo(Cell other) {
        return Double.compare(value, other.getValue());
    }
}

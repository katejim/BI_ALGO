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
    public final static int DIAG_STEP = 0;
    public final static int LEFT_STEP = 1;
    public final static int BOTTOM_STEP = 2;


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

    public void setPrevious(Cell previous) {
        this.previous = previous;
    }

    public Cell(double value, Cell previous, int xCoordinate, int yCoordinate) {
        this.value = value;
        this.previous = previous;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getType() {
        Cell previous = this.previous;
        if (previous != null) {
            if (((xCoordinate - 1) == previous.xCoordinate)
                    && ((yCoordinate - 1) == previous.yCoordinate)) {
                return DIAG_STEP;
            } else if ((xCoordinate == previous.xCoordinate)
                    && ((yCoordinate - 1) == previous.yCoordinate)) {
                return LEFT_STEP;
            } else if (((xCoordinate - 1) == previous.xCoordinate)
                    && (yCoordinate  == previous.yCoordinate)) {
                return BOTTOM_STEP;
            }
        }
        return  -1;
    }

    @Override
    public int compareTo(Cell other) {
        return Double.compare(value, other.getValue());
    }
}

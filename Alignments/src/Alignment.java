import javafx.util.Pair;

import java.util.List;

/**
 * Created by kate on
 * 14.10.15.
 */
abstract public class Alignment {
    final int eqWeight;
    final int notEqWeight;
    final int gap;

    public Alignment(int eqWeight, int notEqWeight, int gap) {
        this.eqWeight = eqWeight;
        this.notEqWeight = notEqWeight;
        this.gap = gap;
    }

    abstract Pair<String,String> getPath(String string1, String string2);
    abstract void initalize(Matrix inMatrix, double gap);

    public Matrix generateMatrix(int rows, int columns, String str1, String str2) {
        Matrix mmatrix = new Matrix(rows + 1, columns + 1);
        initalize(mmatrix, gap);
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

    String getStringRepresentation(List<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list) {
            builder.append(ch);
        }
        return builder.toString();
    }
}
import javafx.util.Pair;

/**
 * Created by kate on
 * 14.10.15.
 */
public interface IAlignment {
    int GLOBAL_ALIGNEMENT = 1;
    Pair<String,String> getPath(String string1, String string2);
    void initalize(Matrix inMatrix, int type, double gap);
    Matrix generateMatrix(int rows, int columns, String str1, String str2);
}

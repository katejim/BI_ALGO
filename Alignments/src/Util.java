import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by kate on
 * 05.10.15.
 */
public class Util {
    public static Pair<Double, Cell> getMax(Pair<Double, Cell> diag, Pair<Double, Cell> vertical, Pair<Double, Cell> horizontal) {
        double max = -1;

        Pair<Double, Cell> result;
        if (diag.getKey() > vertical.getKey()) {
            max = diag.getKey();
            result = diag;
        } else {
            max = vertical.getKey();
            result = vertical;
        }

        if (horizontal.getKey() > max) {
            max = horizontal.getKey();
            result = horizontal;
        }
        return result;
    }

    public static int isEquals(char i, char j, int match, int mismatch) {
        return i == j ? match : -mismatch;
    }


}

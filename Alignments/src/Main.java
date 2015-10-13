import javafx.util.Pair;

/**
 * Created by kate on
 * 05.10.15.
 */
public class Main {
    public static void main(String[] args) {
        String first = "ATTAGCTCGTCTCATTGTATGTTCATCACTCCTCCCGACAAAAAGCATGAATCAGTAGTAACACTGCT";
        String second = "TCAGGTAATAAAAAAGCCGGAGCTCCCTTTCCGGCACGTCTCATTGCCGTATCACTTCCTCCCGAAAAAGCATTAATCAGTAAAACCCGACTGCTCAGCGAGAATCGTCGAAAAGGACG";
        GlobalAlignment globalAlignment = new GlobalAlignment(1, 1, 1);
        Pair<String, String> result = globalAlignment.getPath(first, second);
        System.out.println(result.getKey() + "\n" + result.getValue());
    }
}

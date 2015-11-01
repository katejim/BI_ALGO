import javafx.util.Pair;

/**
 * Created by kate on
 * 05.10.15.
 */
public class Main {
    public static void main(String[] args) {
//        String first = "ATTAGCTCGTCTCATTGTATGTTCATCACTCCTCCCGACAAAAAGCATGACTGCT";
//        String second = "TCAGGTAATAAAAAAGCCGGAGCTCCCTTTCCGGCACGTCTCATTGCCGTATCACTTCCTCCCGAAAAAGCATTAATCAGTAAAACCCGACTGCTCAGCGAGAATCGTCGAAAAGGACG";

        String first = "TG";
        String second = "TG";


        System.out.println("GLOBAL");
        GlobalAlignment globalAlignment = new GlobalAlignment(1, 1, 1);
        Pair<String, String> result = globalAlignment.getPath(first, second);
        System.out.println(result.getKey() + "\n" + result.getValue());

        System.out.println("\nWEIGHTED GLOBAL");
        WeightedGlobal wglobalAlignment = new WeightedGlobal(1);
        Pair<String, String> resultWeighted = wglobalAlignment.getPath(first, second);
        System.out.println(resultWeighted.getKey() + "\n" + resultWeighted.getValue());

        System.out.println("\nLOCAL");
        LocalAlignment localAlignment = new LocalAlignment(1, 1, 1);
        Pair<String, String> resultLocal = localAlignment.getPath(first, second);
        System.out.println(resultLocal.getKey() + "\n" + resultLocal.getValue());


        System.out.println("\nAFFINE");
        Affine affineAlignment = new Affine(1, -1, -4, -1);
        Pair<String, String> resultAffine = affineAlignment.getPath(first, second);
        System.out.println(resultAffine.getKey() + "\n" + resultAffine.getValue());
    }
}

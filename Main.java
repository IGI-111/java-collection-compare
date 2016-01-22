import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        // add collections to test here
        List<Collection<String>> toBench = new ArrayList<Collection<String>>();
        toBench.add(new ArrayList<String>());
        toBench.add(new LinkedList<String>());

        //TODO: generate dataset
        List<String> dataset = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            dataset.add("test1");
        }

        for (Collection<String> c : toBench) {
            Benchmark<String> b = new Benchmark<String>(c, dataset);
            Benchmark.Result result = b.bench();
            System.out.println(result.collection + " " +
                    " Insert: " + result.insertTime +
                    " Delete: " + result.deleteTime +
                    " Search: " + result.searchTime);
        }
    }
}

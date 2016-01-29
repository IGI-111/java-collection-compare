import java.util.Collection;
import java.util.List;

public class Benchmark<T> {
    private Collection<T> collection;
    private List<T> dataset;
    public Benchmark(Collection<T> toBench, List<T> dataset) {
        this.collection = toBench;
        this.dataset = dataset;
    }
    private long benchInsert() { // in ms
        return time(()-> {
            collection.addAll(dataset);
        });
    }
    private long benchDelete() { // in ns
        return time(()-> {
            collection.removeAll(dataset);
        });
    }
    private long benchSearch() { // in ns
        return time(()-> {
            collection.containsAll(dataset);
        });
    }

    public Result bench() {
        long insertTime = benchInsert();
        long searchTime = benchSearch();
        long deleteTime = benchDelete();
        return new Result(
                   collection.getClass().getSimpleName(),
                   insertTime,
                   deleteTime,
                   searchTime);
    }

    private long time(Runnable fun) {
        long before = System.nanoTime();
        fun.run();
        long after = System.nanoTime();
        return after - before;
    }

    public class Result {
        public final String collection;
        public final long insertTime;
        public final long deleteTime;
        public final long searchTime;
        Result(String collection,
               long insertTime,
               long deleteTime,
               long searchTime) {
            this.insertTime = insertTime;
            this.deleteTime = deleteTime;
            this.searchTime = searchTime;
            this.collection = collection;
        }

    }
}

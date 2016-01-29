import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.List;
import java.util.Arrays;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        // add collections to test here
        List<Collection<String>> toBench = new ArrayList<Collection<String>>();
        toBench.add(new ArrayList<String>()); // Array
        toBench.add(new LinkedList<String>()); // List
        toBench.add(new TreeSet<String>()); // AVL
        toBench.add(new HashSet<String>()); // Hash table
        toBench.add(new BinarySearchTree<String>()); // BST
        // toBench.add(new AVLTree<String>());          // custom AVL

        for (int dataSize = 1; dataSize <= 6; ++dataSize) {
            for (int alphaLength = 1; alphaLength <= 26; ++alphaLength) {
                System.out.println("Word length: " + dataSize);
                System.out.println("Alphabet size: " + alphaLength);

                List<String> dataset = Alphabet.getAllPossibleWords(alphaLength, dataSize);

                for (Collection<String> c : toBench) {
                    Benchmark<String> b = new Benchmark<String>(c, dataset);
                    Benchmark<String>.Result result = b.bench();
                    System.out.println(result.collection + " Insert: " + result.insertTime
                        + " Remove: " + result.deleteTime + " Find: " + result.searchTime);
                }
                System.out.println();
            }
        }
    }
}

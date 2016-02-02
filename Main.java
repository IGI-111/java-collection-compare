import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main {
    private static Map<String, Map<String, BufferedWriter>> generateWriters(
        String path, List<Collection<String>> list) {
        Map<String, Map<String, BufferedWriter>> result =
            new HashMap<String, Map<String, BufferedWriter>>();
        try {
            for (Collection<String> c : list) {
                String name = c.getClass().getSimpleName();

                Map<String, BufferedWriter> map =
                    new HashMap<String, BufferedWriter>();
                map.put("Insert", new BufferedWriter(new FileWriter(
                                      path + name + "_Insert.out")));
                map.put("Find", new BufferedWriter(
                                    new FileWriter(path + name + "_Find.out")));
                map.put("Remove", new BufferedWriter(new FileWriter(
                                      path + name + "_Remove.out")));

                result.put(name, map);
            }

        } catch (IOException e) {
            System.out.println("Cannot open file in path: " + path);
        }
        return result;
    }
    private static Map<String, Map<String, BufferedWriter>> writers;
    private static void writeResultToFile(
        Benchmark<String>.Result result, int size) {
        try {
            writers.get(result.collection)
                .get("Insert")
                .write(size + " " + result.insertTime);
            writers.get(result.collection).get("Insert").newLine();
            writers.get(result.collection).get("Insert").flush();

            writers.get(result.collection)
                .get("Remove")
                .write(size + " " + result.deleteTime);
            writers.get(result.collection).get("Remove").newLine();
            writers.get(result.collection).get("Remove").flush();

            writers.get(result.collection)
                .get("Find")
                .write(size + " "
                    + result.searchTime);
            writers.get(result.collection).get("Find").newLine();
            writers.get(result.collection).get("Find").flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // add collections to test here
        List<Collection<String>> toBench = new ArrayList<Collection<String>>();
        toBench.add(new ArrayList<String>()); // Array
        toBench.add(new LinkedList<String>()); // List
        toBench.add(new TreeSet<String>()); // AVL
        toBench.add(new HashSet<String>()); // Hash table
        toBench.add(new BinarySearchTree<String>()); // BST
        toBench.add(new AVLTree<String>()); // custom AVL

        if (args.length > 0) {
            String path = args[0] + "/";
            writers = generateWriters(path, toBench);
        }

        for (int dataSize = 1; dataSize <= 6; ++dataSize) {
            for (int alphaLength = 1; alphaLength <= 26; ++alphaLength) {
                List<String> dataset =
                    Alphabet.getAllPossibleWords(alphaLength, dataSize);

                System.out.println("Size: " + dataset.size());

                for (Collection<String> c : toBench) {
                    Benchmark<String> b = new Benchmark<String>(c, dataset);
                    Benchmark<String>.Result result = b.bench();

                    // print result
                    System.out.println(result.collection + " Insert: "
                        + result.insertTime + " Remove: " + result.deleteTime
                        + " Find: " + result.searchTime);

                    // write result
                    if (args.length > 0) {
                        writeResultToFile(result, dataset.size());
                    }
                }
                System.out.println();
            }
        }
    }
}

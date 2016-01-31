import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.lang.StringBuilder;
import java.lang.Character;

public class Alphabet {
    private static Alphabet alphabet = null;
    private StringBuilder builder = new StringBuilder();
    private List<String> list = new ArrayList<String>();
    private int alphabetLength = 0;
    private int wordLength = 0;

    private void fillList() {
        for (int i = 0; i < wordLength; ++i) builder.append("a");

        do {
            list.add(builder.toString());
        } while (increment());
    }

    private boolean increment() {
        int i = wordLength - 1;
        while (true) {
            if (builder.charAt(i) == ('a' + alphabetLength - 1)) {
                if (i == 0)
                    return false;
                builder.setCharAt(i, 'a');
                --i;
            } else {
                builder.setCharAt(i, (char) (builder.charAt(i) + 1));
                return true;
            }
        }
    }

    public static List<String> getAllPossibleWords(
        int alphabetLength, int wordLength) {
        if (alphabet == null)
            alphabet = new Alphabet();
        alphabet.alphabetLength = alphabetLength;
        alphabet.wordLength = wordLength;
        alphabet.list = new ArrayList<String>();
        alphabet.builder.setLength(0);
        alphabet.fillList();
        Collections.shuffle(alphabet.list);
        return alphabet.list;
    }
}

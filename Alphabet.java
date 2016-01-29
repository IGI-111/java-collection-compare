import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.lang.StringBuilder;
import java.lang.Character;

public class Alphabet {
    private static StringBuilder builder = new StringBuilder();
    private final List<String> list;
    private final int alphabetLength;
    private final int wordLength;

    private Alphabet(int alphabetLength, int wordLength) {
        this.alphabetLength = alphabetLength;
        this.wordLength = wordLength;
        list = new ArrayList<String>();
    }

    private void fillList() {
        for (int i = 0 ; i < wordLength ; ++i) builder.append("a");

        do {
            list.add(builder.toString());
        } while (increment());
    }

    private boolean increment() {
        int i = wordLength-1;
        while(true){
            if(builder.charAt(i) == ('a' + alphabetLength)){
                if (i == 0) return false;
                builder.setCharAt(i, 'a');
                --i;
            } else {
                builder.setCharAt(i, (char) (builder.charAt(i) + 1));
                return true;
            }
        }
    }

    public static List<String> getAllPossibleWords(int length, int wordLength) {
        Alphabet alpha = new Alphabet(length, wordLength);
        alpha.fillList();
        Collections.shuffle(alpha.list);
        return alpha.list;
    }
}

import java.util.HashSet;
import java.util.Set;

/**
 * word class.
 * @author Ajinkya Nimbalkar Andrew ID: animbalk
 *
 */
public class Word implements Comparable<Word> {
    /**
     * string part of the word.
     */
    private String word;
    /**
     * list of line numbers.
     */
    private Set<Integer> index;
    /**
     * frequency of occurence of the word.
     */
    private int frequency;
    // TODO implement methods below.
    /**
     * constructor method.
     * @param str word string
     */
    public Word(String str) {
        word = str;
        frequency = 1;
        index = new HashSet<Integer>();
    }
    /**
     * getter method for word.
     * @return String word
     */
    public String getWord() {
        return word;
    }
    /**
     * getter method for frequency.
     * @return int frequency value
     */
    public int getFrequency() {
        return frequency;
    }
    /**
     * setter method for word.
     * @param str word string.
     */
    public void setWord(String str) {
        word = str;
    }
    /**
     * setter method for frequency.
     * @param freq frequency value.
     */
    public void setFrequency(int freq) {
        frequency = freq;
    }
    /**
     * add another line number for the word.
     * @param line line number value.
     */
    public void addToIndex(Integer line) {
        index.add(line);
    }
    /**
     * getter method for the set of line numbers.
     * @return Set<Integer>
     */
    public Set<Integer> getIndex() {
        return index;
    }
    @Override
    /**
     * comparing based on alphabetical ordering.
     * @param arg0 word to compare to.
     * @return value depending on comparison.
     */
    public int compareTo(Word arg0) {
        // TODO Auto-generated method stub
        if (word.compareTo(arg0.word) > 0) {
            return 1;
        } else if (word.compareTo(arg0.word) < 0) {
            return -1;
        } else {
            return 0;
        }
    }
    @Override
    /**
     * overwriting a toString method.
     * @return string value of word.
     */
    public String toString() {
        String str = new String(word.toString() + " "
                + Integer.toString(frequency) + index);
        return str;
    }
}

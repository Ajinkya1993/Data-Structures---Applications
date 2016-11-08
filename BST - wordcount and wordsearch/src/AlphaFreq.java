import java.util.Comparator;
/**
 * class for alphabetical and frequency comparator.
 * @author Ajinkya Nimbalkar Andrew ID: animbalk
 *
 */
public class AlphaFreq implements Comparator<Word> {
    @Override
    /**
     * method to compare two words.
     * @param word1 first word
     * @param word2 second word
     * @return appropriately signed int.
     */
    public int compare(Word word1, Word word2) {
        // TODO Auto-generated method stub
        String w1 = word1.getWord();
        String w2 = word2.getWord();
        int result = w1.toLowerCase().compareTo(w2.toLowerCase());
        if (result == 0) {
            Integer i1 = word1.getFrequency();
            Integer i2 = word2.getFrequency();
            result = i1.compareTo(i2);
        }
        return result;
    }


}

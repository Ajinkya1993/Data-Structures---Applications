import java.util.Comparator;
/**
 * class for the frequency comparator.
 * @author Ajinkya Nimbalkar Andrew ID: animbalk
 *
 */
public class Frequency implements Comparator<Word> {
    @Override
    /**
     * method to compare two words
     * @param word1 first word.
     * @param word2 second word.
     * @return appropriately signed int.
     */
    public int compare(Word word1, Word word2) {
        // TODO Auto-generated method stub
        //  System.out.println("word being compared " + word1 + " " + word2);
        return -Integer.compare(word1.getFrequency(), word2.getFrequency());
    }
}

import java.util.Comparator;
/**
 * class for alphabetical comaprator.
 * @author Ajinkya Nimbalkar Andrew ID: animbalk
 *
 */
public class IgnoreCase implements Comparator<Word> {

    @Override
    /**
     * method to compare two words.
     * @param word1 first word.
     * @param word2 second word.
     * @return appropriately signed int.
     */
    public int compare(Word word1, Word word2) {
        // TODO Auto-generated method st
        //System.out.println("word being compared " + word1 + " " + word2);
        return word1.getWord().toLowerCase().compareTo(word2.getWord().toLowerCase());
    }
}

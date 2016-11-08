import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
/**
 * class for different functions.
 * @author Ajinkya Nimbalkar Andrew ID: animbalk
 *
 */
public class Index {
    /**
     * builds a biary tree of words from a text file.
     * @param fileName name of teh file.
     * @return binary tree.
     */
    public BST<Word> buildIndex(String fileName) {
        //throw new RuntimeException("Implement this!");
        if (fileName == null) {
            return null;
        }
        File filename = new File(fileName);
        BST<Word> bst = new BST<Word>();
        Scanner scanner = null;
        int numlines = 0;
        try {
            scanner = new Scanner(filename, "latin1");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                numlines += 1;
                String[] wordsFromText = line.split("\\W");
                for (String word : wordsFromText) {
                    //String lword = word.toLowerCase();
                    Word newword = new Word(word);
                    if (isword(newword)) {
                        //System.out.println("reached here");
                        Word currword = bst.search(newword);
                        if (currword == null) {
                            newword.addToIndex(numlines);
                            bst.insert(newword);
                        } else {
                            currword.setFrequency(currword.getFrequency() + 1);
                            currword.addToIndex(numlines);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return bst;
    }
    /**
     * builds a binary tree from a file with the specified comaparator.
     * @param fileName name of the file.
     * @param comparator specified comparator.
     * @return binary tree.
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        //throw new RuntimeException("Implement this!");
        if (fileName == null) {
            return null;
        }
        File filename = new File(fileName);
        BST<Word> bst = new BST<Word>(comparator);
        Scanner scanner = null;
        int numlines = 0;
        try {
            scanner = new Scanner(filename, "latin1");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                numlines += 1;
                String[] wordsFromText = line.split("\\W");
                for (String word : wordsFromText) {
                    String lword = word.toLowerCase();
                    Word newword = new Word(lword);
                    if (isword(newword)) {
                        Word currword = bst.search(newword);
                        if (currword == null) {
                            newword.addToIndex(numlines);
                            bst.insert(newword);
                        } else {
                            currword.setFrequency(currword.getFrequency() + 1);
                            currword.addToIndex(numlines);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return bst;
    }
    /**
     * builds a binary tree out of a list of words.
     * @param list list of words.
     * @param comparator specified comaparator.
     * @return binary tree.
     */
    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        //throw new RuntimeException("Implement this!");
        BST<Word> bst = new BST<Word>(comparator);
        for (Word word : list) {
            if (isword(word)) {
                Word currword = bst.search(word);
                if (currword == null) {
                    bst.insert(word);
                } else {
                    continue;
                    //currword.setfreq(currword.getfreq()+1);
                }
            }
        }
        return bst;
    }
    /**
     * sorts a tree into a list alphabetically.
     * @param tree binary tree.
     * @return sorted list.
     */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        //throw new RuntimeException("Implement this!");
        ArrayList<Word> list = new ArrayList<Word>();
        Iterator<Word> it = tree.iterator();
        while (it.hasNext()) {
            list.add(it.next());
        }
        list = alphasorthelper(list);
        return list;
    }
    /**
     * helper method to sort arraylist using insertion sort alphabetically.
     * @param list arraylist to be sorted.
     * @return sorted list.
     */
    private ArrayList<Word> alphasorthelper(ArrayList<Word> list) {
        for (int i = 1; i < list.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (list.get(j).getWord().compareTo(list.get(j - 1).getWord()) < 0) {
                    swap(list, j, j - 1);
                } else {
                    break;
                }
            }
        }
        return list;
    }
    /**
     * method to swap two elements in a list.
     * @param list given list.
     * @param i first index.
     * @param j second index.
     */
    private void swap(ArrayList<Word> list, int i, int j) {
        Word temp = list.get(i);
        list.add(i, list.get(j));
        list.add(j, temp);
    }
    /**
     * sorts the tree into a list based on frequency.
     * @param tree binary tree.
     * @return sorted list.
     */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
      //throw new RuntimeException("Implement this!");
        ArrayList<Word> list = new ArrayList<Word>();
        Iterator<Word> it = tree.iterator();
        while (it.hasNext()) {
            //System.out.println("has next returned true");
            list.add(it.next());
        }
        //System.out.println("freq list size: " + list.size());
        list = freqsorthelper(list);
        return list;
    }
    /**
     * helper method to sort arraylist using insertion sort by frequency.
     * @param list arraylist to be sorted.
     * @return sorted list.
     */
    private ArrayList<Word> freqsorthelper(ArrayList<Word> list) {
        //System.out.println("reached here");
        for (int i = 1; i < list.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (Integer.compare(list.get(j).getFrequency(), list.get(j - 1).getFrequency()) > 0) {
                    Word temp = list.get(j);
                    list.set(j, list.get(j - 1));
                    list.set(j - 1, temp);
                    //swap(list, j, j-1);
                } else {
                    break;
                }
            }
        }
        return list;
    }
    /**
     * method to give a list of word having highest freq.
     * @param tree tree to be sorted.
     * @return highest freq list
     */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        //throw new RuntimeException("Implement this!");
        ArrayList<Word> list = new ArrayList<Word>();
        list = sortByFrequency(tree);
        int maxfreq = list.get(0).getFrequency();
        ArrayList<Word> newlist = new ArrayList<Word>();
        newlist.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getFrequency() == maxfreq) {
                newlist.add(list.get(i));
            } else {
                break;
            }
        }
        return newlist;
    }
    /**
     * helper method to check if word is legal or not.
     * @param word1 word to check for.
     * @return boolean true if legal word.
     */
    private static boolean isword(Word word1) {
        // wrod should already be in lower case.
        String word = word1.getWord();
        if (word == null || word.length() == 0) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if ((word.charAt(i) >= 97 && word.charAt(i) <= 122)
            || (word.charAt(i) >= 65 && word.charAt(i) <= 90)) {
                // character is an alphabet
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}

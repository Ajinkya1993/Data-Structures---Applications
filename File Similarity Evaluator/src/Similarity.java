import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Similarity Class Homework 5.
 * @author Ajinkya Nimbalkar
 *
 */
public class Similarity {
    /**
     * variable to store the file name.
     */
    private File filename;
    /**
     * variable to store number of words in file.
     */
    private BigInteger numwords = new BigInteger("0");
    /**
     * variable to store number of lines in file.
     */
    private int numlines = 0;
    /**
     * variable to store the number of words withour dups.
     */
    private int numuniquewords = 0;
    /**
     * varible to keep updating the square of euclidean norm.
     */
    private double norm = 0;
    //private BigDecimal testnorm =  new BigDecimal(0);
    /**
     * variable to store the norm of the file being compared to.
     */
    private double norm2 = 0;
    /**
     * map to store words and their respective frequencies.
     */
    private Map<String, BigInteger> wordfreq = new HashMap<String, BigInteger>();
    /**
     * constructor method.
     * @param string String input.
     */
    public Similarity(String string) {
        norm = 0;
        norm2 = 0;
        numlines = 0;
        numuniquewords = 0;
        numwords = new BigInteger("0");
        BigInteger sqrnorm = new BigInteger("0");
        if (string == null || string.length() == 0) {
            return;
        }
        String[] wordsFromText = string.split("\\W");
        for (String word : wordsFromText) {
            String lword = word.toLowerCase();
            if (isword(lword)) {
                numwords = numwords.add(BigInteger.valueOf(1));
                BigInteger freq = wordfreq.get(lword);
                if (freq != null) {
                    // word already present
                    sqrnorm = sqrnorm.subtract(freq.multiply(freq));
                    freq = freq.add(BigInteger.valueOf(1L));
                    sqrnorm = sqrnorm.add(freq.multiply(freq));
                    wordfreq.put(lword, freq);
                    //numwords += 1;
                } else {
                    // new word
                    //numwords += 1;
                    numuniquewords += 1;
                    sqrnorm = sqrnorm.add(BigInteger.valueOf(1L));
                    wordfreq.put(lword, BigInteger.valueOf(1L));
                }
            }
        }
        //System.out.println("value of sqrsum is: " + sqrnorm);
        double temp = sqrnorm.doubleValue();
        norm = (double) Math.sqrt(temp);
    }
    /**
     * overloaded constructor method.
     * @param file File to read from.
     */
    public Similarity(File file) {
        filename = file;
        norm = 0;
        norm2 = 0;
        numlines = 0;
        numuniquewords = 0;
        numwords = new BigInteger("0");
        readfile();
    }
    /**
     * returns number of lines in file.
     * @return number of lines.
     */
    public int numOfLines() {
        return numlines;
    }
    /**
     * helper method for reading file and initializing data.
     */
    private void readfile() {
        Scanner scanner = null;
        BigInteger sqrnorm = new BigInteger("0");
        if (filename == null) {
            return;
        }
        try {
            scanner = new Scanner(filename, "latin1");
            while (scanner.hasNextLine()) {
                numlines += 1;
                String line = scanner.nextLine();
                String[] wordsFromText = line.split("\\W");
                for (String word : wordsFromText) {
                    String lword = word.toLowerCase();
                    if (isword(lword)) {
                        numwords = numwords.add(BigInteger.valueOf(1));
                        //System.out.print(word);
                        BigInteger freq = wordfreq.get(lword);
                        if (freq != null) {
                            // word already present
                            sqrnorm = sqrnorm.subtract(freq.multiply(freq));
                            freq = freq.add(BigInteger.valueOf(1L));
                            sqrnorm = sqrnorm.add(freq.multiply(freq));
                            wordfreq.put(lword, freq);
                        } else {
                            // new word
                            numuniquewords += 1;
                            sqrnorm = sqrnorm.add(BigInteger.valueOf(1L));
                            wordfreq.put(lword, BigInteger.valueOf(1L));
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
        //System.out.println("value of sqrsum is: " + sqrnorm);
        double temp = sqrnorm.doubleValue();
        norm = (double) Math.sqrt(temp);
    }
    /**
     * method to return the number of words in file.
     * @return number of words.
     */
    public BigInteger numOfWords() {
        return numwords;
    }
    /**
     * method to return number of unique words.
     * @return number of unique words.
     */
    public int numOfWordsNoDups() {
        return numuniquewords;
    }
    /**
     * method to return the euclidean norm.
     * @return double.
     */
    public double euclideanNorm() {
        return norm;
    }
    /**
     * method to compute the dot product.
     * @param map from a different file.
     * @return double dot product value.
     */
    public double dotProduct(Map<String, BigInteger> map) {
        if (map == null || map.size() == 0 || wordfreq == null || wordfreq.size() == 0) {
            return 0;
        }
        BigInteger dotprod = new BigInteger("0");
        BigInteger sqrnorm2 = new BigInteger("0");
        norm2 = 0;
        for (Map.Entry<String, BigInteger> entry: map.entrySet()) {
            if (entry == null) {
                continue;
            }
            BigInteger val1 = entry.getValue();
            if (val1 == null) {
                continue;
            }
            sqrnorm2 = sqrnorm2.add(val1.multiply(val1));
            BigInteger val2 = wordfreq.get(entry.getKey());
            if (val2 != null) {
                dotprod = dotprod.add(val1.multiply(val2));
            }
        }
        double temp = sqrnorm2.doubleValue();
        norm2 = Math.sqrt(temp);
        return dotprod.doubleValue();
    }
    /**
     * method to return the distance between 2 files.
     * @param map Map of the file being comapared.
     * @return distance double value.
     */
    public double distance(Map<String, BigInteger> map) {
        if (map == null || map.size() == 0 || wordfreq.size() == 0 || wordfreq == null) {
            return Math.PI / 2.0;
        }
        double dist = 0;
        double dotprod = dotProduct(map);
        dist = dotprod / (norm * norm2);
        dist = dotprod / (norm * norm2);
        if ((dotprod - norm * norm2) >= 0 && (dotprod - norm * norm2) < 0.0000001) {
            dist = 1.0;
        }
        dist = Math.acos(dist);
        return dist;
    }
    /**
     * helper method to check if word is legal or not.
     * @param word String.
     * @return boolean true if legal word.
     */
    private boolean isword(String word) {
        // wrod should already be in lower case.
        if (word == null || word.length() == 0) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) < 97 || word.charAt(i) > 122) {
                // if any character is not a lowercase alphabet
                return false;
            }
        }
        return true;
    }
    /**
     * method to return the map of words and frequencies.
     * @return Map<String, Integer>
     */
    public Map<String, BigInteger> getMap() {
        return wordfreq;
    }
}

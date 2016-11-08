import java.io.File;

public class mytestcode {

    /**
     * Test program to check Similarity with files of large size.
     * @param args arguments
     */
    public static void main(String[] args) {
        Similarity file1 = new Similarity(new String("I believe that is the case of correct won't be able to do  23sb_ 23b3_ 45sdb 3b tes3ts  believe that case correct of"));
        printOutput(file1);

        Similarity file2 = new Similarity(new String("I believe that is the case of correct won't be able to do  23sb_ 23b3_ 45sdb 3b tes3ts  believe that case correct of"));
        printOutput(file2);

        System.out.println(file1.dotProduct(file2.getMap()) + " dot product.");
        System.out.println(file1.distance(file2.getMap()) + " distance.");
    }

    /**
     * Prints some values of Similarity object.
     * @param sim Similarity object to deal with
     */
    private static void printOutput(Similarity sim) {
        System.out.println(sim.numOfLines() + " lines.");    
        System.out.println(sim.numOfWords() + " words.");
        System.out.println(sim.numOfWordsNoDups() + " distinct words.");
        System.out.println(sim.euclideanNorm() + " Euclidean norm.\n");
    }
}

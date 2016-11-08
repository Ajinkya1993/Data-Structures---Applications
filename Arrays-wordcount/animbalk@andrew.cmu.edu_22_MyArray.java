/**
 * Implementation of my own array class.
 * @author Ajinkya Nimbalkar
 *
 */
public class MyArray {
    /**
     * my array.
     */
    private String[] strarray;
    /**
     * keeps count of the size.
     */
    private int count;
    /**
     * keeps count of the capacity of the array.
     */
    private int capacity;
    /**
     * constructor method.
     */
    public MyArray() {
        capacity = 1;
        strarray = new String[capacity];
        count = 0;
    }
    /**
     * overloaded constructor method.
     * @param initialCapacity initial capcity for the array.
     */
    public MyArray(int initialCapacity) {
        strarray = new String[initialCapacity];
        capacity = initialCapacity;
        count = 0;
    }
    /**
     * adds the string to the array.
     * @param text the string to be addded
     */
    public void add(String text) {
        if (text == null) {
            return;
        } else if (text.length() > 0) {
            for (int i = 0; i < text.length(); i++) {
                if (!(((text.charAt(i)) <= 'Z'
                        && (text.charAt(i)) >= 'A')
                        || ((text.charAt(i)) >= 'a'
                        && (text.charAt(i)) <= 'z'))) {
                    return;
                }
            }
            if (count < capacity) {
                strarray[count] = text;
                count += 1;
            } else if (count >= capacity) {
                String[] temp;
                if (capacity == 0) {
                    temp = new String[1];
                    capacity = 1;
                } else {
                    capacity = capacity * 2;
                    temp = new String[capacity];
                }
                for (int i = 0; i < count; i++) {
                    temp[i] = strarray[i];
                }
                strarray = new String[capacity];
                strarray = temp;
                strarray[count] = text;
                count += 1;
            }
        }
    }
    /**
     * searches for a particular string in the array.
     * @param key the string to be searched for
     * @return true if key is present and false if not
     */
    public boolean search(String key) {
        for (int i = 0; i < count; i++) {
            if (strarray[i].equals(key)) {
                return true;
            }
        }
        return false;
    }
    /**
     * returns the size of the array being used.
     * @return the size of the array being used
     */
    public int size() {
        return count;
    }
    /**
     * returns the total capacity of the array.
     * @return the total capacity of the array
     */
    public int getCapacity() {
        return capacity;
    }
    /**
     * displays the contents of the array.
     */
    public void display() {
        for (int i = 0; i < count; i++) {
            System.out.print(strarray[i] + " ");
        }
    }
    /**
     * removes any duplicate entries in the array.
     */
    public void removeDups() {
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                if (i == j) {
                    continue;
                } else {
                    if (strarray[i].equals(strarray[j])) {
                        for (int k = j; k < (count - 1); k++) {
                            strarray[k] = strarray[k + 1];
                        }
                        strarray[count - 1] = new String();
                        count -= 1;
                        j -= 1;
                    }
                }
            }
        }
    }
    /*
    public static void main(String[] args) {
        MyArray test = new MyArray();
        for (int i=0; i<20; i++) {
            test.add("Ajinkya");
        }
        test.display();
        System.out.println("");
        System.out.println("Size: " + String.valueOf(test.size()));
        System.out.println("Capacity: " + String.valueOf(test.getCapacity()));
        System.out.println(String.valueOf(test.search("Shubham")));
        test.removeDups();
        test.display();
    }
    */

}

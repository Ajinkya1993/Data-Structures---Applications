/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework Assignment 4
 * HashTable Implementation with linear probing
 *
 * Andrew ID: animbalk
 * @author Ajinkya Nimbalkar
 */
public class MyHashTable implements MyHTInterface {
    /**
     * keeps a count of the size of the hashtable.
     */
    private int size = 0;
    /**
     * keeps a count of the number of collision instances.
     */
    private int collisions = 0;
    /**
     * Allow rehasing when 1.
     */
    private int rehashing = 0;
    /**
     * helps prevent collision adds for duplicate instances.
     */
    private int multipleadd = 0;
    /**
     * class for each member of the hashtable.
     */
    private static class DataItem {
        /**
         * string value of the data item.
         */
        private String data;
        /**
         * keeps track of frequency of data item.
         */
        private int frequency;
        /**
         * constructor method.
         */
        public DataItem() {
            data = null;
            frequency = 0;
        }
        /**
         * getter method for data variable.
         * @return data String.
         */
        public String getdata() {
            return data;
        }
        /**
         * getter method for frequency variable.
         * @return frequency int value.
         */
        public int getfreq() {
            return frequency;
        }
        /**
         * sets data variable.
         * @param str the string the data variable should beset to.
         */
        public void setdata(String str) {
            data = str;
        }
        /**
         * sets frequency toa certain value.
         * @param freq int value.
         */
        public void setfreq(int freq) {
            frequency = freq;
        }
    }
    /**
     * array of dataitems.
     */
    private DataItem[] myarray;
    /**
     * default constructor.
     */
    public MyHashTable() {
        // default size of 10
        myarray = new DataItem[10];
    }
    /**
     * overloaded constructor.
     * @param size initial capacity for the hash table.
     */
    public MyHashTable(int size) {
        myarray = new DataItem[size];
    }
    /**
     * Instead of using String's hashCode, you are to implement your own here.
     * You need to take the table length into your account in this method.
     *
     * In other words, you are to combine the following two steps into one step.
     * 1. converting Object into integer value
     * 2. compress into the table using modular hashing (division method)
     *
     * Helper method to hash a string for English lowercase alphabet and blank,
     * we have 27 total. But, you can assume that blank will not be added into
     * your table. Refer to the instructions for the definition of words.
     *
     * For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 + 19*27^0 = 60,337
     *
     * But, to make the hash process faster, Horner's method should be applied as follows;
     *
     * var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0 can be rewritten as
     * (((var4*n + var3)*n + var2)*n + var1)*n + var0
     *
     * Note: You must use 27 for this homework.
     *
     * However, if you have time, I would encourage you to try with other
     * constant values than 27 and compare the results but it is not required.
     * @param input input string for which the hash value needs to be calculated
     * @return int hash value of the input string
     */
    private int hashFunc(String input) {
        // TODO implement this
        if (input == null) {
            return 0;
        }
        int  sum = 0;
        int n = input.length();
        for (int i = 0; i < n; i++) {
            int ch = (int) (input.charAt(i)) - 96;
            if (i == (n - 1)) {
                // avoids overflow.
                sum = (sum + ch) % myarray.length;
            } else {
                // this step is used to avoid overflow.
                sum = (sum + ch) * 27 % myarray.length;
                }
        }
        if (sum < 0) {
            sum = -sum;
        }
        return (int) (sum % myarray.length);
    }
    /**
     * method to check if a number is prime.
     * @param number the number to be checked.
     * @return true if prime. false if not.
     */
    private boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; (i * i) <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * doubles array length and rehash items whenever the load factor is reached.
     */
    private void rehash() {
        // TODO implement this
        // rehashing should take into account active items only.
        // taking load factor as: (active) / arraylength as of now
        // check for (size + 1) so as to prevent crossing load factor at any time.
        if (myarray != null && (double) (size + 1) / (double) (myarray.length) <= 0.5) {
            // no need of rehashing
            return;
        }
        int newsize;
        //check if initial array is valid.
        if (myarray == null || myarray.length == 0) {
            newsize = 10;
        } else {
            newsize = myarray.length * 2;
            while (!(isPrime(newsize))) {
                newsize += 1;
            }
        }
        System.out.println("Rehashing " + (size + 1)
                + " items. New size is " + newsize);
        DataItem[] temparray = new DataItem[myarray.length];
        temparray = myarray;
        myarray = new DataItem[newsize];
        size = 0;
        // rehashing. therefore begin a new count for collisions.
        collisions = 0;
        for (int i = 0; i < temparray.length; i++) {
            if (temparray[i] == null) {
                continue;
            } else {
                while (temparray[i].getfreq() > 0) {
                    insert(temparray[i].getdata());
                    multipleadd = 1;
                    temparray[i].setfreq(temparray[i].getfreq() - 1);
                }
                multipleadd = 0;
            }
        }
    }
    @Override
    /**
     * inserts the string value as a dataitem into the hashtable.
     */
    public void insert(String value) {
        // TODO Auto-generated method stub
        // call rehash before every insert
        rehash();
        if (value == null) {
            return;
        }
        // check if word is valid or not
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) < 97 || value.charAt(i) > 122) {
                // if any character is not a lowercase alphabet
                return;
            }
        }
        int arraysize = myarray.length;
        // find index by hash function
        int index = hashValue(value) % arraysize;
        int newind = (index + 1) % arraysize;
        // helps prevent duplicate collision addition.
        int dupcollision = 0;
        if (myarray[index] == null) {
            // empty position
            myarray[index] = new DataItem();
            myarray[index].setdata(value);
            myarray[index].setfreq(1);
            size += 1;
        } else if (myarray[index].getdata().equals(value)
                && myarray[index].getfreq() > 0) {
            // data at index position is same as value
            myarray[index].setfreq(myarray[index].getfreq() + 1);
        } else if (myarray[index].getfreq() == -1
                || !(myarray[index].getdata().equals(value))) {
            // collision instance
            if (rehashing == 0 && multipleadd == 0) {
                if (hashValue(myarray[index].getdata()) == index) {
                    // collision instance only if the hash value
                    // of both strings is same.
                    collisions += 1;
                    dupcollision = 1;
                }
            }
            // found = 1 if empty position is found to insert.
            int found = 0;
            // searching next empty position
            while (newind != index) {
                if (myarray[newind] == null) {
                    // empty position
                    myarray[newind] = new DataItem();
                    myarray[newind].setdata(value);
                    myarray[newind].setfreq(1);
                    size += 1;
                    found = 1;
                    break;
                } else if (myarray[newind].getdata().equals(value)
                        && myarray[newind].getfreq() > 0) {
                    // data at index position is same as value
                    myarray[newind].setfreq(myarray[newind].getfreq() + 1);
                    // ignoring duplicate collision instance.
                    if (dupcollision == 1) {
                        collisions -= 1;
                        dupcollision = 0;
                    }
                    found = 1;
                    break;
                } else {
                    newind = (newind + 1) % arraysize;
                }
            }
            if (found == 1) {
                // item was inserted successfully
                return;
            }
            while (found == 0) {
                // no empty position found
                // entire array has been traversed
                // so now insert at deleted position
                if (myarray[newind].getfreq() == -1) {
                    // found deleted position
                    myarray[newind].setdata(value);
                    myarray[newind].setfreq(1);
                    size += 1;
                    found = 1;
                    return;
                } else {
                    newind = (newind + 1) % arraysize;
                }
            }
        }
    }
    /**
     * returns the size of the hashtable.
     * @return size of the hash table.
     */
    public int size() {
        // TODO Auto-generated method stub
        if (myarray == null) {
            return -1;
        } else {
            return size;
        }
    }
    /**
     * prints the contents of the hash table in a specific format.
     */
    public void display() {
        // TODO Auto-generated method stub
        if (myarray == null || myarray.length == 0) {
            // empty array
            System.out.print("**");
            return;
        }
        for (int i = 0; i < myarray.length; i++) {
            if (myarray[i] == null) {
                // empty position
                System.out.print("**");
            } else if (myarray[i].getfreq() > 0) {
                // occupied position
                System.out.print("[" + myarray[i].getdata()
                        + ", " + myarray[i].getfreq() + "]");
            } else {
                //deleted position
                System.out.print("#DEL#");
            }
            if (i != myarray.length - 1) {
                System.out.print(" ");
            }
        }
        // this step is needed **
        System.out.println();
    }
    /**
     * checks if the string is contained in the hash table.
     * @param key String input.
     * @return true if present. false if not.
     */
    public boolean contains(String key) {
        // TODO Auto-generated method stub
        if (myarray == null || size == 0) {
            return false;
        }
        // check if key is a valid word
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) < 97 || key.charAt(i) > 122) {
                // if any character is not a lowercase alphabet
                return false;
            }
        }
        int index = hashValue(key) % myarray.length;
        int currindex = index;
        do {
            if (myarray[currindex] == null) {
                // empty position
                return false;
            } else if (myarray[currindex].getfreq() == -1
                    && myarray[currindex].getdata().equals(key)) {
                return false;
            } else if (myarray[currindex].getfreq() == -1
                    || !(myarray[currindex].getdata().equals(key))) {
                //deleted item
                currindex += 1;
                currindex = currindex % myarray.length;
            } else if (myarray[currindex].getdata().equals(key)) {
                return true;
            }
        } while (((currindex) % myarray.length != index));
        return false;
    }
    /**
     * gives the number of collsions.
     * @return number of cllisions.
     */
    public int numOfCollisions() {
        // TODO Auto-generated method stub
        return collisions;
    }
    /**
     * returns the index.
     * @param value String input.
     * @return the index by of a string using hashfunc method.
     */
    public int hashValue(String value) {
        // TODO Auto-generated method stub
        return hashFunc(value) % myarray.length;
    }
    /**
     * shows the frequency of a particular string in the hash table.
     * @param key String input.
     * @return frequency.
     */
    public int showFrequency(String key) {
        // TODO Auto-generated method stub
        if (myarray == null || size == 0) {
            return 0;
        }
        int index = hashValue(key) % myarray.length;
        int currindex = index;
        do {
            if (myarray[currindex] == null) {
                // empty position
                return 0;
            } else if (myarray[currindex].getfreq() == -1) {
                // deleted item
                currindex += 1;
                currindex = currindex % myarray.length;
            } else if (myarray[currindex].getdata().equals(key)) {
                return myarray[currindex].getfreq();
            } else {
                currindex += 1;
                currindex = currindex % myarray.length;
            }
        } while (((currindex) % myarray.length != index));
        return 0;
    }
    /**
     * removes a particular string from the hash table.
     * @param key String input.
     * @return the string if it was present. else null.
     */
    public String remove(String key) {
        // TODO Auto-generated method stub
     // TODO Auto-generated method stub
        if (myarray == null || size == 0) {
            return null;
        }
        // check if key is a valid word
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) < 97 || key.charAt(i) > 122) {
                // if any character is not a lowercase alphabet
                return null;
            }
        }
        int index = hashValue(key) % myarray.length;
        int currindex = index;
        do {
            if (myarray[currindex] == null) {
                // empty position
                return null;
            } else if (myarray[currindex].getfreq() == -1) {
                // deleted item
                currindex += 1;
                currindex = currindex % myarray.length;
            } else if (myarray[currindex].getdata().equals(key)) {
                // found string. now delete.
                myarray[currindex].setfreq(-1);
                size -= 1;
                return myarray[currindex].getdata();
            }
        } while (((currindex) % myarray.length != index));
        return null;
    }
}

/**
 * Class compares the time efficiency of 3 different algorithms.
 * @author Ajinkya Nimbalkar animbalk
 *
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class Josephus2 {

    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithAD(int size, int rotation) {
        if(size<=0) {
            throw new RuntimeException("size should be greater than zero");
        }
        if(rotation <= 0) {
            throw new RuntimeException("rotation should be greater than zero");
        }
        Deque<Integer> ad = new ArrayDeque<>();
        for(int i=size; i>=1; i--) {
            ad.push(i);
        }
        if(size == 1) {
            return ad.pop();
        }
        //int count = 0;
        //count += rotation;
        //ad.remove(count);
        int count = 0;
        int sizecount = size;
        int survivor=0;
        while(sizecount>1) {
            for(Iterator itr = ad.iterator(); itr.hasNext();) {
                {
                    if(ad.size() > 1) {
                        itr.next();
                        //System.out.println("value of itr is: " + String.valueOf(itr.next()));
                        count += 1;
                        count = count % rotation;
                        //System.out.println("value of count is: " + String.valueOf(count));
                        if(count == 0) {
                            itr.remove();
                            //itr.next();
                            //ad.remove(itr.next());
                            sizecount --;
                        }
                        else {
                            //itr.next();
                        }
                        
                        //System.out.println(ad.toString());
                    }
                        
                    else {
                        //System.out.println("size of queue = " + String.valueOf(ad.size()));
                        survivor = ad.pop();
                        return survivor;                   
                    }
                }
            }
        }          
        survivor = ad.pop();
        return survivor;
        //System.out.println("size of queue = " + String.valueOf(ad.size()));
        //return ad.pop();
    }
    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLL(int size, int rotation) {
        if(size<=0) {
            throw new RuntimeException("size should be greater than zero");
        }
        if(rotation <= 0) {
            throw new RuntimeException("rotation should be greater than zero");
        }
        Deque<Integer> ll = new LinkedList<>();
        for(int i=1; i<=size; i++) {
            ll.add(i);
        }
        if(size == 1) {
            return ll.removeFirst();
        }
        int count = 0;
        int sizecount = size;
        int survivor = 0;
        while(sizecount>1) {
            for (Iterator itr = ll.iterator(); itr.hasNext();) {
                if(ll.size() > 1) {
                    itr.next();
                    count += 1;
                    count = count % rotation;
                    //System.out.println("value of count is: " + String.valueOf(count));
                    if(count == 0) {
                        itr.remove();
                        //itr.next();
                        //ad.remove(itr.next());
                        sizecount --;
                    }
                    //System.out.println(ll.toString());
                
                }
                else {
                    //System.out.println("size of queue = " + String.valueOf(ll.size()));
                    survivor = ll.removeFirst();
                    return survivor;                   
                }
            }
        }
        survivor = ll.removeFirst();
        return survivor;
            
    }

    /**
     * Uses LinkedList class to find the survivor's position.
     * However, do NOT use the LinkedList as Queue/Deque
     * Instead, use the LinkedList as "List"
     * That means, it uses index value to find and remove a person to be executed in the circle
     *
     * Note: Think carefully about this method!!
     * When in doubt, please visit one of the office hours!!
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLLAt(int size, int rotation) {
        if(size<=0) {
            throw new RuntimeException("size should be greater than zero");
        }
        if(rotation <= 0) {
            throw new RuntimeException("rotation should be greater than zero");
        }
        
        List<Integer> ll = new LinkedList<>();
        for(int i=1; i<=size; i++) {
            ll.add(i);
        }
        if(size == 1) {
            return ll.remove(0);
        }
        int count = 0;
        int sizecount = size;
        //int numremoved = 0;
        while(sizecount>1) {
            int tmpcount = sizecount;
            count = count % rotation;
            int index = 0;
            for (int i=1; i<=tmpcount; i++) {
                if(ll.size()>1) {
                    count += 1;
                    if((count % rotation) == 0) {
                        ll.remove(index);
                        sizecount --;
                        index--;
                    }
                    index += 1;
                }
            }
        }
        System.out.println(ll);
        return ll.remove(0);
        
    }

}
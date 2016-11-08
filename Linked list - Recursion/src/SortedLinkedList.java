public class SortedLinkedList implements MyListInterface{
    
    class Node {

        private String data;
        private Node next;
        
        public Node(String val, Node nextnow) {
        data = val;
        next = nextnow;
        }
        
    }
    private Node head;
     /**
     * constructor method.
     */
    public SortedLinkedList() {
        head = null;
    }
    
    public SortedLinkedList(String[] unsorted) {
        if (unsorted.length > 0) {
            if(unsorted[0] != null) {
                head = new Node(unsorted[0], null);
            }
        if(unsorted.length > 1) {
            addingval(unsorted,1);
        }
        } 
        
    }
    
    public void addingval (String[] unsorted,int ind) {
        add(unsorted[ind]);
        ind++;
        if(ind < unsorted.length) {              
            addingval(unsorted,ind);
        }
    }

    @Override
    public void add(String value) {
        if (value == null) {
        return;
        } 
        if(contains(value)) {
            return;
        }
        
        Node item=new Node(value,null);
        Node currentNode=head;
        Node previousNode=null;
        
        if (head != null) {
            addsort(previousNode,currentNode,item);
        }  else if (head == null) {
            head = item;
        }
    }
    private void addsort(Node previousNode,Node currentNode,Node item) {
        if ((item.data).compareTo(currentNode.data) > 0) {
            if(currentNode.next != null) {
                previousNode=currentNode;
                currentNode=currentNode.next;
                addsort(previousNode,currentNode,item);
            } else {
                currentNode.next = item;
                item.next = null;
                return;
            }
            
        }
        
        else if((item.data).compareTo(currentNode.data) < 0) {
            if (previousNode != null) {    //insert bw prev and curr
                previousNode.next=item;
                item.next=currentNode;
            } else {    //insert in beginning
                item.next=head;
                head=item;
            }
        }
    }

    public boolean contains(String testValue) {
        if( head == null || testValue == null) {
            return false;
        }
        else if ( head != null ) {
            return itercontain (head,testValue);
    }
        return false;
    }

    private boolean itercontain(Node temp, String testValue) {
        // TODO Auto-generated method stub
        if( temp.next != null && (temp.data).equals(testValue)) {
            return true;
            }
            else if (temp.next != null) {
                temp=temp.next;
                return itercontain (temp,testValue);
            }
            return false;
    }

    public void display() {
        // TODO Auto-generated method stub
        System.out.print("[");
            display(head);
            System.out.print("]");
        }  
    
    private void display(Node temp) {
        // TODO Auto-generated method stub
        if( temp != null ) {
        if (temp.next == null) {
        System.out.print(temp.data);    
        } else {
            System.out.print(temp.data + ", ");
            temp=temp.next;
            display(temp);
            }
        }
    }

    public String removeFirst() {
        if (head == null) {
            return null;
        }
        else if (head != null && head.next != null) { //only 1 element
            String temp=head.data;
            head=head.next;
            return temp;
        } else if (head != null && head.next == null) {
            String str = head.data;
            head=null;
            return str;
        }
        return null;
        // TODO Auto-generated method stub
    }

    public String removeAt(int i) {
        if ( i >= 0) {
            if (i == 0) {
                return removeFirst();
            } else if (i >= size()) {
                System.out.println("RETURNING 0");
                return null;
            } else {
                int count=1;
                return removeAt(count,head,i);
            }
        }
        System.out.println("RETURNING 1");
        return null;
    }
    private String removeAt(int count,Node temp,int i) {
        if( count != i) {
        count++;
        return removeAt(count,temp.next,i);
            } else if (count == i) {
                String temp_now=temp.next.data;
                temp.next=temp.next.next;
                return temp_now;
            }
        System.out.println("RETURNING 2");
        return null;
    }
    

    public int size() {
        // TODO Auto-generated method stub
        int count=0;
        Node temp=head;
        if (head == null) {
            System.out.println("reaching here");
            return count;
        } else if (head != null) {
            count += 1;
            count = sizeiter(temp,count);
            return count;
        }
        return 1;
    }
    
    private int sizeiter(Node temp,int count) {
        // TODO Auto-generated method stub
        if(temp.next != null) {
            temp=temp.next;
            count++;
            count = sizeiter(temp,count);
        } else if (temp.next == null) {
            return count;
        }
        return count;
    }

    
    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        if (head == null) {
            return true;
        } else {
        return false;
        }
    }
}


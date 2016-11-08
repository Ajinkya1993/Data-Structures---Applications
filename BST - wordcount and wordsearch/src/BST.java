import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * class for binary tree operations.
 * @author Ajinkya Nimbalkar Andrew ID: animbalk
 *
 * @param <T> the crux data of the node.
 */
public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {
    /**
     * root of the tree.
     */
    private Node<T> root;
    /**
     * specified comparator.
     */
    private Comparator<T> comparator;
    /**
     * constructor fot the bst class.
     */
    public BST() {
        this(null);
    }
    /**
     * constructor for the binary tree class.
     * @param comparator specified comparator.
     */
    public BST(Comparator<T> comparator) {
        this.comparator = comparator;
        root = null;
    }
    /**
     * method returns the specified comparator.
     * @return comparator.
     */
    public Comparator<T> comparator() {
        return comparator;
    }
    /**
     * returns the root of the bianry tree.
     * @return T crux data.
     */
    public T getRoot() {
        //throw new RuntimeException("Implement this");
        if (root == null) {
            return null;
        } else {
            return root.data;
        }
    }
    /**
     * calculates teh height of the tree.
     * @return integer value of the height.
     */
    public int getHeight() {
        //throw new RuntimeException("Implement this recursively");
        int numnodes = getNumberOfNodes();
        if (root == null) {
            return 0;
        }
        int height = heighthelper(root);
        return height;
    }
    /**
     * method to help with the height calculation.
     * @param current pointer to current node.
     * @return current height.
     */
    private int heighthelper(Node<T> current) {
        int hl = 0;
        int hr = 0;
        if (current == null) {
            return 0;
        }
        if (current.left != null) {
            hl += 1;
            hl += heighthelper(current.left);
        }
        if (current.right != null) {
            hr += 1;
            hr += heighthelper(current.right);
        }
        if (hl > hr) {
            return hl;
        } else {
            return hr;
        }
    }
    /**
     * return the number of nodes in the binary tree.
     * @return number of nodes.
     */
    public int getNumberOfNodes() {
        //throw new RuntimeException("Implement this recursively");
        int count = 0;
        if (root == null) {
            return 0;
        }
        count = numnodeshelper(root);
        count += 1; // for root node
        return count;
    }
    /**
     * helper method for number of nodes.
     * @param current pointer to the current node.
     * @return current value of number of nodes.
     */
    private int numnodeshelper(Node<T> current) {
        int count = 0;
        if (current == null) {
            return 0;
        }
        if (current.left != null) {
            count += 1;
            //System.out.println("left node: " + count + " " + current.left.data);
            count += numnodeshelper(current.left);
        }
        if (current.right != null) {
            count += 1;
            //System.out.println("right node: " + count + " " + current.right.data);
            count += numnodeshelper(current.right);
        }
        return count;
    }
    @Override
    /**
     * method to search for a particular data in the tree.
     * @param toSearch the node to be searched for.
     * @return returns the searched node.
     */
    public T search(T toSearch) {
        //throw new RuntimeException("Implement this recursively");
        //  base case
        if (root == null) {
            return null;
        }
        Node<T> current = root;
        T found;
        if (comparator == null) {
            found = searchhelper(current, toSearch);
        } else {
            found = searchhelpercomparator(current, toSearch);
        }
        return found;
    }
    /**
     * method to help search without comparator.
     * @param current pointer ti the current node.
     * @param tosearch the node to be searched for.
     * @return searched node.
     */
    private T searchhelper(Node<T> current, T tosearch) {
        if (current == null) {
            return null;
        }
        //System.out.println((current.data == null));
        if (current.data.compareTo(tosearch) < 0) {
            current = current.right;
            return searchhelper(current, tosearch);
        } else if (current.data.compareTo(tosearch) > 0) {
            current = current.left;
            return searchhelper(current, tosearch);
        }
        return current.data;
    }
    /**
     * method to help search with specified comparator.
     * @param current current node
     * @param tosearch node to be searched
     * @return node searched
     */
    private T searchhelpercomparator(Node<T> current, T tosearch) {
        if (current == null) {
            return null;
        }
        if (comparator.compare(current.data, tosearch) < 0) {
            current = current.right;
            return searchhelper(current, tosearch);
        } else if (comparator.compare(current.data, tosearch) > 0) {
            current = current.left;
            return searchhelper(current, tosearch);
        }
        return current.data;
    }
    @Override
    /**
     * inserts a node at the appropriate position in the tree.
     * @param toInsert the node to be inserted.
     */
    public void insert(T toInsert) {
        //throw new RuntimeException("Implement this recursively");
        // base case
        if (root == null) {
            root = new Node<T>(toInsert);
            return;
        }
        Node<T> parent = root;
        if (comparator == null) {
            toinserthelper(parent, toInsert);
        } else {
            toinserthelpercomparator(parent, toInsert);
        }
    }
    /**
     * helper method for toInsert method.
     * @param parent the parent node.
     * @param toInsert the node to be isnerted.
     */
    private void toinserthelper(Node<T> parent, T toInsert) {
        Node<T> current;
        boolean isleftchild;
        if (parent.data.compareTo(toInsert) < 0) {
            current = parent.right;
            isleftchild = false;
        } else if (parent.data.compareTo(toInsert) > 0) {
            current = parent.left;
            isleftchild = true;
        } else {
            return;
        }
        if (current == null) {
            if (isleftchild) {
                parent.left = new Node<T>(toInsert);
            } else if (!isleftchild) {
                parent.right = new Node<T>(toInsert);
            }
            return;
        } else {
            toinserthelper(current, toInsert);
        }
    }
    /**
     * helper method for toInsert method with comparator.
     * @param parent parent node.
     * @param toInsert the node to be isnerted.
     */
    private void toinserthelpercomparator(Node<T> parent, T toInsert) {
        Node<T> current;
        boolean isleftchild;
        if (comparator.compare(parent.data, toInsert) < 0) {
            current = parent.right;
            isleftchild = false;
        } else if (comparator.compare(parent.data, toInsert) > 0) {
            current = parent.left;
            isleftchild = true;
        } else {
            return;
        }
        if (current == null) {
            if (isleftchild) {
                parent.left = new Node<T>(toInsert);
            } else if (!isleftchild) {
                parent.right = new Node<T>(toInsert);
            }
            return;
        } else {
            toinserthelper(current, toInsert);
        }
    }
    //@Override
    /**
     * method to return an iterator for the binary tree.
     * @return iterator.
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int count = 0;
            private TreeSet<T> myset = new TreeSet<T>();
            private Iterator<T> it;
            public boolean hasNext() {
                if (count == 0) {
                    count = 1;
                    if (root == null) {
                        return false;
                    }
                    addtotreeset(myset, root);
                    it = myset.iterator();
                }
                return it.hasNext();
            }
            public T next() {
                if (count == 0) {
                    count = 1;
                    if (root == null) {
                        return null;
                    }
                    addtotreeset(myset, root);
                    it = myset.iterator();
                }
                return it.next();
            }
            private void addtotreeset(TreeSet<T> myset, Node<T> current) {
                if (current == null) {
                    return;
                }
                myset.add(current.data);
                addtotreeset(myset, current.left);
                addtotreeset(myset, current.right);
            }
        };
    }
    /**
     * node class.
     * @author Ajinkya Nimbalkar Andrew ID: animbalk.
     *
     * @param <T> the main data for the node.
     */
    private static class Node<T> {
        /**
         * data part of the node.
         */
        private T data;
        /**
         * left node.
         */
        private Node<T> left;
        /**
         * right node.
         */
        private Node<T> right;
        /**
         * constructor method.
         * @param data data
         */
        Node(T data) {
            this(data, null, null);
        }
        /**
         * constructor method.
         * @param data data
         * @param left left node.
         * @param right right node.
         */
        Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
        @Override
        /**
         * overriding the toString method.
         * @return string representation of the word.
         */
        public String toString() {
            return data.toString();
        }
    }
}

package edu.auburn.bmb0136.comp2210.module4.assignment4;

import java.util.Iterator;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Brandon B (bmb0136@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

    //////////////////////////////////////////////////////////
    // Do not change the following three fields in any way. //
    //////////////////////////////////////////////////////////

    /** References to the first and last node of the list. */
    Node front;
    Node rear;

    /** The number of nodes in the list. */
    int size;

    /////////////////////////////////////////////////////////
    // Do not change the following constructor in any way. //
    /////////////////////////////////////////////////////////

    /**
     * Instantiates an empty LinkedSet.
     */
    public LinkedSet() {
        front = null;
        rear = null;
        size = 0;
    }


    //////////////////////////////////////////////////
    // Public interface and class-specific methods. //
    //////////////////////////////////////////////////

    ///////////////////////////////////////
    // DO NOT CHANGE THE TO STRING METHOD //
    ///////////////////////////////////////
    /**
     * Return a string representation of this LinkedSet.
     *
     * @return a string representation of this LinkedSet
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (T element : this) {
            result.append(element).append(", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }


    ///////////////////////////////////
    // DO NOT CHANGE THE SIZE METHOD //
    ///////////////////////////////////
    /**
     * Returns the current size of this collection.
     *
     * @return  the number of elements in this collection.
     */
    public int size() {
        return size;
    }

    //////////////////////////////////////
    // DO NOT CHANGE THE IS EMPTY METHOD //
    //////////////////////////////////////
    /**
     * Tests to see if this collection is empty.
     *
     * @return  true if this collection contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }


    /**
     * Ensures the collection contains the specified element. Neither duplicate
     * nor null values are allowed. This method ensures that the elements in the
     * linked list are maintained in ascending natural order.
     *
     * @param  element  The element whose presence is to be ensured.
     * @return true if collection is changed, false otherwise.
     */
    public boolean add(T element) {
        if (element == null) {
            return false;
        }

        if (isEmpty()) {
            Node n = new Node(element);
            front = rear = n;
            size = 1;
            return true;
        }

        // Try to add at the start or end
        // This also makes adding to either end O(1)
        Node left = front;
        Node right = rear;
        while (left.element.compareTo(right.element) <= 0) {
            int cmpL = element.compareTo(left.element);
            int cmpR = element.compareTo(right.element);

            // Duplicates check
            if (cmpL == 0 || cmpR == 0) {
                return false;
            }

            // If less than the front, add before it
            if (cmpL < 0) {
                Node temp = new Node(element);
                temp.next = left;
                temp.prev = left.prev;
                // If there is a node before left, update it's next value (otherwise it will be == left)
                if (left.prev != null) {
                    left.prev.next = temp;
                } else { // At the start
                    front = temp;
                }
                left.prev = temp;
                size++;
                return true;
            }

            // If greater than the rear, add after it
            if (cmpR > 0) {
                Node temp = new Node(element);
                temp.prev = right;
                temp.next = right.next;
                // If there is a node after right, update it's prev value (otherwise it will be == right)
                if (right.next != null) {
                    right.next.prev = temp;
                } else { // At the end
                    rear = temp;
                }
                right.next = temp;
                size++;
                return true;
            }
            left = left.next;
            right = right.prev;
            if (left == null || right == null) {
                return false;
            }
        }

        // If the value goes in the middle of the list
        // /!\ left and right are swapped!
        if (right.element.compareTo(element) < 0 && left.element.compareTo(element) > 0) {
            Node n = new Node(element);
            n.prev = right;
            right.next = n;
            n.next = left;
            left.prev = n;
            size++;
            return true;
        }

        return false;
    }

    /**
     * Ensures the collection does not contain the specified element.
     * If the specified element is present, this method removes it
     * from the collection. This method, consistent with add, ensures
     * that the elements in the linked lists are maintained in ascending
     * natural order.
     *
     * @param   element  The element to be removed.
     * @return  true if collection is changed, false otherwise.
     */
    public boolean remove(T element) {
        if (isEmpty()) {
            return false;
        }

        if (size == 1) {
            if (element.compareTo(front.element) == 0) {
                size = 0;
                front = rear = null;
                return true;
            }
            return false;
        }

        // If this looks familiar, it is because it is just the add code
        // But I changed adding to removing

        Node left = front;
        Node right = rear;

        while (left.element.compareTo(right.element) <= 0) {
            int cmpL = element.compareTo(left.element);
            int cmpR = element.compareTo(right.element);

            if (cmpL == 0) {
                if (left.next != null) {
                    left.next.prev = left.prev;
                }
                if (left.prev != null) {
                    left.prev.next = left.next;
                } else { // At the start
                    front = front.next;
                }
                size--;
                return true;
            }

            if (cmpR == 0) {
                if (right.prev != null) {
                    right.prev.next = right.next;
                }
                if (right.next != null) {
                    right.next.prev = right.prev;

                } else { // At the end
                    rear = rear.prev;
                }
                size--;
                return true;
            }

            // If less than the front, then it is not there
            if (cmpL < 0) {
                return false;
            }

            // If greater than the rear, then it is not there
            if (cmpR > 0) {
                return false;
            }

            left = left.next;
            right = right.prev;
        }
        return false;
    }


    /**
     * Searches for specified element in this collection.
     *
     * @param   element  The element whose presence in this collection is to be tested.
     * @return  true if this collection contains the specified element, false otherwise.
     */
    public boolean contains(T element) {
        for (T t : this) {
            if (element.compareTo(t) == 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(Set<T> s) {
        for (T t : this) {
            if (!s.contains(t)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(LinkedSet<T> s) {
        if (size != s.size) {
            return false;
        }

        // If two linked sets are equal then each element is in the same place
        // So basically this is just an array equals

        Node thisN = front;
        Node otherN = s.front;

        while (thisN != null) {
            if (thisN.element.compareTo(otherN.element) != 0) {
                return false;
            }

            thisN = thisN.next;
            otherN = otherN.next;
        }

        return true;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(Set<T> s){
        LinkedSet<T> newSet = new LinkedSet<>();
        // This is O(n)
        for (T t : this) {
            addToEnd(newSet, t);
        }
        // This is the O(n^2) part
        for (T t : s) {
            newSet.add(t);
        }
        return newSet;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(LinkedSet<T> s){
        LinkedSet<T> newSet = new LinkedSet<>();
        if (isEmpty() || s.isEmpty()) {
            for (T t : (isEmpty() ? s : this)) {
                addToEnd(newSet, t);
            }
            return newSet;
        }

        // This is really complex but it is O(n)
        // It only has "loops" through each set once
        // The "looping" is split up across multiple steps
        // I put an excessive amount of comments to try and help
        // Good luck :)

        // Flip order to allow for the assumption below
        if (front.element.compareTo(s.front.element) > 0) {
            return s.union(this);
        }

        // The aforementioned assumption (besides being sorted):
        //          |--s----- ?
        // |----------this--- ?
        // |--------x <- add these to newSet (up to the first element of s)

        // EDGE CASE: disjoint sets
        //                 |---s--- ?
        // |----this----|
        // Check for this
        if (rear.element.compareTo(s.front.element) < 0) {
            // Since there is no overlap, the code is easy
            // And because we just add to the end, its O(n1+n2)
            // AKA O(n)
            for (T t : this) {
                addToEnd(newSet, t);
            }
            for (T t : s) {
                addToEnd(newSet, t);
            }
            return newSet;
        }

        // Add the elements mentioned in the above assumption
        Node thisN = front;
        Node otherN = s.front;
        while (thisN.element.compareTo(otherN.element) < 0) {
            addToEnd(newSet, thisN.element);
            thisN = thisN.next;
        }

        boolean isCase1 = rear.element.compareTo(otherN.element) <= 0;
        // Case 1:
        //          |--s------|    (Set A)
        // |----------this-------| (Set B)
        //          |---------| < add these (to the last element of s)
        //                    x--| < then these

        // Case 2:
        //          |--s----------| (Set B)
        // |----------this----|     (Set A)
        //          |---------| < add these
        //                    x----| < then these

        // Since they are so similar, we can use common code:
        // 1. add from both up to the end of set A
        // 2. add the rest from set B
        Node setANode;
        do { // Neither set is empty, so thisN and otherN are not null

            int cmp = thisN.element.compareTo(otherN.element);

            if (cmp == 0) {
                addToEnd(newSet, thisN.element); // either one will work
            } else {
                T a = cmp < 0 ? thisN.element : otherN.element;
                T b = cmp < 0 ? otherN.element : thisN.element;
                addToEnd(newSet, a);
                addToEnd(newSet, b);
            }

            thisN = thisN.next;
            otherN = otherN.next;
            setANode = isCase1 ? otherN : thisN;
        } while (setANode != null);

        Node setBNode = isCase1 ? thisN : otherN;
        while (setBNode != null) {
            addToEnd(newSet, setBNode.element);
            setBNode = setBNode.next;
        }

        return newSet;
    }

    /**
     * Returns a set that is the intersection of this set and the parameter set.
     *
     * @return  a set that contains elements that are in both this set and the parameter set
     */
    public Set<T> intersection(Set<T> s) {
        LinkedSet<T> newSet = new LinkedSet<>();
        if (isEmpty() || s.isEmpty()) {
            return newSet;
        }
        for (T t : this) {
            if (s.contains(t)) {
                // Since we are looping over this (a linked set)
                // All values of t will be in order
                addToEnd(newSet, t);
            }
        }
        return newSet;
    }

    /**
     * Returns a set that is the intersection of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in both
     *            this set and the parameter set
     */
    public Set<T> intersection(LinkedSet<T> s) {
        LinkedSet<T> newSet = new LinkedSet<>();

        Node thisN = front;
        Node otherN = s.front;

        while (thisN != null) {
            // While this looks like it would be O(n^2)
            // We do not go back to the start of the other set on each iteration
            // So this O(N) operation only happens once
            // However it gets broken up over (possibly) multiple iterations
            while (otherN != null) {
                int cmp = otherN.element.compareTo(thisN.element);
                // Wait for thisN to catch up
                if (cmp > 0) {
                    break;
                }
                if (cmp == 0) {
                    addToEnd(newSet, thisN.element);
                    break;
                }
                otherN = otherN.next;
            }
            thisN = thisN.next;
        }

        return newSet;
    }


    /**
     * Returns a set that is the complement of this set and the parameter set.
     *
     * @return  a set that contains elements that are in this set but not the parameter set
     */
    public Set<T> complement(Set<T> s) {
        Set<T> newSet = new LinkedSet<>();
        for (T t : this) {
            if (!s.contains(t)) {
                newSet.add(t);
            }
        }
        return newSet;
    }


    /**
     * Returns a set that is the complement of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in this
     *            set but not the parameter set
     */
    public Set<T> complement(LinkedSet<T> s) {
        LinkedSet<T> newSet = new LinkedSet<>();
        if (isEmpty()) {
            return newSet;
        }

        Node thisN = front;
        Node otherN = s.front;

        while (thisN != null) {
            boolean doAdd = true;
            while (otherN != null) {
                int cmp = otherN.element.compareTo(thisN.element);
                if (cmp == 0) {
                    doAdd = false;
                    break;
                }
                if (cmp > 0) {
                    break;
                }
                otherN = otherN.next;
            }
            if (doAdd) {
                addToEnd(newSet, thisN.element);
            }
            thisN = thisN.next;
        }

        return newSet;
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in ascending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node n = front;

            @Override
            public boolean hasNext() {
                return size != 0 && n != null;
            }

            @Override
            public T next() {
                T e = n.element;
                n = n.next;
                return e;
            }
        };
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in descending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {
            private Node n = rear;

            @Override
            public boolean hasNext() {
                return size != 0 && n != null;
            }

            @Override
            public T next() {
                T e = n.element;
                n = n.prev;
                return e;
            }
        };
    }


    /**
     * Returns an iterator over the members of the power set
     * of this LinkedSet. No specific order can be assumed.
     *
     * @return  an iterator over members of the power set
     */
    public Iterator<Set<T>> powerSetIterator() {
        final Set<T> parent = this;
        return new Iterator<Set<T>>() {
            private final int totalCount = 1 << size;
            private int setNumber = 0;

            @Override
            public boolean hasNext() {
                return setNumber < totalCount;
            }

            @Override
            public Set<T> next() {
                Set<T> set = new LinkedSet<>();
                int i = 0;
                for (T t : parent) {
                    int mask = 1 << i;
                    if ((setNumber & mask) != 0) {
                        set.add(t);
                    }
                    i++;
                }
                setNumber++;
                return set;
            }
        };
    }



    //////////////////////////////
    // Private utility methods. //
    //////////////////////////////

    // Would make this static but Node is not static and I cannot change it so ¯\_(ツ)_/¯
    private void addToEnd(LinkedSet<T> set, T t) {
        Node n = new Node(t);
        n.prev = set.rear;
        if (set.rear != null) {
            set.rear.next = n;
        }
        set.rear = n;
        if (set.front == null) {
            set.front = n;
        }
        set.size++;
    }


    ////////////////////
    // Nested classes //
    ////////////////////

    //////////////////////////////////////////////
    // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
    //////////////////////////////////////////////

    /**
     * Defines a node class for a doubly-linked list.
     */
    class Node {
        /** the value stored in this node. */
        T element;
        /** a reference to the node after this node. */
        Node next;
        /** a reference to the node before this node. */
        Node prev;

        /**
         * Instantiate an empty node.
         */
        public Node() {
            element = null;
            next = null;
            prev = null;
        }

        /**
         * Instantiate a node that contains element
         * and with no node before or after it.
         */
        public Node(T e) {
            element = e;
            next = null;
            prev = null;
        }
    }

}

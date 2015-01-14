/**
 * ArrayHeapImplementation
 * An array-based heap implementation
 * @author Josh You
 */

public class ArrayHeapImplementation<T extends Comparable<? super T>> 
    implements Heap<T>{
    private static final int DEFAULT_CAPACITY = 8;
    private T[] array_heap;
    private int num_items;

    public ArrayHeapImplementation() {
        @SuppressWarnings("unchecked")
          T[] tmp = (T[]) new Comparable[DEFAULT_CAPACITY];
        array_heap = tmp;
        num_items = 0;
    }
    
    /**Ensures that the array has sufficient space to add any new items
     * by doubling the array size whenever the number of items
     * reaches the length of the array.
     */
    public void ensureCapacity() {
        if (num_items >= (array_heap.length - 1)) {
            //Creates a new array with twice the length, puts all current 
            //items in the new array, and assigns array_heap to the 
            //doubled array.
            @SuppressWarnings("unchecked")
              T[] doubled = (T[]) new Comparable[num_items * 2];
            for (int i = 0; i < num_items + 1; i++) {
                doubled[i] = array_heap[i];
            }
            array_heap = doubled;
        }
    }
    
    /** Places a node in its proper place in the heap by swapping it with
     *  its parents until its parent has a larger value or it is the top node
     */
    public void bubbleUp(int index) {
        if (index <= 1) {
            return;
        }
        if (array_heap[index].compareTo(array_heap[index / 2]) > 0) {
            T old_parent = array_heap[index / 2];
            T old_child = array_heap[index];
            array_heap[index] = old_parent;
            array_heap[index / 2] = old_child;
            bubbleUp(index / 2);
            return;
        }
        return;
    }
    
    /** Places a node in its proper place in the heap by swapping it with its
     *  largest child until it is larger than both of its children or
     *  it is on the lowest level of the heap
     */
    public void bubbleDown(int index) {
        if (index >= num_items) {
            return;
        }
        //exits if the node has no children
        if (array_heap[index * 2] == null) {
            return;
        }
        T larger_child;
        int larger_index;
        //if the the node has only one child, that child is the larger one 
        //by default
        if (array_heap[1 + index * 2] == null) {
            larger_child = array_heap[index * 2];
            larger_index = index * 2;
        } else if (array_heap[index * 2].compareTo(array_heap[1 + index * 2])
            < 0) {
            larger_child = array_heap[1 + index * 2];
            larger_index = 1 + index * 2;
        } else {
            larger_child = array_heap[index * 2];
            larger_index = index * 2;
        }
        if (larger_child.compareTo(array_heap[index]) > 0) {
            T old_value = array_heap[index];
            array_heap[index] = larger_child;
            array_heap[larger_index] = old_value;
            bubbleDown(larger_index);
        }
        return;
    }
        
    /** Adds the given item to the heap. */
    public void add(T item) {
        ensureCapacity();
        array_heap[num_items + 1] = item;
        num_items++;
        bubbleUp(num_items);
    }
    
    /** Removes and returns the maximum value in the heap.
     * @return null if the heap is empty.
     */
    public T removeRoot() {
        if (isEmpty()) {
            return null;
        }
        T ret = array_heap[1];
        array_heap[1] = array_heap[num_items];
        array_heap[num_items] = null;
        num_items--;
        bubbleDown(1);
        return ret;
    }
    
    /** Returns the maximum value, but doesn't change the heap.
     * @return null if the heap is empty.
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return array_heap[1];

    }
    
    /** Returns true if the heap is empty. */
    public boolean isEmpty() {
        if (num_items <= 0) {
            return true;
        }
        return false;
    }
    
    /** Removes all items from the heap. */
    public void clear() {
        @SuppressWarnings("unchecked")
          T[] tmp = (T[]) new Comparable[DEFAULT_CAPACITY];
        array_heap = tmp;
        num_items = 0;
    }
    
    public static void print(String toPrint) {
        System.out.println(toPrint);
    }
    
    /* Creates a heap and tests all of the heap methods
     */
    public static void main(String args[]) {
        Heap<Integer> test_heap = new ArrayHeapImplementation<Integer>();
        if (!test_heap.isEmpty()) {
            print("error: isEmpty returned false negative");
        }
        test_heap.add(0);
        if (test_heap.isEmpty()) {
            print("error: isEmpty returned false positive");
        }
        if (test_heap.peek() != 0) {
            print("peek returned wrong value- expected 0, got "
                  + test_heap.peek());
        }
        int removed = test_heap.removeRoot();
        if (removed != 0) {
            print("removeRoot returned wrong value- expected 0, got "
                  + removed);
        }
        if (!test_heap.isEmpty()) {
            print("removeRoot failed to remove value");
        }
        test_heap.add(0);
        test_heap.add(7);
        test_heap.add(14);
        test_heap.add(21);
        test_heap.add(28);
        for (int i = 0; i < 5; i++) {
            removed = test_heap.removeRoot();
            if (removed != (7 * (4 - i))) {
                print("removeRoot returned wrong value- expected "
                      + (7 * (4 - i)) + ", got " + removed);
            }
        }
        
        for (int i = 0; i < 1000; i++) {
            test_heap.add(22);
        }
        if (test_heap.peek() != 22) {
            print("failed to add large number of items");
        }
        test_heap.clear();
        if (!test_heap.isEmpty()) {
            print("clear failed- heap not empty after clearing");
        }
        if (test_heap.peek() != null) {
            print("clear failed- peek not null after clearning");
        }
    }
}
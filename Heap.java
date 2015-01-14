/**
 * An interface for the Max-Heap ADT.  Note that the ordering of
 * elements is determined with the T.compareTo() method.
 * @param <T> the type of data the heap stores.
 * @author Jadrian Miles
 */
public interface Heap<T extends Comparable<? super T>> {
    
    /** Adds the given item to the heap. */
    public void add(T item);
    
    /** Removes and returns the maximum value in the heap.
     * @return null if the heap is empty.
     */
    public T removeRoot();
    
    /** Returns the maximum value, but doesn't change the heap.
     * @return null if the heap is empty.
     */
    public T peek();
    
    /** Returns true if the heap is empty. */
    public boolean isEmpty();
    
    /** Removes all items from the heap. */
    public void clear();
}
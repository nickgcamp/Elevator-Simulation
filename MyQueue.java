/**
 * Queue Interface
 * @author Nick Campuzano
 * @param <T> DataType of what you want to store in the Queue.
 */
public interface MyQueue<T>
{
    /**
     * Enqueues data at the back of the Queue
     * @param entry Data to enqueue into the Queue
     */
    void enqueue(T entry);

    /**
     * Dequeue data off the front of the Queue
     * @return Data which was at the front of the Queue. May be null if Queue is empty
     */
    T dequeue();
    
    /**
     * Peek at what data is at the front of the Queue
     * @return Data (does not remove) which is currently at the front of the Queue.  May be null if Queue is empty
     */
    T peek();

    /**
     * peek at given position
     * @param targetFloor target floor
     * @param pos position in floor
     * @return data at given position
     */
    T peekAtPos(int targetFloor,int pos);
    
    /**
     * Retrieves the size of the Queue
     * @return An integer denoting how many items are currently in the Queue
     */
    int size();
    
    /**
     * Determines if the Queue is empty or not
     * @return Boolean indicating if the Queue is empty (true) or not (false)
     */
    boolean isEmpty();

    /**
     * deletes everything in list
     */
    void clear();
}

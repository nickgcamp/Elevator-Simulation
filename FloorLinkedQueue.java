/**
 * Linked List version of Queue
 *
 * @author Nick Campuzano
 */
public class FloorLinkedQueue<ElevatorRider> implements MyQueue<ElevatorRider> {
    private int count;
    private Node front;
    private Node back;

    private class Node {
        ElevatorRider data;
        Node next;
    }

    public FloorLinkedQueue() {
        count = 0;
        front = null;
        back = null;
    }

    public String toString() {
        StringBuilder output = new StringBuilder(size() + " Riders\n");
        Node curr = front;
        while (curr != null) {
            output.append("\t").append(curr.data);
            curr = curr.next;
        }
        return output.toString();
    }

    /**
     * adds entry to back of queue
     *
     * @param entry Data to enqueue into the Queue
     */
    @Override
    public void enqueue(ElevatorRider entry) {
        Node newNode = new Node();
        newNode.data = entry;
        if (!isEmpty())
            back.next = newNode;
        else
            front = newNode;
        back = newNode;
        count++;
    }

    /**
     * removes object from front of queue
     *
     * @return object being removed
     */
    @Override
    public ElevatorRider dequeue() {
        if (isEmpty())
            return null;
        ElevatorRider temp = front.data;
        front.data = null;
        front = front.next;
        count--;
        return temp;
    }

    /**
     * shows what is at the top of the queue
     *
     * @return object at top of queue
     */
    @Override
    public ElevatorRider peek() {
        if (isEmpty())
            return null;
        return front.data;
    }

    /**
     * shows what is at the given pos in queue
     * @param pos position in queue
     * @return object at position
     */

    public ElevatorRider peekAtPos(int targetFloor, int pos){
        if (isEmpty()){
            return null;
        }
        if (pos == 1){
            return front.data;
        }
        else {
            Node temp = front;
            for (int i = 1; i <= pos; i++){
                temp = temp.next;
            }
            return temp.data;
        }
    }

    /**
     * gets the number of items in queue
     *
     * @return integer number total items in queue
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * checks if queue is empty
     *
     * @return true if empty. false if not empty
     */
    @Override
    public boolean isEmpty() {
        if (count == 0)
            return true;
        return false;
    }

    public void clear(){
        count = 0;
        front = null;
        back = null;
    }


}

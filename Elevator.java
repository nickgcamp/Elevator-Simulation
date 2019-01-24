/**
 * Elevator class implemented as stack
 *
 * @author Nick Campuzano
 */
public class Elevator<ElevatorRider> implements MyStack<ElevatorRider> {

    private int count;
    private int currentFloor;
    private Node top;

    public class Node {
        ElevatorRider data;
        Node next;
    } //end node

    public Elevator() { // base constructor
        ElevatorRider data;
        top = null;
        count = 0;
        currentFloor = 0;
    } // end constructor

    public Elevator(int floor){ // set current floor when creating the elevator
        ElevatorRider data;
        top = null;
        count = 0;
        currentFloor = floor;
    } // end constructor

    public String toString() {
        String output = "Here is what is in the elevator:\n";
        output += "There are " + size() + " riders in the Elevator\n";
        //here to actually see what is in th elevator
        Node curr = top;
        while (curr != null) {
            output += "\t" + curr.data;
            curr = curr.next;
        }
        return output;
    }

    /**
     * gets the floor you are on
     *
     * @return integer denoting current floor
     */
    int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * change the current floor you are on
     *
     * @param currentFloor integer to which floor you want to go to
     */
    void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     * adds object to top of stack
     *
     * @param entry Data to push onto the Stack
     */
    @Override
    public void push(ElevatorRider entry) {

        Node newNode = new Node();
        newNode.data = entry;
        newNode.next = top;
        top = newNode;
        count++;
    }

    /**
     * removes object from top of stack
     *
     * @return object to be removed
     */
    @Override
    public ElevatorRider pop() {
        if (isEmpty()) {
            return null;
        }
        ElevatorRider temp = top.data;
        top.data = null;
        count--;
        top = top.next;
        return temp;
    }

    /**
     * checks to see what is on the top of the stack
     *
     * @return object at top. if empty returns null.
     */
    @Override
    public ElevatorRider peek() {
        if (isEmpty()) {
            return null;
        }
        return top.data;
    }

    /**
     * checks what object is at given Pos in stack
     * @param pos given pos for where you want in stack
     * @return object at given position
     */
    public ElevatorRider peekAtPos(int pos){
        if (isEmpty()){
            return null;
        }if (pos == 1){
            return top.data;
        }else {
            Node temp = top;
            for (int i = 1; i <= pos - 1;i++){
                temp = temp.next; // temp is now at target node
            }
            return temp.data;
        }
    }


    /**
     * checks to see how many objects are in the stack
     *
     * @return integer denoting the number of items in the stack
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * checks if the stack is empty
     *
     * @return true if empty. false if not empty
     */
    @Override
    public boolean isEmpty() {
        if (count == 0)
            return true;
        return false;
    }
}

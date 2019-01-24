/**
 * Class for a building with floors as array of queues
 *
 * @author Nick Campuzano
 */
public class Building<ElevatorRider> implements MyQueue<ElevatorRider> {

    private int[] count;
    private int defaultFloor;
    private MyQueue<ElevatorRider>[] floorArray;

    /**
     * Constructor for a new building
     *
     * @param numOfFloors   number of floors in building
     * @param startingFloor denotes the default floor
     */
    public Building(int numOfFloors, int startingFloor) {
        count = new int[numOfFloors];
        defaultFloor = startingFloor;
        floorArray = new MyQueue[numOfFloors];
        for (int i = 0; i < floorArray.length; i++) {
            floorArray[i] = new FloorLinkedQueue<ElevatorRider>();
        }
    } // end constructor


    public String toString() {
        String output = "Here is what is in the building: \n";
        for (int i = 0; i < floorArray.length; i++) {
            output += "On floor " + (i + 1) + " there are " + floorArray[i].toString() + "\n";
        }
        return output;
    }

    /**
     * add to specific floor
     *
     * @param entry       object to enter
     * @param targetFloor floor we want to add object too
     */
    public void enqueue(ElevatorRider entry, int targetFloor) {
        floorArray[targetFloor].enqueue(entry);
        count[targetFloor]++;
    }

    /**
     * add object to bottom floor of queue
     *
     * @param entry Data to enqueue into the Queue
     */
    @Override
    public void enqueue(ElevatorRider entry) { // need to tell which floor to add to
        enqueue(entry, defaultFloor);
    }

    /**
     * removes object from queue
     *
     * @param targetFloor integer denoting which floor you want to remove from
     * @return object to be removed on target floor
     */
    public ElevatorRider dequeue(int targetFloor) {
        if (!floorArray[targetFloor].isEmpty()) {
            count[targetFloor]--;
            return floorArray[targetFloor].dequeue();
        }
        return null;
    }

    /**
     * removes object from queue. by default removes from bottom floor
     *
     * @return object to be removed
     */
    @Override
    public ElevatorRider dequeue() {
        for (int i = 0; i < floorArray.length; i++) {
            if (!floorArray[i].isEmpty()) {
                count[defaultFloor]--;
                return floorArray[i].dequeue();
            }
        }
        return null;
    }

    /**
     * checks what object is at the front on target floor
     *
     * @param targetFloor integer denoting which floor you want to look at
     * @return object at top of queue on target floor
     */
    public ElevatorRider peek(int targetFloor) {
        if (!floorArray[targetFloor].isEmpty()) {
            return floorArray[targetFloor].peek();
        }
        return null;
    }

    /**
     * Method to peek at specific position on a specific floor.
     *
     * @param targetFloor target floor
     * @param pos         position in target floor
     * @return ElevatorRider object at specific position on target floor
     */
    public ElevatorRider peekAtPos(int targetFloor, int pos) {
        if (!floorArray[targetFloor].isEmpty()) {
            return floorArray[targetFloor].peekAtPos(targetFloor, pos);
        }
        return null;
    }

    /**
     * checks what object is in the front by default on bottom floor
     *
     * @return object at front of queue
     */
    @Override
    public ElevatorRider peek() {
        for (int i = 0; i < floorArray.length; i++) {
            if (!floorArray[i].isEmpty()) {
                return floorArray[i].peek();
            }
        }
        return null;
    }

    /**
     * checks how many objects are in the queue
     *
     * @param targetFloor integer denoting which floor you want
     * @return integer denoting number of objects in queue
     */
    public int size(int targetFloor) {
        return count[targetFloor];
    }

    /**
     * checks how many objects are in the queue by default bottom floor
     *
     * @return integer denoting number of objects in queue
     */
    @Override
    public int size() {
        return count[0];
    }

    /**
     * checks if the queue is empty at target floor
     *
     * @param targetFloor integer denoting which floor
     * @return true if empty. false if not empty
     */
    public boolean isEmpty(int targetFloor) {
        if (count[targetFloor] == 0)
            return true;
        return false;
    }

    /**
     * checks if the queue is empty by default on bottom floor
     *
     * @return true if empty. false if not empty
     */
    @Override
    public boolean isEmpty() {
        if (count[defaultFloor] == 0)
            return true;
        return false;
    }

    /**
     * deletes all contents of the building
     */
    @Override
    public void clear() {
        for (int i = 0; i < 5; i++)
            floorArray[i].clear();
    }
}

/**
 * Class for an Elevator Rider Object
 *
 * @author Nick Campuzano
 */

public class ElevatorRider {
    int idNumber;
    int targetFloor;
    int frustration;

    /**
     * method to create a new object
     * @param idNumber Object Id number
     * @param targetFloor int denoting target floor
     * @param frustration int denoting frustration level
     */
    public ElevatorRider(int idNumber, int targetFloor, int frustration) {
        this.idNumber = idNumber;
        this.targetFloor = targetFloor;
        this.frustration = frustration;

    } // end constructor

    /**
     * method to get ID number
     * @return int id number
     */
    public int getIdNumber() {
        return idNumber;
    }

    /**
     * sets the id number
     * @param idNumber integer for new id number
     */
    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * method to get objects frustration level
     * @return integer denoting frustration level
     */
    public int getFrustration() {
        return frustration;
    }

    /**
     * sets the objects frustration level
     * @param frustration integer for new frustration level
     */
    public void setFrustration(int frustration) {
        this.frustration = frustration;
    }

    /**
     * method to raise the frustration level by one
     */
    public void raiseFrustraion(){
        frustration += 1;
    }

    /**
     * method to get the objects target floor
     * @return integers denoting target floor
     */
    public int getTargetFloor() {
        return targetFloor;
    }

    /**
     * sets the objects target floor
     * @param targetFloor integer number of the new target floor
     */
    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    @Override
    public String toString() {
        return "Elevator Rider " + idNumber +
                " : Target Floor " + (targetFloor + 1) +
                " : Frustration Level " + frustration + "\n";
    }
}

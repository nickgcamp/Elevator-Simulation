import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Driver for Project One Elevators
 * has 2 modes: Morning Mode and Evening Mode
 *
 * @author Nick Campuzano , Nate Seleshi, Mohamed Omar
 */
public class Driver {

    public static void main(String[] args) {

        int currentFloor = 0;

        // ******************  MORNING MODE ******************
        // create building array of queues
        Building<ElevatorRider> building = new Building<>(5, 0);
        //create parking lot queue
        waitingBuilding<ElevatorRider> parkingLot = new waitingBuilding<>(1, 0);
        //create in home queue
        inHome<ElevatorRider> inHome = new inHome<>(1, 0);
        // create temp list. This will be shuffled
        List<ElevatorRider> tempWaitingList = new ArrayList<>();
        // create elevator
        Elevator<ElevatorRider> elevator = new Elevator<>();

        //create day riders place in list tempWaitingList
        for (int i = 1; i < 111; i++) {
            ElevatorRider dayRider = new ElevatorRider(i, 4, 0);
            tempWaitingList.add(dayRider);
        }
        for (int i = 111; i < 186; i++) {
            ElevatorRider dayRider = new ElevatorRider(i, 3, 0);
            tempWaitingList.add(dayRider);
        }
        for (int i = 186; i < 251; i++) {
            ElevatorRider dayRider = new ElevatorRider(i, 2, 0);
            tempWaitingList.add(dayRider);
        }
        for (int i = 251; i < 351; i++) {
            ElevatorRider dayRider = new ElevatorRider(i, 1, 0);
            tempWaitingList.add(dayRider);
        }
        //shuffle day riders
        Collections.shuffle(tempWaitingList);
        // move day riders from tempWaitingList to parkingLot
        for (ElevatorRider aTempWaitingList : tempWaitingList) parkingLot.enqueue(aTempWaitingList);

        // adding night riders to main building

        for (int i = 351; i < 355; i++) {
            ElevatorRider nightRider = new ElevatorRider(i, 0, 0);
            building.enqueue(nightRider, 1);
        }
        for (int i = 355; i < 359; i++) {
            ElevatorRider nightRider = new ElevatorRider(i, 0, 0);
            building.enqueue(nightRider, 2);
        }
        for (int i = 359; i < 363; i++) {
            ElevatorRider nightRider = new ElevatorRider(i, 0, 0);
            building.enqueue(nightRider, 3);
        }
        for (int i = 363; i < 367; i++) {
            ElevatorRider nightRider = new ElevatorRider(i, 0, 0);
            building.enqueue(nightRider, 4);
        }

        System.out.println("*********** Start Day Mode ***************");
        System.out.println(parkingLot);
        System.out.println("Start");
        System.out.println(building);
        System.out.println("Start");
        System.out.println(elevator);


        // to start add 5 people to elevator
        for (int i = 0; i < 5; i++)
            elevator.push(parkingLot.dequeue());
        // three cases
        // 1. if parking lot is empty
        // 2. if lobby(floor 1) is empty
        // 3. if elevator empty

        // big while loop to move all day riders to home floors
        while (elevator.size() > 0) { // every time enter this loop we are moving the elevator
            if (parkingLot.size() != 0) { // case 1
                // adding 5 people from parking lot to building
                if (parkingLot.peek() != null) {
                    for (int i = 0; i < 5; i++)
                        building.enqueue(parkingLot.dequeue());
                }
                //raise frustration of everyone in elevator
                if (elevator.size() > 0) {
                    for (int i = 1; i < elevator.size(); i++) {
                        elevator.peekAtPos(i).raiseFrustraion();
                    }
                }
                // raise frustration of everyone in the lobby on first floor
                if (building.size(0) > 0) {
                    for (int i = 0; i < building.size(0); i++) {
                        building.peekAtPos(0, i).raiseFrustraion();
                    }
                }
                // elevator still has room and there is someone on the first floor
                while (elevator.size() < 20 && building.size(0) > 0) {
                    elevator.push(building.dequeue()); // add to elevator from floor 1
                }
                //if here elevator has people in. need to move to floor and drop people off
                currentFloor = elevator.peek().getTargetFloor(); // current floor is now first person target floor
                elevator.setCurrentFloor(currentFloor);
                while (currentFloor == elevator.peek().getTargetFloor()) {
                    building.enqueue(elevator.pop(), currentFloor); // remove from elevator at correct floor
                }
            } // end case 1
            // if here parking lot is empty but there are still people in the lobby
            else if (building.size(0) != 0) { // case 2
                // adding 5 people from parking lot to building
                if (parkingLot.peek() != null) {
                    for (int i = 0; i < 5; i++)
                        building.enqueue(parkingLot.dequeue());
                }
                //raise frustration of everyone in elevator
                if (elevator.size() > 0) {
                    for (int i = 1; i < elevator.size(); i++) {
                        elevator.peekAtPos(i).raiseFrustraion();
                    }
                }
                // raise frustration of everyone in the lobby on first floor
                if (building.size(0) > 0) {
                    for (int i = 0; i < building.size(0); i++) {
                        building.peekAtPos(0, i).raiseFrustraion();
                    }
                }
                // elevator still has room and there is someone on the first floor
                while (elevator.size() < 20 && building.size(0) > 0) {
                    elevator.push(building.dequeue()); // add to elevator from floor 1
                }
                //if here elevator has people in. need to move to floor and drop people off
                currentFloor = elevator.peek().getTargetFloor(); // current floor is now first person target floor
                elevator.setCurrentFloor(currentFloor);
                while (currentFloor == elevator.peek().getTargetFloor()) {
                    building.enqueue(elevator.pop(), currentFloor); // remove from elevator at correct floor
                }
            } // end case 2
            //if here parking and lobby are empty but elevator still has people
            else { // case 3
                // adding 5 people from parking lot to building
                if (parkingLot.peek() != null) {
                    for (int i = 0; i < 5; i++)
                        building.enqueue(parkingLot.dequeue());
                }
                //raise frustration of everyone in elevator
                if (elevator.size() > 0) {
                    for (int i = 1; i < elevator.size(); i++) {
                        elevator.peekAtPos(i).raiseFrustraion();
                    }
                }
                // raise frustration of everyone in the lobby  on first floor
                //bonus question
                if (building.size(0) > 0) {
                    for (int i = 0; i < building.size(0); i++) {
                        building.peekAtPos(0, i).raiseFrustraion();
                    }
                }
                // elevator still has room and there is someone on the first floor
                while (elevator.size() < 20 && building.size(0) > 0) {
                    elevator.push(building.dequeue()); // add to elevator from floor 1
                }
                //if here elevator has people in it . need to move to floor off first person in elevator
                // and drop off at their target floor
                currentFloor = elevator.peek().getTargetFloor(); // current floor is now first person target floor
                elevator.setCurrentFloor(currentFloor);
                if (currentFloor == elevator.peek().getTargetFloor()) {
                    building.enqueue(elevator.pop(), currentFloor); // remove from elevator if at correct floor
                }

            } // end case 3
        } // end big while loop to move all day riders to home floors

        // move night riders down to lobby

        // move elevator to each floor and pick up all night riders
        for (int i = 0; i < 4; i++) {
            for (int k = 1; k < 5; k++)
                elevator.push(building.dequeue(k));
        }
        while (building.size(0) != 16) // while lobby does not have 16 people
            building.enqueue(elevator.pop(), 0);


        System.out.println("*********** Final Day Mode ***************\n");
        System.out.println(building);
        System.out.println("Final");
        System.out.println(parkingLot);
        System.out.println("Final");
        System.out.println(elevator);

        // average frustration
        int sumFrustration = 0;
        for (int i = 0; i < 5; i++) { //index to loop through each floor
            for (int j = 1; j < building.size(i); j++) { // index to loop through each floor
                sumFrustration += building.peekAtPos(i, j).getFrustration(); // add everyone's frustration level together
            }
        }
        int numOfRiders = (building.size(0)
                + building.size(1)
                + building.size(2)
                + building.size(3)
                + building.size(4));
        System.out.println("Sum of Frustration: " + sumFrustration);
        System.out.println("Number of Riders: " + numOfRiders);
        System.out.println("Average Frustration: " + sumFrustration / 366);
        System.out.println("\n");


        // CLEARING FOR EVENING MODE
        System.out.println("Clearing for evening mode");
        System.out.println("Should be nothing in the Building, parkingLot, and Elevator\n");
        // need to clear building, parking lot, and elevator
        // elevator, parking lot already clear
        building.clear();



        // ***************************************************
        // ******************  EVENING MODE ******************
        // ***************************************************

        System.out.println("************ Evening Mode ************\n");
        //create day riders place in list tempWaitingList
        tempWaitingList.clear(); // clear previous list from day mode
        for (int i = 1; i < 111; i++) {
            ElevatorRider dayRider = new ElevatorRider(i, 4, 0);
            tempWaitingList.add(dayRider);
        }
        for (int i = 111; i < 186; i++) {
            ElevatorRider dayRider = new ElevatorRider(i, 3, 0);
            tempWaitingList.add(dayRider);
        }
        for (int i = 186; i < 251; i++) {
            ElevatorRider dayRider = new ElevatorRider(i, 2, 0);
            tempWaitingList.add(dayRider);
        }
        for (int i = 251; i < 351; i++) {
            ElevatorRider dayRider = new ElevatorRider(i, 1, 0);
            tempWaitingList.add(dayRider);
        }
        //shuffle day riders
        Collections.shuffle(tempWaitingList);
        // move day riders from tempWaitingList to parkingLot
        for (ElevatorRider aTempWaitingList : tempWaitingList) inHome.enqueue(aTempWaitingList);
        System.out.println(inHome);

        // adding night riders to lobby

        for (int i = 351; i < 355; i++) {
            ElevatorRider nightRider = new ElevatorRider(i, 0, 0);
            building.enqueue(nightRider, 0);
        }
        for (int i = 355; i < 359; i++) {
            ElevatorRider nightRider = new ElevatorRider(i, 0, 0);
            building.enqueue(nightRider, 0);
        }
        for (int i = 359; i < 363; i++) {
            ElevatorRider nightRider = new ElevatorRider(i, 0, 0);
            building.enqueue(nightRider, 0);
        }
        for (int i = 363; i < 367; i++) {
            ElevatorRider nightRider = new ElevatorRider(i, 0, 0);
            building.enqueue(nightRider, 0);
        }
        System.out.println(building);
        // if here all riders have been created. night riders are in lobby
        // day riders are randomly shuffled in queue inHome
        //Move day riders first;
        int floorTracker = 0;
        while (building.size(0) != 366) { // b/c  I know that is when all day riders on on the first floor
            if (inHome.size() != 0) { // case 1 everyone has left home waiting for elevator to get to ground floor
                // add 5 people to their queue floors
                if (inHome.peek() != null) {
                    for (int i = 0; i < 5; i++) {
                        floorTracker = inHome.peek().getTargetFloor();
                        building.enqueue(inHome.dequeue(), floorTracker);
                    }
                }
                if (building.size(4) > 0){
                    for (int i = 0; i < building.size(4);i++)
                        building.peekAtPos(4,i).raiseFrustraion();
                }
                if (building.size(3) > 0){
                    for (int i = 0; i < building.size(3);i++)
                        building.peekAtPos(3,i).raiseFrustraion();
                }
                if (building.size(2) > 0){
                    for (int i = 0; i < building.size(4);i++)
                        building.peekAtPos(2,i).raiseFrustraion();
                }
                if (building.size(1) > 0){
                    for (int i = 0; i < building.size(1);i++)
                        building.peekAtPos(1,i).raiseFrustraion();
                }
                //raise frustration of people waiting in building for elevator
                for (int i = 0; i < 5; i++) { // loop through each floor
                    for (int k = 1; k < building.size(i); k++) { // loop through queue on each floor
                        if (building.peek(i) != null)
                            building.peekAtPos(i, k).raiseFrustraion(); // raise frustration
                    }
                } // end frustration in the building

                // add to people to elevator
                for (int i = 4; i > 0; i--) { // loop through floor top to bottom does not go to floor 1
                    while (elevator.size() < 20 && building.size(i) > 0) { // loop through queue at building
                        if (building.peek(i) != null) // if there is someone on the floor add to elevator
                            elevator.push(building.dequeue(i)); // put onto elevator
                    }
                } // add to to elevator

                // empty elevator on ground floor
                while (elevator.size() != 0) {
                    if (elevator.peek() != null) {
                        building.enqueue(elevator.pop(), 0);
                    }
                }
            }// end case 1 everyone in floor queue
        } // main while loop

        // final building print out here

    }// end main
} // end class

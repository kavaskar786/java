// File: DeluxeRoom.java
package hotelmanagement;

public class DeluxeRoom implements Room {
    private int roomNumber;
    private boolean isOccupied;

    public DeluxeRoom(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
    }

    @Override
    public void bookRoom() {
        if (!isOccupied) {
            isOccupied = true;
            System.out.println("Room " + roomNumber + " booked successfully.");
        } else {
            System.out.println("Room " + roomNumber + " is already occupied.");
        }
    }

    @Override
    public void checkOut() {
        if (isOccupied) {
            isOccupied = false;
            System.out.println("Checked out from Room " + roomNumber + ".");
        } else {
            System.out.println("Room " + roomNumber + " is not occupied.");
        }
    }

    @Override
    public void displayRoomInfo() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Occupied: " + isOccupied);
    }
}

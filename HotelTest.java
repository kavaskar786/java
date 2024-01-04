// File: HotelTest.java
package hotelmanagement;

public class HotelTest {
    public static void main(String[] args) {
        HotelManagementSystem hotel = new HotelManagementSystem();

        Room deluxeRoom1 = new DeluxeRoom(101);
        Room deluxeRoom2 = new DeluxeRoom(102);

        deluxeRoom1.bookRoom();
        deluxeRoom1.displayRoomInfo();

        deluxeRoom2.bookRoom();
        deluxeRoom2.displayRoomInfo();

        deluxeRoom1.checkOut();
        deluxeRoom1.displayRoomInfo();
    }
}
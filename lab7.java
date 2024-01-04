import java.util.ArrayList;
import java.util.List;

class HotelRoom {
    private int roomNumber;
    private boolean isOccupied;

    public HotelRoom(int roomNumber, boolean isOccupied) {
        this.roomNumber = roomNumber;
        this.isOccupied = isOccupied;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}

public class lab7 {

    public static void main(String[] args) {
        // Sample list of hotel rooms
        List<HotelRoom> rooms = new ArrayList<>();
        rooms.add(new HotelRoom(101, false));
        rooms.add(new HotelRoom(102, true));
        rooms.add(new HotelRoom(103, false));
        rooms.add(new HotelRoom(104, true));
        rooms.add(new HotelRoom(105, false));

        // Using lambda expression to filter available rooms
        List<HotelRoom> availableRooms = filterRooms(rooms, room -> !room.isOccupied());

        // Displaying the available rooms
        System.out.println("Available Rooms:");
        for (HotelRoom room : availableRooms) {
            System.out.println("Room Number: " + room.getRoomNumber());
        }
    }

    // Lambda expression for filtering rooms based on a condition
    private static List<HotelRoom> filterRooms(List<HotelRoom> rooms, RoomFilterCondition condition) {
        List<HotelRoom> filteredRooms = new ArrayList<>();
        for (HotelRoom room : rooms) {
            if (condition.test(room)) {
                filteredRooms.add(room);
            }
        }
        return filteredRooms;
    }

    // Functional interface for the room filter condition
    @FunctionalInterface
    interface RoomFilterCondition {
        boolean test(HotelRoom room);
    }
}

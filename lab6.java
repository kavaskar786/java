import java.util.HashMap;
import java.util.Map;

// Define a generic class for rooms
class Room<T> {
    private T roomNumber;
    private boolean isOccupied;

    public Room(T roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
    }

    public T getRoomNumber() {
        return roomNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupy() {
        isOccupied = true;
    }

    public void vacate() {
        isOccupied = false;
    }
}

// Define a generic interface for billing
interface Billable<T> {
    double calculateBill(T item);
}

// Implement a generic hotel management system
class HotelManagementSystem<T> {
    private Map<T, Room<T>> rooms;

    public HotelManagementSystem() {
        this.rooms = new HashMap<>();
    }

    // Method to add a room to the hotel
    public void addRoom(Room<T> room) {
        rooms.put(room.getRoomNumber(), room);
    }

    // Method to book a room
    public void bookRoom(T roomNumber) {
        Room<T> room = rooms.get(roomNumber);
        if (room != null && !room.isOccupied()) {
            room.occupy();
            System.out.println("Room " + roomNumber + " booked successfully.");
        } else {
            System.out.println("Room " + roomNumber + " is not available for booking.");
        }
    }

    // Method to calculate and print the bill for a room
    public void calculateBill(Billable<T> billable, T roomNumber) {
        Room<T> room = rooms.get(roomNumber);
        if (room != null && room.isOccupied()) {
            double bill = billable.calculateBill(roomNumber);
            System.out.println("Bill for Room " + roomNumber + ": $" + bill);
        } else {
            System.out.println("Room " + roomNumber + " is not occupied.");
        }
    }
}

// Example implementation
public class lab6 {
    public static void main(String[] args) {
        // Create a hotel management system for Integer room numbers
        HotelManagementSystem<Integer> hotel = new HotelManagementSystem<>();

        // Add rooms to the hotel
        hotel.addRoom(new Room<>(101));
        hotel.addRoom(new Room<>(102));
        hotel.addRoom(new Room<>(103));

        // Book a room
        hotel.bookRoom(101);

        // Implement billing logic for Integer room numbers
        Billable<Integer> integerBillable = roomNumber -> 100.0; // Simple billing logic: $100 per night

        // Calculate and print the bill for a room
        hotel.calculateBill(integerBillable, 101);

        // Create a hotel management system for String room numbers
        HotelManagementSystem<String> stringHotel = new HotelManagementSystem<>();

        // Add rooms to the hotel
        stringHotel.addRoom(new Room<>("A101"));
        stringHotel.addRoom(new Room<>("A102"));

        // Book a room
        stringHotel.bookRoom("A101");

        // Implement billing logic for String room numbers
        Billable<String> stringBillable = roomNumber -> 120.0; // Simple billing logic: $120 per night

        // Calculate and print the bill for a room
        stringHotel.calculateBill(stringBillable, "A101");
    }
}

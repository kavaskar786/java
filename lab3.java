import java.util.Scanner;

// Abstract class representing a generic room in a hotel
abstract class Room {
    private String roomNumber;
    private double price;

    // Constructor
    public Room(String roomNumber, double price) {
        this.roomNumber = roomNumber;
        this.price = price;
    }

    // Abstract method to be implemented by subclasses
    public abstract void displayInfo();

    // Getter methods
    public String getRoomNumber() {
        return roomNumber;
    }

    public double getPrice() {
        return price;
    }
}

// Subclass representing a standard room
class StandardRoom extends Room {
    // Additional property specific to StandardRoom
    private boolean hasBalcony;

    // Constructor
    public StandardRoom(String roomNumber, double price, boolean hasBalcony) {
        // Call the constructor of the superclass (Room)
        super(roomNumber, price);
        this.hasBalcony = hasBalcony;
    }

    // Override the displayInfo method
    @Override
    public void displayInfo() {
        System.out.println("Standard Room " + getRoomNumber() +
                ", Price: $" + getPrice() +
                ", Balcony: " + (hasBalcony ? "Yes" : "No"));
    }

    // Final method that cannot be overridden by subclasses
    public final void specialService() {
        System.out.println("Special service for Standard Room");
    }
}

// Subclass representing a deluxe room
class DeluxeRoom extends Room {
    // Additional property specific to DeluxeRoom
    private boolean hasJacuzzi;

    // Constructor
    public DeluxeRoom(String roomNumber, double price, boolean hasJacuzzi) {
        // Call the constructor of the superclass (Room)
        super(roomNumber, price);
        this.hasJacuzzi = hasJacuzzi;
    }

    // Override the displayInfo method
    @Override
    public void displayInfo() {
        System.out.println("Deluxe Room " + getRoomNumber() +
                ", Price: $" + getPrice() +
                ", Jacuzzi: " + (hasJacuzzi ? "Yes" : "No"));
    }
}

public class lab3 {
    public static void main(String[] args) {
        // Create Scanner object to get user inputs
        Scanner scanner = new Scanner(System.in);

        // Get user inputs for StandardRoom
        System.out.println("Enter details for Standard Room:");
        System.out.print("Room Number: ");
        String standardRoomNumber = scanner.next();
        System.out.print("Price: $");
        double standardRoomPrice = scanner.nextDouble();
        System.out.print("Has Balcony (true/false): ");
        boolean hasBalcony = scanner.nextBoolean();

        // Create instance of StandardRoom using user inputs
        StandardRoom standardRoom = new StandardRoom(standardRoomNumber, standardRoomPrice, hasBalcony);

        // Display information about StandardRoom
        standardRoom.displayInfo();

        // Call the final method from the StandardRoom class
        standardRoom.specialService();

        // Get user inputs for DeluxeRoom
        System.out.println("\nEnter details for Deluxe Room:");
        System.out.print("Room Number: ");
        String deluxeRoomNumber = scanner.next();
        System.out.print("Price: $");
        double deluxeRoomPrice = scanner.nextDouble();
        System.out.print("Has Jacuzzi (true/false): ");
        boolean hasJacuzzi = scanner.nextBoolean();

        // Create instance of DeluxeRoom using user inputs
        DeluxeRoom deluxeRoom = new DeluxeRoom(deluxeRoomNumber, deluxeRoomPrice, hasJacuzzi);

        // Display information about DeluxeRoom
        deluxeRoom.displayInfo();

        // Close the scanner
        scanner.close();
    }
}

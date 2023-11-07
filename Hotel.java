package labs;

import java.util.Scanner;

public class Hotel {
    // Public data member
    public String publicName;

    // Private data members (Class, Data members)
    private String name;
    private int totalRooms;

    // Protected data member
    protected int protectedOccupiedRooms;

    // Default data member
    int defaultRating;

    // Public Constructor (Constructor Access Specifier)
    public Hotel() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter hotel name: ");
        publicName = scanner.nextLine();
        System.out.print("Enter total number of rooms: ");
        totalRooms = scanner.nextInt();
        protectedOccupiedRooms = 0;
        defaultRating = 3;
    }

    // Constructor with Protected Access Specifier
    protected Hotel(String name, int totalRooms, int rating) {
        this.name = name;
        this.totalRooms = totalRooms;
        protectedOccupiedRooms = 0;
        defaultRating = rating;
    }

    // Method with Public Access Specifier (Methods)
    public void bookRoom() {
        if (protectedOccupiedRooms < totalRooms) {
            protectedOccupiedRooms++;
            System.out.println("Room booked successfully.");
        } else {
            System.out.println("Sorry, no rooms available.");
        }
    }

    // Method with Private Access Specifier
    private void bookRoom(int numRooms) {
        if (protectedOccupiedRooms + numRooms <= totalRooms) {
            protectedOccupiedRooms += numRooms;
            System.out.println(numRooms + " rooms booked successfully.");
        } else {
            System.out.println("Sorry, not enough rooms available for your request.");
        }
    }

    // Method with Protected Access Specifier
    protected void checkAvailability() {
        int availableRooms = totalRooms - protectedOccupiedRooms;
        System.out.println("Available rooms: " + availableRooms);
    }

    // Method with Default Access Specifier
    void checkAvailability(boolean detailed) {
        if (detailed) {
            System.out.println("Total rooms: " + totalRooms);
            System.out.println("Occupied rooms: " + protectedOccupiedRooms);
            System.out.println("Available rooms: " + (totalRooms - protectedOccupiedRooms));
        } else {
            checkAvailability();
        }
    }

    public static void main(String[] args) {
        Hotel hotel1 = new Hotel();
        Hotel hotel2 = new Hotel("Luxury Inn", 100, 5);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rooms to book in hotel1: ");
        int roomsToBook1 = scanner.nextInt();
        hotel1.bookRoom(roomsToBook1);

        System.out.print("Enter the number of rooms to book in hotel2: ");
        int roomsToBook2 = scanner.nextInt();
        // Accessing a private method - not allowed
        // hotel2.bookRoom(roomsToBook2);

        System.out.println("Checking availability of hotels:");
        hotel1.checkAvailability(true);
        hotel2.checkAvailability(true);
    }
}

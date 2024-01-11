import java.util.*;

class Guest {
    private String name;
    private int age;

    public Guest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class Room {
    private int roomNumber;
    private boolean isOccupied;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
    }

    public int getRoomNumber() {
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

class Reservation {
    private int reservationId;
    private Guest guest;
    private int roomNumber;

    public Reservation(int reservationId, Guest guest, int roomNumber) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.roomNumber = roomNumber;
    }

    public int getReservationId() {
        return reservationId;
    }

    public Guest getGuest() {
        return guest;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}

class Hotel {
    private String name;
    private Map<Integer, Room> rooms;
    private List<Reservation> reservations;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new HashMap<>();
        this.reservations = new ArrayList<>();
    }

    public void addRoom(int roomNumber) {
        Room room = new Room(roomNumber);
        rooms.put(roomNumber, room);
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms in " + name + ":");
        for (Room room : rooms.values()) {
            if (!room.isOccupied()) {
                System.out.println("Room " + room.getRoomNumber());
            }
        }
    }

    public void bookRoom(int roomNumber, Guest guest) {
        Room room = rooms.get(roomNumber);
        if (room != null && !room.isOccupied()) {
            room.occupy();
            Reservation reservation = new Reservation(reservations.size() + 1, guest, roomNumber);
            reservations.add(reservation);
            System.out.println("Room " + roomNumber + " booked successfully for " + guest.getName());
        } else {
            System.out.println("Room " + roomNumber + " is not available for booking.");
        }
    }

    public void vacateRoom(int roomNumber) {
        Room room = rooms.get(roomNumber);
        if (room != null && room.isOccupied()) {
            room.vacate();
            Reservation reservationToRemove = null;
            for (Reservation reservation : reservations) {
                if (reservation.getRoomNumber() == roomNumber) {
                    reservationToRemove = reservation;
                    break;
                }
            }
            if (reservationToRemove != null) {
                reservations.remove(reservationToRemove);
                System.out.println("Room " + roomNumber + " vacated successfully.");
            }
        } else {
            System.out.println("Room " + roomNumber + " is not occupied.");
        }
    }

    public void displayGuestList() {
        System.out.println("Guest List in " + name + ":");
        for (Reservation reservation : reservations) {
            System.out.println("Reservation ID: " + reservation.getReservationId() +
                    ", Guest: " + reservation.getGuest().getName() +
                    ", Room: " + reservation.getRoomNumber());
        }
    }
}

public class lab_8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a hotel
        Hotel myHotel = new Hotel("Grand Hotel");

        // Add some rooms to the hotel
        myHotel.addRoom(101);
        myHotel.addRoom(102);
        myHotel.addRoom(103);

        // User interaction
        int choice;
        do {
            System.out.println("\n1. Display available rooms");
            System.out.println("2. Book a room");
            System.out.println("3. Vacate a room");
            System.out.println("4. Display guest list");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    myHotel.displayAvailableRooms();
                    break;
                case 2:
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.nextLine();
                    System.out.print("Enter guest age: ");
                    int guestAge = scanner.nextInt();
                    System.out.print("Enter the room number to book: ");
                    int roomToBook = scanner.nextInt();
                    Guest guest = new Guest(guestName, guestAge);
                    myHotel.bookRoom(roomToBook, guest);
                    break;
                case 3:
                    System.out.print("Enter the room number to vacate: ");
                    int roomToVacate = scanner.nextInt();
                    myHotel.vacateRoom(roomToVacate);
                    break;
                case 4:
                    myHotel.displayGuestList();
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 5);
    }
}

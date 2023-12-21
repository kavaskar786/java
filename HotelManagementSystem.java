import java.util.concurrent.*;

// Class representing a guest in the hotel
class Guest extends Thread {
    private String name;

    public Guest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " has checked into the hotel.");
        // Simulate making requests
        makeRequest("Towel");
        makeRequest("Breakfast");
        System.out.println(name + " has completed their stay and checked out.");
    }

    private void makeRequest(String request) {
        // Simulate time to make a request
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " is requesting " + request);
        HotelManagementSystem.placeRequest(request);
    }
}

// Class representing the hotel staff
class HotelStaff extends Thread {
    private BlockingQueue<String> requests;
    private volatile boolean isRunning = true;

    public HotelStaff(BlockingQueue<String> requests) {
        this.requests = requests;
    }

    public void stopProcessing() {
        isRunning = false;
        // Interrupt the thread to break out of blocking operation
        interrupt();
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                String request = requests.take();
                System.out.println("Hotel staff is fulfilling request: " + request);
                // Simulate time to fulfill the request
                Thread.sleep(3000);
                System.out.println("Hotel staff has fulfilled the request: " + request);
            } catch (InterruptedException e) {
                // Handle interruption, e.g., exit the loop
                System.out.println("Hotel staff thread interrupted. Exiting.");
                isRunning = false;
            }
        }
    }
}

// Class representing the hotel management system
public class HotelManagementSystem {
    private static BlockingQueue<String> requests = new LinkedBlockingQueue<>();
    private static HotelStaff hotelStaff;

    public static void placeRequest(String request) {
        try {
            requests.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        hotelStaff = new HotelStaff(requests);
        hotelStaff.start();

        // Simulate multiple guests
        for (int i = 1; i <= 3; i++) {
            Guest guest = new Guest("Guest " + i);
            guest.start();
            // Simulate time between guests
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Simulate time for guests to complete their requests
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop hotel staff processing after guests have completed their requests
        hotelStaff.stopProcessing();
    }
}

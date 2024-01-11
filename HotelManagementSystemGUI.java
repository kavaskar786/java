import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class Room {
    private int roomNumber;
    private boolean isOccupied;
    private String guestName;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isOccupied = false;
        this.guestName = "N/A";
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public String getGuestName() {
        return guestName;
    }

    public void occupy(String guestName) {
        this.isOccupied = true;
        this.guestName = guestName;
    }

    public void vacate() {
        this.isOccupied = false;
        this.guestName = "N/A";
    }
}

class Hotel {
    private Map<Integer, Room> rooms;

    public Hotel() {
        this.rooms = new HashMap<>();
        // Add more rooms for demonstration purposes
        for (int i = 101; i <= 110; i++) {
            addRoom(i);
        }
    }

    public void addRoom(int roomNumber) {
        Room room = new Room(roomNumber);
        rooms.put(roomNumber, room);
    }

    public boolean isRoomAvailable(int roomNumber) {
        Room room = rooms.get(roomNumber);
        return room != null && !room.isOccupied();
    }

    public void bookRoom(int roomNumber, String guestName) {
        Room room = rooms.get(roomNumber);
        if (room != null && !room.isOccupied()) {
            room.occupy(guestName);
            appendToTextArea("Room " + roomNumber + " booked successfully for " + guestName);
        } else {
            appendToTextArea("Room " + roomNumber + " is not available for booking.");
        }
    }

    public void vacateRoom(int roomNumber) {
        Room room = rooms.get(roomNumber);
        if (room != null && room.isOccupied()) {
            room.vacate();
            appendToTextArea("Room " + roomNumber + " vacated successfully.");
        } else {
            appendToTextArea("Room " + roomNumber + " is not occupied.");
        }
    }

    public void displayRoomStatus(int roomNumber) {
        Room room = rooms.get(roomNumber);
        if (room != null) {
            String status = room.isOccupied() ? "Occupied by " + room.getGuestName() : "Available";
            appendToTextArea("Room " + roomNumber + " status: " + status);
        } else {
            appendToTextArea("Room " + roomNumber + " does not exist.");
        }
    }

    public void displayOccupiedRooms() {
        StringBuilder occupiedRooms = new StringBuilder("Occupied Rooms:\n");
        for (Room room : rooms.values()) {
            if (room.isOccupied()) {
                occupiedRooms.append("Room ").append(room.getRoomNumber()).append(" - ").append(room.getGuestName()).append("\n");
            }
        }
        appendToTextArea(occupiedRooms.toString());
    }

    public void displayAllRooms() {
        StringBuilder allRooms = new StringBuilder("All Rooms:\n");
        for (Room room : rooms.values()) {
            String status = room.isOccupied() ? "Occupied by " + room.getGuestName() : "Available";
            allRooms.append("Room ").append(room.getRoomNumber()).append(" - ").append(status).append("\n");
        }
        appendToTextArea(allRooms.toString());
    }

    public void displayAvailableRooms() {
        StringBuilder availableRooms = new StringBuilder("Available Rooms:\n");
        for (Room room : rooms.values()) {
            if (!room.isOccupied()) {
                availableRooms.append("Room ").append(room.getRoomNumber()).append("\n");
            }
        }
        appendToTextArea(availableRooms.toString());
    }

    private void appendToTextArea(String text) {
        HotelManagementSystemGUI.appendToTextArea(text);
    }
}

public class HotelManagementSystemGUI {
    private Hotel hotel;
    private static JTextArea textArea;

    public HotelManagementSystemGUI() {
        this.hotel = new Hotel();
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Hotel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        placeComponents(buttonPanel);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new FlowLayout());

        JLabel roomLabel = new JLabel("Room Number:");
        panel.add(roomLabel);

        JTextField roomText = new JTextField(20);
        panel.add(roomText);

        JLabel guestLabel = new JLabel("Guest Name:");
        panel.add(guestLabel);

        JTextField guestText = new JTextField(20);
        panel.add(guestText);

        JButton bookButton = new JButton("Book Room");
        panel.add(bookButton);

        JButton vacateButton = new JButton("Vacate Room");
        panel.add(vacateButton);

        JButton statusButton = new JButton("Room Status");
        panel.add(statusButton);

        JButton occupiedRoomsButton = new JButton("Occupied Rooms");
        panel.add(occupiedRoomsButton);

        JButton allRoomsButton = new JButton("All Rooms");
        panel.add(allRoomsButton);

        JButton availableRoomsButton = new JButton("Available Rooms");
        panel.add(availableRoomsButton);

        JButton clearButton = new JButton("Clear Text Area");
        panel.add(clearButton);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int roomNumber = Integer.parseInt(roomText.getText());
                    String guestName = guestText.getText();
                    if (hotel.isRoomAvailable(roomNumber)) {
                        hotel.bookRoom(roomNumber, guestName);
                    } else {
                        appendToTextArea("Room " + roomNumber + " is not available for booking.");
                    }
                } catch (NumberFormatException ex) {
                    appendToTextArea("Invalid room number. Please enter a valid number.");
                }
            }
        });

        vacateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int roomNumber = Integer.parseInt(roomText.getText());
                    hotel.vacateRoom(roomNumber);
                } catch (NumberFormatException ex) {
                    appendToTextArea("Invalid room number. Please enter a valid number.");
                }
            }
        });

        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int roomNumber = Integer.parseInt(roomText.getText());
                    hotel.displayRoomStatus(roomNumber);
                } catch (NumberFormatException ex) {
                    appendToTextArea("Invalid room number. Please enter a valid number.");
                }
            }
        });

        occupiedRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hotel.displayOccupiedRooms();
            }
        });

        allRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hotel.displayAllRooms();
            }
        });

        availableRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hotel.displayAvailableRooms();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });
    }

    public static void appendToTextArea(String text) {
        textArea.append(text + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HotelManagementSystemGUI().createAndShowGUI();
            }
        });
    }
}

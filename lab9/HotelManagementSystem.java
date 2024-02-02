chpackage lab9;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HotelManagementSystem extends JFrame {

    private JTextField roomNumberField;
    private JComboBox<String> roomTypeComboBox;
    private JTextArea statusTextArea;
    private JTextArea reservationHistoryTextArea;
    private Map<Integer, String> roomStatus;
    private Map<Integer, Date> reservationHistory;

    public HotelManagementSystem() {
        // Set frame properties
        setTitle("Hotel Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize room status and reservation history
        roomStatus = new HashMap<>();
        reservationHistory = new HashMap<>();

        // Create components
        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberField = new JTextField(10);

        JLabel roomTypeLabel = new JLabel("Room Type:");
        String[] roomTypes = {"Single", "Double", "Suite"};
        roomTypeComboBox = new JComboBox<>(roomTypes);

        JButton checkInButton = new JButton("Check In");
        JButton checkOutButton = new JButton("Check Out");
        JButton viewStatusButton = new JButton("View Status");
        JButton viewHistoryButton = new JButton("View Reservation History");

        statusTextArea = new JTextArea(15, 40);
        statusTextArea.setEditable(false);

        reservationHistoryTextArea = new JTextArea(15, 40);
        reservationHistoryTextArea.setEditable(false);

        // Add action listeners
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomNumber = Integer.parseInt(roomNumberField.getText());
                String roomType = (String) roomTypeComboBox.getSelectedItem();
                checkIn(roomNumber, roomType);
            }
        });

        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int roomNumber = Integer.parseInt(roomNumberField.getText());
                checkOut(roomNumber);
            }
        });

        viewStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewStatus();
            }
        });

        viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewReservationHistory();
            }
        });

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Set layout
        setLayout(new BorderLayout());

        // Create panel for input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(roomNumberLabel);
        inputPanel.add(roomNumberField);
        inputPanel.add(roomTypeLabel);
        inputPanel.add(roomTypeComboBox);
        inputPanel.add(checkInButton);
        inputPanel.add(checkOutButton);

        // Create panel for status and history
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.add(new JLabel("Room Status:"));
        statusPanel.add(new JScrollPane(statusTextArea));
        statusPanel.add(new JLabel("Reservation History:"));
        statusPanel.add(new JScrollPane(reservationHistoryTextArea));

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(viewStatusButton);
        buttonPanel.add(viewHistoryButton);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(statusPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void checkIn(int roomNumber, String roomType) {
        // Implement check-in logic here
        roomStatus.put(roomNumber, roomType);
        reservationHistory.put(roomNumber, new Date());
        JOptionPane.showMessageDialog(this, "Checked in to Room " + roomNumber + " (" + roomType + ") successfully!");
        updateStatusAndHistory();
    }

    private void checkOut(int roomNumber) {
        // Implement check-out logic here
        roomStatus.remove(roomNumber);
        JOptionPane.showMessageDialog(this, "Checked out from Room " + roomNumber + " successfully!");
        updateStatusAndHistory();
    }

    private void viewStatus() {
        // Display the current room status
        StringBuilder status = new StringBuilder("Room Status:\n");
        for (Map.Entry<Integer, String> entry : roomStatus.entrySet()) {
            status.append("Room ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        statusTextArea.setText(status.toString());
    }

    private void viewReservationHistory() {
        // Display the reservation history
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder history = new StringBuilder("Reservation History:\n");
        for (Map.Entry<Integer, Date> entry : reservationHistory.entrySet()) {
            history.append("Room ").append(entry.getKey()).append(": ").append(dateFormat.format(entry.getValue())).append("\n");
        }
        reservationHistoryTextArea.setText(history.toString());
    }

    private void updateStatusAndHistory() {
        // Update both status and reservation history displays
        viewStatus();
        viewReservationHistory();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HotelManagementSystem().setVisible(true);
            }
        });
    }
}

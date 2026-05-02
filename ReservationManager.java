import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

// ReservationManager.java
// Uses a 2D array to represent the coffee shop floor:
//   - 10 tables (rows: index 0-9 = tables 1-10)
//   - 4 seats per table (columns: index 0-3 = seats 1-4)
// null cell = free seat, Reservation object = booked seat.
// Every change is saved to reservations.txt immediately.
public class ReservationManager {

    private static final String RESERVATION_FILE = "reservations.txt";
    private static final int NUM_TABLES = 10;
    private static final int SEATS_PER_TABLE = 4;

    // 2D array: tables[tableIndex][seatIndex]
    private Reservation[][] tables = new Reservation[NUM_TABLES][SEATS_PER_TABLE];

    // Queue - walk-in customers waiting for a table (FIFO)
    private WaitingQueue waitingList = new WaitingQueue();

    // Book a specific seat at a specific table, then save to file
    public boolean addReservation(int tableNum, int seatNum, String customerName, String date) {
        if (tableNum < 1 || tableNum > NUM_TABLES) {
            System.out.println("Invalid table number. Choose 1-" + NUM_TABLES + ".");
            return false;
        }
        if (seatNum < 1 || seatNum > SEATS_PER_TABLE) {
            System.out.println("Invalid seat number. Choose 1-" + SEATS_PER_TABLE + ".");
            return false;
        }
        int t = tableNum - 1;
        int s = seatNum - 1;
        if (tables[t][s] != null) {
            System.out.println("Seat " + seatNum + " at Table " + tableNum + " is already booked by " + tables[t][s].getCustomerName() + ".");
            return false;
        }
        tables[t][s] = new Reservation(customerName, date);
        System.out.println("Booked: Table " + tableNum + ", Seat " + seatNum + " for " + customerName + " on " + date);
        saveToFile();
        return true;
    }

    // Cancel a specific seat booking, then save to file
    public boolean cancelReservation(int tableNum, int seatNum) {
        if (tableNum < 1 || tableNum > NUM_TABLES || seatNum < 1 || seatNum > SEATS_PER_TABLE) {
            System.out.println("Invalid table or seat number.");
            return false;
        }
        int t = tableNum - 1;
        int s = seatNum - 1;
        if (tables[t][s] == null) {
            System.out.println("Seat " + seatNum + " at Table " + tableNum + " is already empty.");
            return false;
        }
        System.out.println("Cancelled: " + tables[t][s].getCustomerName() + " at Table " + tableNum + ", Seat " + seatNum);
        tables[t][s] = null;
        saveToFile();
        return true;
    }

    // saveToFile: rewrites the entire reservations.txt with all currently booked seats.
    // Called after every booking/cancellation so crashes never lose data.
    public void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(RESERVATION_FILE));
            for (int t = 0; t < NUM_TABLES; t++) {
                for (int s = 0; s < SEATS_PER_TABLE; s++) {
                    if (tables[t][s] != null) {
                        writer.println(t + "|" + s + "|"
                                + tables[t][s].getCustomerName() + "|"
                                + tables[t][s].getDate());
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving reservations to file: " + e.getMessage());
        }
    }

    // loadFromFile: reads reservations.txt at startup and restores the table layout.
    // If the file does not exist yet, all seats start as empty.
    public void loadFromFile() {
        File file = new File(RESERVATION_FILE);
        if (!file.exists()) return;
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    int t            = Integer.parseInt(parts[0]);
                    int s            = Integer.parseInt(parts[1]);
                    String custName  = parts[2];
                    String date      = parts[3];
                    if (t >= 0 && t < NUM_TABLES && s >= 0 && s < SEATS_PER_TABLE) {
                        tables[t][s] = new Reservation(custName, date);
                        count++;
                    }
                }
            }
            reader.close();
            System.out.println("Loaded " + count + " reservation(s) from " + RESERVATION_FILE);
        } catch (IOException e) {
            System.out.println("Error loading reservations from file: " + e.getMessage());
        }
    }

    // Show all 10 tables with all 4 seats each
    public void showAllTables() {
        System.out.println("\n--- All Reservation Tables (10 tables x 4 seats) ---");
        for (int t = 0; t < NUM_TABLES; t++) {
            System.out.println("Table " + (t + 1) + ":");
            for (int s = 0; s < SEATS_PER_TABLE; s++) {
                if (tables[t][s] == null) {
                    System.out.println("  Seat " + (s + 1) + ": (empty)");
                } else {
                    System.out.println("  Seat " + (s + 1) + ": " + tables[t][s].getCustomerName() + " | " + tables[t][s].getDate());
                }
            }
        }
    }

    // Show only one specific table
    public void showTable(int tableNum) {
        if (tableNum < 1 || tableNum > NUM_TABLES) {
            System.out.println("Invalid table number. Choose 1-" + NUM_TABLES + ".");
            return;
        }
        int t = tableNum - 1;
        System.out.println("\nTable " + tableNum + ":");
        for (int s = 0; s < SEATS_PER_TABLE; s++) {
            if (tables[t][s] == null) {
                System.out.println("  Seat " + (s + 1) + ": (empty)");
            } else {
                System.out.println("  Seat " + (s + 1) + ": " + tables[t][s].getCustomerName() + " | " + tables[t][s].getDate());
            }
        }
    }

    // Find all seats booked by a customer name
    public void searchByCustomerName(String name) {
        System.out.println("\n--- Search results for: " + name + " ---");
        boolean found = false;
        for (int t = 0; t < NUM_TABLES; t++) {
            for (int s = 0; s < SEATS_PER_TABLE; s++) {
                if (tables[t][s] != null && tables[t][s].getCustomerName().toLowerCase().contains(name.toLowerCase())) {
                    System.out.println("Table " + (t + 1) + ", Seat " + (s + 1) + " | " + tables[t][s].getCustomerName() + " | " + tables[t][s].getDate());
                    found = true;
                }
            }
        }
        if (!found) System.out.println("Not found.");
    }

    // Find all seats booked for a given date
    public void searchByDate(String date) {
        System.out.println("\n--- Reservations on: " + date + " ---");
        boolean found = false;
        for (int t = 0; t < NUM_TABLES; t++) {
            for (int s = 0; s < SEATS_PER_TABLE; s++) {
                if (tables[t][s] != null && tables[t][s].getDate().equalsIgnoreCase(date)) {
                    System.out.println("Table " + (t + 1) + ", Seat " + (s + 1) + " | " + tables[t][s].getCustomerName());
                    found = true;
                }
            }
        }
        if (!found) System.out.println("Not found.");
    }

    // --- Queue (waiting list) operations ---
    public void addToWaitingList(String name) { waitingList.addCustomer(name); }
    public String serveNextWaiting() { return waitingList.serveNextCustomer(); }
    public void showWaitingList() { waitingList.displayQueue(); }
    public String peekNextWaiting() { return waitingList.peekNextCustomer(); }
    public boolean isCustomerWaiting(String name) { return waitingList.isInQueue(name); }
    public boolean removeFromWaitingList(String name) { return waitingList.removeCustomer(name); }
    public int waitingListSize() { return waitingList.size(); }

    // Count how many seats are currently booked
    public int countBookedSeats() {
        int count = 0;
        for (int t = 0; t < NUM_TABLES; t++) {
            for (int s = 0; s < SEATS_PER_TABLE; s++) {
                if (tables[t][s] != null) count++;
            }
        }
        return count;
    }

    public int getTotalSeats() { return NUM_TABLES * SEATS_PER_TABLE; }
    public int getNumTables() { return NUM_TABLES; }
    public int getSeatsPerTable() { return SEATS_PER_TABLE; }
}

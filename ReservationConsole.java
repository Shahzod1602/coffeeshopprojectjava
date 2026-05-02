import java.util.Scanner;

public class ReservationConsole {
    private Scanner scanner;
    private ReservationManager reservationManager;

    public ReservationConsole(Scanner scanner, ReservationManager reservationManager) {
        this.scanner = scanner;
        this.reservationManager = reservationManager;
    }

    public void runMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Reservation Management ---");
            System.out.println("  Tables: 1-10  |  Seats per table: 1-4");
            System.out.println("1. Book a Seat");
            System.out.println("2. Cancel a Booking");
            System.out.println("3. View All Tables");
            System.out.println("4. View a Specific Table");
            System.out.println("5. Search by Customer Name");
            System.out.println("6. Search by Date");
            System.out.println("7. View Waiting List");
            System.out.println("8. Add Customer to Waiting List");
            System.out.println("9. Seat Next Waiting Customer");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter table number (1-10): ");
                    int tableNum = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter seat number (1-4): ");
                    int seatNum = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter customer name: ");
                    String custName = scanner.nextLine();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    reservationManager.addReservation(tableNum, seatNum, custName, date);
                    break;
                case 2:
                    System.out.print("Enter table number (1-10): ");
                    int cancelTable = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter seat number (1-4): ");
                    int cancelSeat = Integer.parseInt(scanner.nextLine());
                    reservationManager.cancelReservation(cancelTable, cancelSeat);
                    break;
                case 3:
                    reservationManager.showAllTables();
                    break;
                case 4:
                    System.out.print("Enter table number (1-10): ");
                    reservationManager.showTable(Integer.parseInt(scanner.nextLine()));
                    break;
                case 5:
                    System.out.print("Enter customer name: ");
                    reservationManager.searchByCustomerName(scanner.nextLine());
                    break;
                case 6:
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    reservationManager.searchByDate(scanner.nextLine());
                    break;
                case 7:
                    // Queue: show all customers waiting for a table
                    reservationManager.showWaitingList();
                    break;
                case 8:
                    // Queue: add a walk-in customer to the back of the waiting line
                    System.out.print("Enter customer name: ");
                    String waitName = scanner.nextLine();
                    if (reservationManager.isCustomerWaiting(waitName)) {
                        System.out.println(waitName + " is already in the waiting list.");
                    } else {
                        reservationManager.addToWaitingList(waitName);
                    }
                    break;
                case 9:
                    // Queue: seat the customer who has been waiting the longest (FIFO)
                    reservationManager.serveNextWaiting();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

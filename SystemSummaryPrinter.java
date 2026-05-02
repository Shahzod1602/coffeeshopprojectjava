// SystemSummaryPrinter.java
// Prints a high-level summary of the whole coffee shop system

public class SystemSummaryPrinter {
    private SystemSummaryPrinter() {
    }

    public static void print(OrderManager orderManager, MenuManager menuManager,
            InventoryManager inventoryManager, EmployeeManager employeeManager,
            ReservationManager reservationManager) {
        System.out.println("\n--- System Summary ---");
        System.out.println("Total Orders: " + orderManager.getTotalOrders());
        System.out.println("  Current Orders: " + orderManager.countOrdersByStatus("Current"));
        System.out.println("  Completed Orders: " + orderManager.countOrdersByStatus("Completed"));
        System.out.println("  Cancelled Orders: " + orderManager.countOrdersByStatus("Canceled"));
        System.out.println("Total Menu Items: " + menuManager.getTotalMenuItems());
        System.out.println("Total Inventory Items: " + inventoryManager.getTotalInventoryItems());
        System.out.println("Total Employees: " + employeeManager.getTotalEmployees());
        System.out.println("Tables: " + reservationManager.getNumTables() + " tables x " + reservationManager.getSeatsPerTable() + " seats = " + reservationManager.getTotalSeats() + " total seats");
        System.out.println("  Booked Seats: " + reservationManager.countBookedSeats());
        System.out.println("  Free Seats  : " + (reservationManager.getTotalSeats() - reservationManager.countBookedSeats()));
        System.out.println("  Waiting List: " + reservationManager.waitingListSize());
    }
}

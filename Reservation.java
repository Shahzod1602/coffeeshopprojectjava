// Reservation.java
// Holds the data for one booked seat: who booked it and for what date.
// Position (table + seat) is tracked by the 2D array in ReservationManager.
public class Reservation {
    private String customerName;
    private String date;

    public Reservation(String customerName, String date) {
        this.customerName = customerName;
        this.date = date;
    }

    public String getCustomerName() { return customerName; }
    public String getDate() { return date; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setDate(String date) { this.date = date; }

    public void displayInfo() {
        System.out.println("Customer: " + customerName);
        System.out.println("Date    : " + date);
    }
}

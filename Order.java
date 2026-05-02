public class Order {
    private int orderId;
    private String customerName;
    private String date;
    private String status;
    private String itemName;
    private double totalPrice;

    public Order(int orderId, String customerName, String date, String status, String itemName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.date = date;
        this.status = status;
        this.itemName = itemName;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
    public String getItemName() { return itemName; }
    public double getTotalPrice() { return totalPrice; }
    public void setStatus(String status) { this.status = status; }

    public void displayInfo() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Date: " + date);
        System.out.println("Status: " + status);
        System.out.println("Item: " + itemName);
        System.out.printf("Price: %.2f%n", totalPrice);
    }
}

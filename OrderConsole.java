import java.util.ArrayList;
import java.util.Scanner;


public class OrderConsole {
    private Scanner scanner;
    private OrderManager orderManager;
    private MenuManager menuManager;

    public OrderConsole(Scanner scanner, OrderManager orderManager, MenuManager menuManager) {
        this.scanner = scanner;
        this.orderManager = orderManager;
        this.menuManager = menuManager;
    }

    public void runMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Order Management ---");
            System.out.println("1.  Create Order");
            System.out.println("2.  Cancel Order");
            System.out.println("3.  Complete Order");
            System.out.println("4.  View All Orders");
            System.out.println("5.  Search by Customer Name");
            System.out.println("6.  Search by Date");
            System.out.println("7.  Search by Status");
            System.out.println("8.  Search by Order ID");
            System.out.println("9.  View Recent Orders");
            System.out.println("10. View Prep Queue");
            System.out.println("11. Process Next Prep Order");
            System.out.println("12. Mark Order as Priority");
            System.out.println("0.  Back");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    menuManager.showAllMenuItems();
                    if (menuManager.getTotalMenuItems() == 0) {
                        System.out.println("No menu items available. Add items to the menu first.");
                        break;
                    }
                    System.out.print("Enter item ID to order: ");
                    MenuItem selectedItem = menuManager.searchById(Integer.parseInt(scanner.nextLine()));
                    if (selectedItem == null) {
                        System.out.println("Item not found.");
                        break;
                    }
                    int orderId = orderManager.getNextOrderId();
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    orderManager.addOrder(new Order(orderId, customerName, date, "Current", selectedItem.getName(), selectedItem.getPrice()));
                    System.out.println("Order created with ID: " + orderId + " | Item: " + selectedItem.getName() + " | Price: " + selectedItem.getPrice());
                    break;
                case 2:
                    System.out.print("Enter order ID to cancel: ");
                    if (orderManager.cancelOrder(Integer.parseInt(scanner.nextLine()))) {
                        System.out.println("Order cancelled.");
                    } else {
                        System.out.println("Not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter order ID to complete: ");
                    if (orderManager.completeOrder(Integer.parseInt(scanner.nextLine()))) {
                        System.out.println("Order completed.");
                    } else {
                        System.out.println("Not found.");
                    }
                    break;
                case 4:
                    orderManager.showAllOrders();
                    break;
                case 5:
                    System.out.print("Enter customer name: ");
                    printOrders(orderManager.searchByCustomerName(scanner.nextLine()));
                    break;
                case 6:
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    printOrders(orderManager.searchByDate(scanner.nextLine()));
                    break;
                case 7:
                    System.out.print("Enter status (Current/Completed/Canceled): ");
                    printOrders(orderManager.searchByStatus(scanner.nextLine()));
                    break;
                case 8:
                    System.out.print("Enter order ID: ");
                    Order found = orderManager.searchById(Integer.parseInt(scanner.nextLine()));
                    if (found == null) System.out.println("Not found.");
                    else found.displayInfo();
                    break;
                case 9:
                    // Stack: shows the last 10 created orders, newest first
                    orderManager.showRecentOrders();
                    break;
                case 10:
                    // Deque: shows the barista's current prep queue
                    orderManager.showPrepQueue();
                    break;
                case 11:
                    // Deque: barista picks up the next order from the front
                    orderManager.processNextPrepOrder();
                    break;
                case 12:
                    // Deque: move an order to the front of the prep queue (VIP)
                    System.out.print("Enter order ID to mark as priority: ");
                    Order priOrder = orderManager.searchById(Integer.parseInt(scanner.nextLine()));
                    if (priOrder == null) {
                        System.out.println("Order not found.");
                    } else {
                        orderManager.addToPrepQueueFront(priOrder);
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void printOrders(ArrayList<Order> results) {
        if (results.isEmpty()) {
            System.out.println("Not found.");
        } else {
            for (int i = 0; i < results.size(); i++) {
                results.get(i).displayInfo();
                System.out.println("------------------------------");
            }
        }
    }
}

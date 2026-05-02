import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

public class OrderManager {

    private static final String ORDER_FILE = "orders.txt";

    // Dynamic Array - main storage, grows automatically as orders are added
    private ArrayList<Order> orders = new ArrayList<Order>();

    // Auto-incrementing ID counter — always one higher than the max ID seen
    private int nextOrderId = 1;

    // Stack - tracks recently created orders (LIFO: newest always on top)
    private OrderStack recentOrdersStack = new OrderStack(10);

    // Deque - barista's prep queue (priority orders go to front, regular to back)
    private PrepDeque prepQueue = new PrepDeque();

    // Returns the next available order ID and advances the counter.
    public int getNextOrderId() {
        return nextOrderId++;
    }

    // Internal add: updates data structures only, does NOT save to file.
    // Used by loadFromFile() to avoid rewriting the file on every loaded line.
    private void addOrderInternal(Order order) {
        orders.add(order);
        recentOrdersStack.push(order);
        if (order.getStatus().equalsIgnoreCase("Current")) {
            prepQueue.addRegularOrder(order);
        }
        // Keep nextOrderId ahead of any loaded ID
        if (order.getOrderId() >= nextOrderId) {
            nextOrderId = order.getOrderId() + 1;
        }
    }

    // addOrder: create a regular order, then immediately save to file
    public void addOrder(Order order) {
        addOrderInternal(order);
        saveToFile();
    }

    // addPriorityOrder: VIP order goes to front of prep queue, then save
    public void addPriorityOrder(Order order) {
        orders.add(order);
        recentOrdersStack.push(order);
        prepQueue.addPriorityOrder(order);
        saveToFile();
    }

    public boolean cancelOrder(int orderId) {
        Order order = searchById(orderId);
        if (order != null) {
            order.setStatus("Canceled");
            saveToFile();
            return true;
        }
        return false;
    }

    public boolean completeOrder(int orderId) {
        Order order = searchById(orderId);
        if (order != null) {
            order.setStatus("Completed");
            saveToFile();
            return true;
        }
        return false;
    }

    // saveToFile: rewrites the entire orders.txt file with current data.
    // Called after every change so a crash never loses more than one operation.
    public void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(ORDER_FILE));
            for (int i = 0; i < orders.size(); i++) {
                Order o = orders.get(i);
                writer.println(o.getOrderId() + "|" + o.getCustomerName() + "|"
                        + o.getDate() + "|" + o.getStatus() + "|"
                        + o.getItemName() + "|" + o.getTotalPrice());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving orders to file: " + e.getMessage());
        }
    }

    // loadFromFile: reads orders.txt at startup and restores previous session data.
    // If the file does not exist yet, starts fresh with no orders.
    public void loadFromFile() {
        File file = new File(ORDER_FILE);
        if (!file.exists()) return;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    int id            = Integer.parseInt(parts[0]);
                    String custName   = parts[1];
                    String date       = parts[2];
                    String status     = parts[3];
                    String itemName   = parts[4];
                    double price      = Double.parseDouble(parts[5]);
                    addOrderInternal(new Order(id, custName, date, status, itemName, price));
                }
            }
            reader.close();
            System.out.println("Loaded " + orders.size() + " order(s) from " + ORDER_FILE);
        } catch (IOException e) {
            System.out.println("Error loading orders from file: " + e.getMessage());
        }
    }

    public void showAllOrders() {
        System.out.println("\n--- All Orders ---");
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).displayInfo();
            System.out.println("------------------------------");
        }
    }

    public Order searchById(int orderId) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId() == orderId) return orders.get(i);
        }
        return null;
    }

    public ArrayList<Order> searchByCustomerName(String name) {
        ArrayList<Order> results = new ArrayList<Order>();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getCustomerName().toLowerCase().contains(name.toLowerCase()))
                results.add(orders.get(i));
        }
        return results;
    }

    public ArrayList<Order> searchByDate(String date) {
        ArrayList<Order> results = new ArrayList<Order>();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getDate().equalsIgnoreCase(date))
                results.add(orders.get(i));
        }
        return results;
    }

    public ArrayList<Order> searchByStatus(String status) {
        ArrayList<Order> results = new ArrayList<Order>();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getStatus().equalsIgnoreCase(status))
                results.add(orders.get(i));
        }
        return results;
    }

    // --- Stack (recent orders) operations ---
    public void showRecentOrders() { recentOrdersStack.displayAll(); }
    public Order popRecentOrder() { return recentOrdersStack.pop(); }
    public Order peekRecentOrder() { return recentOrdersStack.peek(); }
    public int recentOrdersCount() { return recentOrdersStack.size(); }

    // --- Deque (prep queue) operations ---
    public void showPrepQueue() { prepQueue.displayAll(); }
    public Order processNextPrepOrder() { return prepQueue.processNextOrder(); }
    public void addToPrepQueueFront(Order order) { prepQueue.addPriorityOrder(order); }
    public Order removeLastFromPrepQueue() { return prepQueue.removeLastOrder(); }
    public Order peekNextPrepOrder() { return prepQueue.peekNext(); }

    public int getTotalOrders() { return orders.size(); }

    public int countOrdersByStatus(String status) {
        int count = 0;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getStatus().equalsIgnoreCase(status)) count++;
        }
        return count;
    }
}

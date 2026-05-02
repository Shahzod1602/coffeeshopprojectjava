import java.util.ArrayDeque;
import java.util.Deque;

// PrepDeque.java
// WHY DEQUE?
// The barista's prep queue has two types of orders:
//   - Regular orders: join at the BACK (normal FIFO fairness)
//   - Priority/VIP orders: inserted at the FRONT (served urgently)
//   - Processing always removes from the FRONT
//   - If a customer cancels while waiting, their order is removed from the BACK
// A plain Queue can't insert at the front, and a Stack can't insert at the back.
// Only a Deque supports access from BOTH ends, which is exactly what we need here.
// Five operations: addRegularOrder, addPriorityOrder, processNextOrder, removeLastOrder, displayAll
public class PrepDeque {

    private Deque<Order> prepQueue;

    public PrepDeque() {
        prepQueue = new ArrayDeque<Order>();
    }

    // addRegularOrder: regular order goes to the back of the prep queue
    public void addRegularOrder(Order order) {
        prepQueue.addLast(order);
        System.out.println("Order #" + order.getOrderId() + " (" + order.getItemName() + ") added to prep queue.");
    }

    // addPriorityOrder: VIP or urgent order goes to the front of the prep queue
    public void addPriorityOrder(Order order) {
        prepQueue.addFirst(order);
        System.out.println("Order #" + order.getOrderId() + " (" + order.getItemName() + ") added as PRIORITY to front of prep queue.");
    }

    // processNextOrder: barista picks up the next order from the front
    public Order processNextOrder() {
        if (prepQueue.isEmpty()) {
            System.out.println("Prep queue is empty. No orders to process.");
            return null;
        }
        Order next = prepQueue.pollFirst();
        System.out.println("Now preparing: Order #" + next.getOrderId() + " for " + next.getCustomerName() + " - " + next.getItemName());
        return next;
    }

    // removeLastOrder: cancel the most recently added order from the back
    public Order removeLastOrder() {
        if (prepQueue.isEmpty()) {
            System.out.println("Prep queue is empty.");
            return null;
        }
        Order last = prepQueue.pollLast();
        System.out.println("Removed from back of prep queue: Order #" + last.getOrderId());
        return last;
    }

    // displayAll: show the full prep queue from front to back
    public void displayAll() {
        if (prepQueue.isEmpty()) {
            System.out.println("Prep queue is empty.");
            return;
        }
        System.out.println("\n--- Order Prep Queue (front to back) ---");
        int pos = 1;
        for (Order o : prepQueue) {
            System.out.println("  " + pos + ". Order #" + o.getOrderId() + " | " + o.getCustomerName() + " | " + o.getItemName());
            pos++;
        }
        System.out.println("Total in queue: " + prepQueue.size());
    }

    // peekNext: see the next order to be prepared without removing it
    public Order peekNext() {
        if (prepQueue.isEmpty()) {
            System.out.println("Prep queue is empty.");
            return null;
        }
        Order next = prepQueue.peekFirst();
        System.out.println("Next to prepare: Order #" + next.getOrderId() + " - " + next.getItemName());
        return next;
    }

    // peekLast: see the last (most recently added) order without removing it
    public Order peekLast() {
        if (prepQueue.isEmpty()) {
            System.out.println("Prep queue is empty.");
            return null;
        }
        Order last = prepQueue.peekLast();
        System.out.println("Last in queue: Order #" + last.getOrderId() + " - " + last.getItemName());
        return last;
    }

    public boolean isEmpty() { return prepQueue.isEmpty(); }
    public int size() { return prepQueue.size(); }
}

import java.util.LinkedList;
import java.util.Queue;

// WaitingQueue.java
// WHY QUEUE?
// When all tables are full, walk-in customers wait in line.
// The first person who arrived must get the next available table - that is FIFO.
// A queue enforces fairness: whoever waited the longest is served next.
// Using a stack here would be unfair (last person in gets served first).
// Five operations: addCustomer, serveNextCustomer, peekNextCustomer, isInQueue, displayQueue
public class WaitingQueue {

    private Queue<String> waitingLine;

    public WaitingQueue() {
        waitingLine = new LinkedList<String>();
    }

    // addCustomer: customer joins the back of the waiting line
    public void addCustomer(String name) {
        waitingLine.add(name);
        System.out.println(name + " added to waiting list. Position: " + waitingLine.size());
    }

    // serveNextCustomer: seat the customer who has been waiting the longest (front)
    public String serveNextCustomer() {
        if (waitingLine.isEmpty()) {
            System.out.println("No customers are waiting.");
            return null;
        }
        String next = waitingLine.poll();
        System.out.println("Now seating: " + next);
        return next;
    }

    // peekNextCustomer: see who is next without removing them
    public String peekNextCustomer() {
        if (waitingLine.isEmpty()) {
            System.out.println("No customers are waiting.");
            return null;
        }
        String next = waitingLine.peek();
        System.out.println("Next in line: " + next);
        return next;
    }

    // isInQueue: check if a specific customer is already in the waiting list
    public boolean isInQueue(String name) {
        for (String customer : waitingLine) {
            if (customer.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // displayQueue: show all waiting customers in their waiting order
    public void displayQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println("No customers in waiting list.");
            return;
        }
        System.out.println("\n--- Customer Waiting List ---");
        int pos = 1;
        for (String customer : waitingLine) {
            System.out.println("  " + pos + ". " + customer);
            pos++;
        }
        System.out.println("Total waiting: " + waitingLine.size());
    }

    // removeCustomer: customer left before being seated (remove by name)
    public boolean removeCustomer(String name) {
        boolean removed = waitingLine.remove(name);
        if (removed) {
            System.out.println(name + " removed from waiting list.");
        } else {
            System.out.println(name + " was not found in waiting list.");
        }
        return removed;
    }

    public boolean isEmpty() { return waitingLine.isEmpty(); }
    public int size() { return waitingLine.size(); }

    public void clearQueue() {
        waitingLine.clear();
        System.out.println("Waiting list cleared.");
    }
}

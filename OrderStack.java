// OrderStack.java
// WHY STACK?
// The cashier always needs to see the most recently placed order on top (LIFO).
// If a wrong order was entered, popping removes the last entry instantly.
// A stack gives direct access to the latest order without searching the whole list.
// Five operations: push, pop, peek, size, displayAll
public class OrderStack {

    private Order[] stack;
    private int top;
    private int maxSize;

    public OrderStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new Order[maxSize];
        top = -1;
    }

    // push: place a new order on top of the stack
    public boolean push(Order order) {
        if (top == maxSize - 1) {
            System.out.println("Recent orders stack is full (max " + maxSize + ").");
            return false;
        }
        stack[++top] = order;
        return true;
    }

    // pop: remove and return the most recent order (undo last entry)
    public Order pop() {
        if (top == -1) {
            System.out.println("No recent orders in stack.");
            return null;
        }
        Order removed = stack[top];
        stack[top] = null;
        top--;
        System.out.println("Removed from recent orders: Order #" + removed.getOrderId());
        return removed;
    }

    // peek: see the most recent order without removing it
    public Order peek() {
        if (top == -1) {
            System.out.println("Stack is empty.");
            return null;
        }
        return stack[top];
    }

    // size: how many orders are currently in the stack
    public int size() {
        return top + 1;
    }

    // isEmpty: check if there are no recent orders
    public boolean isEmpty() {
        return top == -1;
    }

    // displayAll: show all recent orders from newest (top) to oldest (bottom)
    public void displayAll() {
        if (top == -1) {
            System.out.println("No recent orders in stack.");
            return;
        }
        System.out.println("\n--- Recent Orders (newest first) ---");
        for (int i = top; i >= 0; i--) {
            stack[i].displayInfo();
            System.out.println("---");
        }
        System.out.println("Total in stack: " + (top + 1));
    }

    // clear: reset the entire stack
    public void clear() {
        for (int i = 0; i <= top; i++) {
            stack[i] = null;
        }
        top = -1;
        System.out.println("Recent orders stack cleared.");
    }
}

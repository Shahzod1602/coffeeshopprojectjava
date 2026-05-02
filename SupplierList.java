// SupplierList.java
// WHY LINKED LIST?
// The coffee shop works with a varying number of ingredient suppliers whose
// contracts begin and end unpredictably — the list has no fixed upper bound.
// Suppliers are appended as new partnerships form and removed when contracts
// end; no index-based slot is ever needed, so a fixed array would waste space
// and an ArrayList's internal array resizing adds unnecessary overhead.
// Searching and deleting by supplier name both require a sequential walk,
// which is the natural operation of a linked structure with no extra cost.
// Operations: add, search, delete
public class SupplierList {

    private static class Node {
        String supplierName;
        String product;
        Node next;

        Node(String supplierName, String product) {
            this.supplierName = supplierName;
            this.product = product;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public SupplierList() {
        head = null;
        size = 0;
    }

    // add: append a new supplier at the end of the list
    public void add(String supplierName, String product) {
        Node newNode = new Node(supplierName, product);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("Supplier added: " + supplierName + " (provides: " + product + ")");
    }

    // search: find a supplier by name and display their details
    public boolean search(String supplierName) {
        Node current = head;
        while (current != null) {
            if (current.supplierName.equalsIgnoreCase(supplierName)) {
                System.out.println("Found: " + current.supplierName + " | Product: " + current.product);
                return true;
            }
            current = current.next;
        }
        System.out.println("Supplier '" + supplierName + "' not found.");
        return false;
    }

    // delete: remove a supplier by name when their contract ends
    public boolean delete(String supplierName) {
        if (head == null) {
            System.out.println("Supplier list is empty.");
            return false;
        }
        if (head.supplierName.equalsIgnoreCase(supplierName)) {
            System.out.println("Supplier removed: " + head.supplierName);
            head = head.next;
            size--;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.supplierName.equalsIgnoreCase(supplierName)) {
                System.out.println("Supplier removed: " + current.next.supplierName);
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        System.out.println("Supplier '" + supplierName + "' not found.");
        return false;
    }

    // displayAll: show every supplier currently in the directory
    public void displayAll() {
        if (head == null) {
            System.out.println("No suppliers in the directory.");
            return;
        }
        System.out.println("\n--- Supplier Directory ---");
        Node current = head;
        int pos = 1;
        while (current != null) {
            System.out.println("  " + pos + ". " + current.supplierName + " | Product: " + current.product);
            current = current.next;
            pos++;
        }
        System.out.println("Total suppliers: " + size);
    }

    public boolean isEmpty() { return head == null; }
    public int size() { return size; }
}

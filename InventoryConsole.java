import java.util.ArrayList;
import java.util.Scanner;

public class InventoryConsole {
    private Scanner scanner;
    private InventoryManager inventoryManager;

    public InventoryConsole(Scanner scanner, InventoryManager inventoryManager) {
        this.scanner = scanner;
        this.inventoryManager = inventoryManager;
    }

    public void runMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Inventory Management ---");
            System.out.println("1. Add Inventory Item");
            System.out.println("2. Update Quantity");
            System.out.println("3. Remove Inventory Item");
            System.out.println("4. View All Inventory Items");
            System.out.println("5. Search by Name");
            System.out.println("6. Search by Supplier");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter inventory ID: ");
                    int invId = Integer.parseInt(scanner.nextLine());
                    if (inventoryManager.searchById(invId) != null) {
                        System.out.println("Inventory ID already exists.");
                        break;
                    }
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter supplier: ");
                    String supplier = scanner.nextLine();
                    inventoryManager.addInventoryItem(new InventoryItem(invId, name, quantity, supplier));
                    System.out.println("Inventory item added.");
                    break;
                case 2:
                    System.out.print("Enter inventory ID to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    if (inventoryManager.searchById(updateId) == null) {
                        System.out.println("Not found.");
                        break;
                    }
                    System.out.print("Enter new quantity: ");
                    int newQty = Integer.parseInt(scanner.nextLine());
                    inventoryManager.updateQuantity(updateId, newQty);
                    System.out.println("Quantity updated.");
                    break;
                case 3:
                    System.out.print("Enter inventory ID to remove: ");
                    if (inventoryManager.removeInventoryItem(Integer.parseInt(scanner.nextLine()))) {
                        System.out.println("Inventory item removed.");
                    } else {
                        System.out.println("Not found.");
                    }
                    break;
                case 4:
                    inventoryManager.showAllInventoryItems();
                    break;
                case 5:
                    System.out.print("Enter item name: ");
                    printItems(inventoryManager.searchByName(scanner.nextLine()));
                    break;
                case 6:
                    System.out.print("Enter supplier: ");
                    printItems(inventoryManager.searchBySupplier(scanner.nextLine()));
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void printItems(ArrayList<InventoryItem> results) {
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

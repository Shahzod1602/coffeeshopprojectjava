import java.util.ArrayList;
import java.util.Scanner;
public class MenuConsole {
    private Scanner scanner;
    private MenuManager menuManager;

    public MenuConsole(Scanner scanner, MenuManager menuManager) {
        this.scanner = scanner;
        this.menuManager = menuManager;
    }

    public void runMenu() {
        int choice;
        do {
            System.out.println("\nMenu Management");
            System.out.println("1. Add Menu Item");
            System.out.println("2. Update Menu Item");
            System.out.println("3. Remove Menu Item");
            System.out.println("4. View All Menu Items");
            System.out.println("5. Search by Name");
            System.out.println("6. Search by Category");
            System.out.println("7. Search by Price");
            System.out.println("8. Daily Specials Board");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter item ID: ");
                    int itemId = Integer.parseInt(scanner.nextLine());
                    if (menuManager.searchById(itemId) != null) {
                        System.out.println("Item ID already exists.");
                        break;
                    }
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    menuManager.addMenuItem(new MenuItem(itemId, name, category, price));
                    System.out.println("Menu item added.");
                    break;
                case 2:
                    System.out.print("Enter item ID to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    if (menuManager.searchById(updateId) == null) {
                        System.out.println("Not found.");
                        break;
                    }
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new category: ");
                    String newCategory = scanner.nextLine();
                    System.out.print("Enter new price: ");
                    double newPrice = Double.parseDouble(scanner.nextLine());
                    menuManager.updateMenuItem(updateId, newName, newCategory, newPrice);
                    System.out.println("Menu item updated.");
                    break;
                case 3:
                    System.out.print("Enter item ID to remove: ");
                    if (menuManager.removeMenuItem(Integer.parseInt(scanner.nextLine()))) {
                        System.out.println("Menu item removed.");
                    } else {
                        System.out.println("Not found.");
                    }
                    break;
                case 4:
                    menuManager.showAllMenuItems();
                    break;
                case 5:
                    System.out.print("Enter item name: ");
                    printItems(menuManager.searchByName(scanner.nextLine()));
                    break;
                case 6:
                    System.out.print("Enter category: ");
                    printItems(menuManager.searchByCategory(scanner.nextLine()));
                    break;
                case 7:
                    System.out.print("Enter price: ");
                    printItems(menuManager.searchByPrice(Double.parseDouble(scanner.nextLine())));
                    break;
                case 8:
                    specialsBoardMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void specialsBoardMenu() {
        int c;
        do {
            System.out.println("\n-- Daily Specials Board (5 slots) --");
            System.out.println("1. View Board");
            System.out.println("2. Add a Special");
            System.out.println("3. Remove a Special (by slot)");
            System.out.println("4. Update a Special (by slot)");
            System.out.println("5. Clear Whole Board");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            c = Integer.parseInt(scanner.nextLine());

            switch (c) {
                case 1:
                    menuManager.showDailySpecials();
                    break;
                case 2:
                    if (menuManager.isSpecialsBoardFull()) {
                        System.out.println("Board is full, remove one first.");
                        break;
                    }
                    System.out.print("Enter special name: ");
                    menuManager.addDailySpecial(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter slot number (1-5): ");
                    menuManager.removeDailySpecial(Integer.parseInt(scanner.nextLine()));
                    break;
                case 4:
                    System.out.print("Enter slot number (1-5): ");
                    int slot = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new special name: ");
                    menuManager.updateDailySpecial(slot, scanner.nextLine());
                    break;
                case 5:
                    menuManager.clearDailySpecials();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (c != 0);
    }

    private void printItems(ArrayList<MenuItem> results) {
        if (results.isEmpty()) {
            System.out.println("Not found.");
            return;
        }
        for (int i = 0; i < results.size(); i++) {
            results.get(i).displayInfo();
            System.out.println("------------------------------");
        }
    }
}
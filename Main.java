import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);

    private OrderManager orderManager = new OrderManager();
    private MenuManager menuManager = new MenuManager();
    private InventoryManager inventoryManager = new InventoryManager();
    private EmployeeManager employeeManager = new EmployeeManager();
    private ReservationManager reservationManager = new ReservationManager();

    private OrderConsole orderConsole = new OrderConsole(scanner, orderManager, menuManager);
    private MenuConsole menuConsole = new MenuConsole(scanner, menuManager);
    private InventoryConsole inventoryConsole = new InventoryConsole(scanner, inventoryManager);
    private EmployeeConsole employeeConsole = new EmployeeConsole(scanner, employeeManager);
    private ReservationConsole reservationConsole = new ReservationConsole(scanner, reservationManager);
    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        // Load saved data from previous sessions
        orderManager.loadFromFile();
        reservationManager.loadFromFile();

        // Shutdown hook: runs when the JVM exits (including Ctrl+C or window close).
        // Per-operation saves already handle most crashes, but this catches edge cases.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                orderManager.saveToFile();
                reservationManager.saveToFile();
            }
        });

        boolean running = true;
        while (running) {
            System.out.println("\n==============================");
            System.out.println("  Coffee Shop Management");
            System.out.println("==============================");
            System.out.println("1. Order Management");
            System.out.println("2. Menu Management");
            System.out.println("3. Inventory Management");
            System.out.println("4. Employee Management");
            System.out.println("5. Reservation Management");
            System.out.println("6. System Summary");
            System.out.println("0. Exit");
            System.out.println("------------------------------");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: orderConsole.runMenu(); break;
                case 2: menuConsole.runMenu(); break;
                case 3: inventoryConsole.runMenu(); break;
                case 4: employeeConsole.runMenu(); break;
                case 5: reservationConsole.runMenu(); break;
                case 6:
                    SystemSummaryPrinter.print(orderManager, menuManager,
                            inventoryManager, employeeManager, reservationManager);
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}

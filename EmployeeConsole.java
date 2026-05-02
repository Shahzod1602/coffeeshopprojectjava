import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeConsole {
    private Scanner scanner;
    private EmployeeManager employeeManager;

    public EmployeeConsole(Scanner scanner, EmployeeManager employeeManager) {
        this.scanner = scanner;
        this.employeeManager = employeeManager;
    }

    public void runMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. View All Employees");
            System.out.println("5. Search by Name");
            System.out.println("6. Search by Role");
            System.out.println("7. Search by Shift");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter employee ID: ");
                    int empId = Integer.parseInt(scanner.nextLine());
                    if (employeeManager.searchById(empId) != null) {
                        System.out.println("Employee ID already exists.");
                        break;
                    }
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter role: ");
                    String role = scanner.nextLine();
                    System.out.print("Enter shift: ");
                    String shift = scanner.nextLine();
                    employeeManager.addEmployee(new Employee(empId, name, role, shift));
                    System.out.println("Employee added.");
                    break;
                case 2:
                    System.out.print("Enter employee ID to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    if (employeeManager.searchById(updateId) == null) {
                        System.out.println("Not found.");
                        break;
                    }
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new role: ");
                    String newRole = scanner.nextLine();
                    System.out.print("Enter new shift: ");
                    String newShift = scanner.nextLine();
                    employeeManager.updateEmployee(updateId, newName, newRole, newShift);
                    System.out.println("Employee updated.");
                    break;
                case 3:
                    System.out.print("Enter employee ID to remove: ");
                    if (employeeManager.removeEmployee(Integer.parseInt(scanner.nextLine()))) {
                        System.out.println("Employee removed.");
                    } else {
                        System.out.println("Not found.");
                    }
                    break;
                case 4:
                    employeeManager.showAllEmployees();
                    break;
                case 5:
                    System.out.print("Enter name: ");
                    printEmployees(employeeManager.searchByName(scanner.nextLine()));
                    break;
                case 6:
                    System.out.print("Enter role: ");
                    printEmployees(employeeManager.searchByRole(scanner.nextLine()));
                    break;
                case 7:
                    System.out.print("Enter shift: ");
                    printEmployees(employeeManager.searchByShift(scanner.nextLine()));
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void printEmployees(ArrayList<Employee> results) {
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

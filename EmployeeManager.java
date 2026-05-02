import java.util.ArrayList;

public class EmployeeManager {
    private ArrayList<Employee> employees = new ArrayList<Employee>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public boolean updateEmployee(int employeeId, String name, String role, String shift) {
        Employee employee = searchById(employeeId);
        if (employee != null) {
            employee.setName(name);
            employee.setRole(role);
            employee.setShift(shift);
            return true;
        }
        return false;
    }

    public boolean removeEmployee(int employeeId) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeId() == employeeId) {
                employees.remove(i);
                return true;
            }
        }
        return false;
    }

    public void showAllEmployees() {
        System.out.println("\n--- All Employees ---");
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (int i = 0; i < employees.size(); i++) {
            employees.get(i).displayInfo();
            System.out.println("------------------------------");
        }
    }

    public Employee searchById(int employeeId) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeId() == employeeId) return employees.get(i);
        }
        return null;
    }

    public ArrayList<Employee> searchByName(String name) {
        ArrayList<Employee> results = new ArrayList<Employee>();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().toLowerCase().contains(name.toLowerCase()))
                results.add(employees.get(i));
        }
        return results;
    }

    public ArrayList<Employee> searchByRole(String role) {
        ArrayList<Employee> results = new ArrayList<Employee>();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getRole().equalsIgnoreCase(role))
                results.add(employees.get(i));
        }
        return results;
    }

    public ArrayList<Employee> searchByShift(String shift) {
        ArrayList<Employee> results = new ArrayList<Employee>();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getShift().equalsIgnoreCase(shift))
                results.add(employees.get(i));
        }
        return results;
    }

    public int getTotalEmployees() { return employees.size(); }
}

public class Employee extends Person {
    private int employeeId;
    private String role;
    private String shift;

    public Employee(int employeeId, String name, String role, String shift) {
        super(employeeId, name);
        this.employeeId = employeeId;
        this.role = role;
        this.shift = shift;
    }

    public int getEmployeeId() { return employeeId; }
    public String getRole() { return role; }
    public String getShift() { return shift; }
    public void setRole(String role) { this.role = role; }
    public void setShift(String shift) { this.shift = shift; }

    @Override
    public String getDetails() {
        return "Employee ID: " + employeeId + ", Name: " + getName() + ", Role: " + role + ", Shift: " + shift;
    }

    public void displayInfo() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + getName());
        System.out.println("Role: " + role);
        System.out.println("Shift: " + shift);
    }
}

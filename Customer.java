// Inheritance Example #2: Customer extends Person
// Method Overriding Example #2: getDetails() behaves differently than Employee.getDetails()
public class Customer extends Person {
    private String membershipType;

    public Customer(int customerId, String name, String membershipType) {
        super(customerId, name);
        this.membershipType = membershipType;
    }

    @Override
    public String getDetails() {
        return "Customer ID: " + getId() + ", Name: " + getName() + ", Membership: " + membershipType;
    }

    public void displayInfo() {
        System.out.println("Customer ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Membership: " + membershipType);
    }
}

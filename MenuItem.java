public class MenuItem extends Item {
    private int itemId;
    private String category;
    private double price;

    public MenuItem(int itemId, String name, String category, double price) {
        super(itemId, name);
        this.itemId = itemId;
        this.category = category;
        this.price = price;
    }

    public int getItemId() { return itemId; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public void displayInfo() {
        System.out.println("Item ID: " + itemId);
        System.out.println("Name: " + getName());
        System.out.println("Category: " + category);
        System.out.printf("Price: %.2f%n", price);
    }
}

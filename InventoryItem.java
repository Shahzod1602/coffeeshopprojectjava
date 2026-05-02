public class InventoryItem extends Item {
    private int inventoryId;
    private int quantity;
    private String supplier;

    public InventoryItem(int inventoryId, String name, int quantity, String supplier) {
        super(inventoryId, name);
        this.inventoryId = inventoryId;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public int getInventoryId() { return inventoryId; }
    public int getQuantity() { return quantity; }
    public String getSupplier() { return supplier; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public void displayInfo() {
        System.out.println("Inventory ID: " + inventoryId);
        System.out.println("Name: " + getName());
        System.out.println("Quantity: " + quantity);
        System.out.println("Supplier: " + supplier);
    }
}

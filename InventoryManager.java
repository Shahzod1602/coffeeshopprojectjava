import java.util.ArrayList;

public class InventoryManager {
    private ArrayList<InventoryItem> inventoryItems = new ArrayList<InventoryItem>();

    public void addInventoryItem(InventoryItem item) {
        inventoryItems.add(item);
    }

    public boolean updateQuantity(int inventoryId, int quantity) {
        InventoryItem item = searchById(inventoryId);
        if (item != null) {
            item.setQuantity(quantity);
            return true;
        }
        return false;
    }

    public boolean removeInventoryItem(int inventoryId) {
        for (int i = 0; i < inventoryItems.size(); i++) {
            if (inventoryItems.get(i).getInventoryId() == inventoryId) {
                inventoryItems.remove(i);
                return true;
            }
        }
        return false;
    }

    public void showAllInventoryItems() {
        System.out.println("\n--- All Inventory Items ---");
        if (inventoryItems.isEmpty()) {
            System.out.println("No inventory items found.");
            return;
        }
        for (int i = 0; i < inventoryItems.size(); i++) {
            inventoryItems.get(i).displayInfo();
            System.out.println("------------------------------");
        }
    }

    public InventoryItem searchById(int inventoryId) {
        for (int i = 0; i < inventoryItems.size(); i++) {
            if (inventoryItems.get(i).getInventoryId() == inventoryId) return inventoryItems.get(i);
        }
        return null;
    }

    public ArrayList<InventoryItem> searchByName(String name) {
        ArrayList<InventoryItem> results = new ArrayList<InventoryItem>();
        for (int i = 0; i < inventoryItems.size(); i++) {
            if (inventoryItems.get(i).getName().toLowerCase().contains(name.toLowerCase()))
                results.add(inventoryItems.get(i));
        }
        return results;
    }

    public ArrayList<InventoryItem> searchBySupplier(String supplier) {
        ArrayList<InventoryItem> results = new ArrayList<InventoryItem>();
        for (int i = 0; i < inventoryItems.size(); i++) {
            if (inventoryItems.get(i).getSupplier().equalsIgnoreCase(supplier))
                results.add(inventoryItems.get(i));
        }
        return results;
    }

    public int getTotalInventoryItems() { return inventoryItems.size(); }
}

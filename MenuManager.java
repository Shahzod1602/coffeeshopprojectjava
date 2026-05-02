import java.util.ArrayList;

public class MenuManager {

    // Dynamic Array - main menu storage, grows automatically as items are added
    private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

    // Static Array - fixed 5-slot chalkboard for today's daily specials
    private SpecialsBoard specialsBoard = new SpecialsBoard();

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public boolean updateMenuItem(int itemId, String name, String category, double price) {
        MenuItem item = searchById(itemId);
        if (item != null) {
            item.setName(name);
            item.setCategory(category);
            item.setPrice(price);
            return true;
        }
        return false;
    }

    public boolean removeMenuItem(int itemId) {
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getItemId() == itemId) {
                menuItems.remove(i);
                return true;
            }
        }
        return false;
    }

    public void showAllMenuItems() {
        System.out.println("\n--- All Menu Items ---");
        if (menuItems.isEmpty()) {
            System.out.println("No menu items found.");
            return;
        }
        for (int i = 0; i < menuItems.size(); i++) {
            menuItems.get(i).displayInfo();
            System.out.println("------------------------------");
        }
    }

    public MenuItem searchById(int itemId) {
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getItemId() == itemId) return menuItems.get(i);
        }
        return null;
    }

    public ArrayList<MenuItem> searchByName(String name) {
        ArrayList<MenuItem> results = new ArrayList<MenuItem>();
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getName().toLowerCase().contains(name.toLowerCase()))
                results.add(menuItems.get(i));
        }
        return results;
    }

    public ArrayList<MenuItem> searchByCategory(String category) {
        ArrayList<MenuItem> results = new ArrayList<MenuItem>();
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getCategory().equalsIgnoreCase(category))
                results.add(menuItems.get(i));
        }
        return results;
    }

    public ArrayList<MenuItem> searchByPrice(double price) {
        ArrayList<MenuItem> results = new ArrayList<MenuItem>();
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getPrice() == price)
                results.add(menuItems.get(i));
        }
        return results;
    }

    // --- Daily Specials Board (Static Array) operations ---
    public void showDailySpecials() { specialsBoard.showAllSpecials(); }
    public boolean addDailySpecial(String name) { return specialsBoard.addSpecial(name); }
    public boolean removeDailySpecial(int slot) { return specialsBoard.removeSpecial(slot); }
    public boolean updateDailySpecial(int slot, String name) { return specialsBoard.updateSpecial(slot, name); }
    public boolean isSpecialsBoardFull() { return specialsBoard.isFull(); }
    public void clearDailySpecials() { specialsBoard.clearBoard(); }

    public int getTotalMenuItems() { return menuItems.size(); }
}

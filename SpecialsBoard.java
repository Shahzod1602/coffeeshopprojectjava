// SpecialsBoard.java
// Static Array: a fixed 5-slot chalkboard for today's daily specials.
// The physical chalkboard has exactly 5 slots - the size never grows or shrinks.
// This maps directly to a fixed-size array declared once at creation.
// Unlike ArrayList (dynamic), this array cannot grow beyond 5 entries.
public class SpecialsBoard {

    private String[] specials;
    private int count;
    private static final int MAX_SLOTS = 5;

    public SpecialsBoard() {
        specials = new String[MAX_SLOTS];
        count = 0;
    }

    // addSpecial: write a new special into the first empty slot
    public boolean addSpecial(String specialName) {
        if (count == MAX_SLOTS) {
            System.out.println("Board is full! All " + MAX_SLOTS + " slots are taken.");
            return false;
        }
        for (int i = 0; i < MAX_SLOTS; i++) {
            if (specials[i] == null) {
                specials[i] = specialName;
                count++;
                System.out.println("Added to slot " + (i + 1) + ": " + specialName);
                return true;
            }
        }
        return false;
    }

    // removeSpecial: erase the special from a given slot number (1-5)
    public boolean removeSpecial(int slot) {
        if (slot < 1 || slot > MAX_SLOTS) {
            System.out.println("Invalid slot. Use 1-" + MAX_SLOTS + ".");
            return false;
        }
        if (specials[slot - 1] == null) {
            System.out.println("Slot " + slot + " is already empty.");
            return false;
        }
        System.out.println("Removed from slot " + slot + ": " + specials[slot - 1]);
        specials[slot - 1] = null;
        count--;
        return true;
    }

    // updateSpecial: rewrite a specific slot with a new name
    public boolean updateSpecial(int slot, String newName) {
        if (slot < 1 || slot > MAX_SLOTS) {
            System.out.println("Invalid slot. Use 1-" + MAX_SLOTS + ".");
            return false;
        }
        if (specials[slot - 1] == null && newName != null) {
            count++;
        }
        specials[slot - 1] = newName;
        System.out.println("Slot " + slot + " updated to: " + newName);
        return true;
    }

    // showAllSpecials: display the full board with all 5 slots
    public void showAllSpecials() {
        System.out.println("\n--- Today's Daily Specials ---");
        for (int i = 0; i < MAX_SLOTS; i++) {
            String entry = specials[i] != null ? specials[i] : "(empty)";
            System.out.println("  Slot " + (i + 1) + ": " + entry);
        }
        System.out.println("Used: " + count + " / " + MAX_SLOTS + " slots");
    }

    // isFull: check if all 5 slots are occupied
    public boolean isFull() {
        return count == MAX_SLOTS;
    }

    public int getCount() { return count; }
    public int getMaxSlots() { return MAX_SLOTS; }

    // clearBoard: erase everything from the board
    public void clearBoard() {
        for (int i = 0; i < MAX_SLOTS; i++) {
            specials[i] = null;
        }
        count = 0;
        System.out.println("Specials board cleared.");
    }
}

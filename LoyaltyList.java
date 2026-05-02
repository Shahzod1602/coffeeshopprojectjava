import java.util.LinkedList;

// LoyaltyList.java
// WHY LINKED LIST?
// The coffee shop's loyalty program has no fixed membership cap — customers
// enroll and cancel at any time, so the list must grow and shrink dynamically.
// VIP customers must be placed at the FRONT of the list so they receive
// priority rewards first; regular customers join at the BACK.
// LinkedList supports O(1) insertion at both ends, while an ArrayList would
// require O(n) element shifting every time a VIP is added to the front.
// Membership checks and cancellations search by name, never by index, so
// node-based traversal is a perfect fit and no random access is wasted.
// Five functions: addMember, addVIPMember, removeMember, isMember,
//                 getFirstMember, displayMembers
public class LoyaltyList {

    private LinkedList<String> members;

    public LoyaltyList() {
        members = new LinkedList<String>();
    }

    // addMember: regular customer joins at the back of the loyalty list
    public void addMember(String name) {
        members.addLast(name);
        System.out.println(name + " enrolled in the loyalty program. Total members: " + members.size());
    }

    // addVIPMember: VIP customer is placed at the front for priority rewards
    public void addVIPMember(String name) {
        members.addFirst(name);
        System.out.println(name + " enrolled as a VIP member (moved to front). Total members: " + members.size());
    }

    // removeMember: customer cancels their loyalty membership
    public boolean removeMember(String name) {
        boolean removed = members.remove(name);
        if (removed) {
            System.out.println(name + " removed from loyalty program.");
        } else {
            System.out.println(name + " was not found in the loyalty program.");
        }
        return removed;
    }

    // isMember: check if a customer is currently enrolled
    public boolean isMember(String name) {
        boolean found = members.contains(name);
        System.out.println(name + (found ? " IS" : " is NOT") + " a loyalty member.");
        return found;
    }

    // getFirstMember: see the highest-priority member at the front of the list
    public String getFirstMember() {
        if (members.isEmpty()) {
            System.out.println("No loyalty members enrolled.");
            return null;
        }
        String first = members.peekFirst();
        System.out.println("Top loyalty member: " + first);
        return first;
    }

    // displayMembers: show all enrolled members in their current priority order
    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("No loyalty members enrolled.");
            return;
        }
        System.out.println("\n--- Loyalty Program Members ---");
        int pos = 1;
        for (String member : members) {
            System.out.println("  " + pos + ". " + member);
            pos++;
        }
        System.out.println("Total members: " + members.size());
    }

    public boolean isEmpty() { return members.isEmpty(); }
    public int size() { return members.size(); }
}

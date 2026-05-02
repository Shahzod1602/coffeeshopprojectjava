# Coffee Shop Management System – Project Report

---

## 1. Project Overview

This is a Java console application that simulates the management of a coffee shop.  
It is divided into five functional areas, each with its own set of operations:

| Module | What it does |
|--------|-------------|
| Order Management | Create, cancel, complete, and search orders |
| Menu Management | Add, update, remove, and search menu items + daily specials |
| Inventory Management | Track stock quantities and suppliers |
| Employee Management | Manage staff records |
| Reservation Management | Handle table bookings and the walk-in waiting list |

---

## 2. File Structure

| File | Role |
|------|------|
| `Main.java` | Entry point, main menu loop |
| `DataSeeder.java` | Loads sample data at startup |
| `Person.java` | Abstract base class for people |
| `Employee.java` | Extends Person, implements Display |
| `Customer.java` | Extends Person, implements Display |
| `Item.java` | Abstract base class for items |
| `MenuItem.java` | Extends Item, implements Display |
| `InventoryItem.java` | Extends Item, implements Display |
| `Order.java` | Implements Display |
| `Reservation.java` | Implements Display |
| `Display.java` | Interface: displayInfo() |
| `Search.java` | Interface: searchByKeyword() |
| `OrderManager.java` | Manages orders + Stack + Deque |
| `MenuManager.java` | Manages menu items + SpecialsBoard |
| `InventoryManager.java` | Manages inventory items |
| `EmployeeManager.java` | Manages employees |
| `ReservationManager.java` | Manages reservations + WaitingQueue |
| `OrderConsole.java` | UI for order operations |
| `MenuConsole.java` | UI for menu + daily specials |
| `InventoryConsole.java` | UI for inventory operations |
| `EmployeeConsole.java` | UI for employee operations |
| `ReservationConsole.java` | UI for reservations + waiting list |
| `SystemSummaryPrinter.java` | Prints a total count summary |
| `OrderStack.java` | Stack implementation (recent orders) |
| `WaitingQueue.java` | Queue implementation (waiting customers) |
| `PrepDeque.java` | Deque implementation (barista prep queue) |
| `SpecialsBoard.java` | Static array (daily specials chalkboard) |

---

## 3. OOP Implementation

---

### 3.1 Abstract Classes

There are two abstract classes in the project.

---

**Abstract Class 1: `Person`**

```java
public abstract class Person {
    private int id;
    private String name;

    public Person(int id, String name) { ... }
    public abstract String getDetails();
}
```

| Variable | Type | Description |
|----------|------|-------------|
| `id` | `int` | unique person ID |
| `name` | `String` | person's name |

`getDetails()` is abstract — each subclass returns its own formatted description.
Subclasses: `Employee`, `Customer`

---

**Abstract Class 2: `Item`**

```java
public abstract class Item {
    private int id;
    private String name;

    public Item(int id, String name) { ... }
    public abstract void displayInfo();
}
```

| Variable | Type | Description |
|----------|------|-------------|
| `id` | `int` | unique item ID |
| `name` | `String` | item name |

`displayInfo()` is abstract — subclasses decide how to print themselves.
Subclasses: `MenuItem`, `InventoryItem`

---

### 3.2 Interfaces

There are two interfaces in the project.

---

**Interface 1: `Display`**

```java
public interface Display {
    void displayInfo();
}
```

Classes that implement `Display`:
`Employee`, `Customer`, `MenuItem`, `InventoryItem`, `Order`, `Reservation`

Each class prints its own fields when `displayInfo()` is called.

---

**Interface 2: `Search`**

```java
public interface Search {
    void searchByKeyword(String keyword);
}
```

Classes that implement `Search`:
`OrderManager`, `MenuManager`, `InventoryManager`, `EmployeeManager`, `ReservationManager`

Each manager searches its own list by checking multiple fields (name, date, ID, status, etc.) against the keyword.

---

### 3.3 Inheritance

There are two inheritance hierarchies in the project.

---

**Inheritance Example 1: `Employee extends Person`**

```java
public class Employee extends Person implements Display {
    private int employeeId;
    private String role;
    private String shift;
    ...
}
```

`Employee` inherits `id` and `name` from `Person`.
It adds its own fields and provides its own `getDetails()` and `displayInfo()`.

| Variable | Type | Description |
|----------|------|-------------|
| `employeeId` | `int` | employee number |
| `role` | `String` | job title (Cashier, Barista, Manager) |
| `shift` | `String` | work shift (Morning, Evening) |

---

**Inheritance Example 2: `Customer extends Person`**

```java
public class Customer extends Person implements Display {
    private String membershipType;
    ...
}
```

`Customer` inherits `id` and `name` from `Person`.
It adds `membershipType` and its own versions of `getDetails()` and `displayInfo()`.

| Variable | Type | Description |
|----------|------|-------------|
| `membershipType` | `String` | customer tier (Regular, VIP) |

*(Additionally: `MenuItem extends Item` and `InventoryItem extends Item` are two more inheritance examples.)*

---

### 3.4 Method Overriding

Three clear examples of `@Override`:

**Override 1 – `getDetails()` in `Employee`**
```java
@Override
public String getDetails() {
    return "Employee ID: " + employeeId + ", Name: " + getName() + ", Role: " + role + ", Shift: " + shift;
}
```

**Override 2 – `getDetails()` in `Customer`**
```java
@Override
public String getDetails() {
    return "Customer ID: " + getId() + ", Name: " + getName() + ", Membership: " + membershipType;
}
```

Same method name — completely different output for each class.

**Override 3 – `displayInfo()` in `MenuItem`**
```java
@Override
public void displayInfo() {
    System.out.println("Item ID: " + itemId);
    System.out.println("Name: " + getName());
    System.out.println("Category: " + category);
    System.out.printf("Price: %.2f%n", price);
}
```

`InventoryItem`, `Order`, `Reservation`, `Employee`, and `Customer` each override `displayInfo()` too — every class prints its own unique set of fields.

---

### 3.5 Polymorphism

Three examples from `DataSeeder.java`:

**Polymorphism Example 1 – Person reference holds Employee**
```java
Person p1 = new Employee(1, "Ali", "Cashier", "Morning");
// p1.getDetails() calls Employee's version at runtime
```

**Polymorphism Example 2 – Person reference holds Customer**
```java
Person p2 = new Customer(101, "Layla", "VIP");
// p2.getDetails() calls Customer's version at runtime
System.out.println("Welcome back, " + p2.getDetails());
```

**Polymorphism Example 3 – Item reference holds MenuItem**
```java
Item itemRef = new MenuItem(1, "Espresso", "Coffee", 2.50);
// itemRef.displayInfo() calls MenuItem's version at runtime
menuManager.addMenuItem((MenuItem) itemRef);
```

In all three cases the variable type is the parent, but the actual object is the child. Java calls the child's method at runtime (dynamic dispatch).

---

## 4. Data Structures

All five data structures are integrated directly into the real functionality of the system. They are not a separate demo section — they work behind the scenes as part of the actual features.

---

### 4.1 Static Array – `SpecialsBoard` (inside `MenuManager`)

**Accessed via:** Menu Management → Daily Specials Board

**Why static array?**
The physical chalkboard on the wall has exactly 5 slots. The size never changes. A static array matches this perfectly — it is declared once with a fixed size and cannot grow or shrink, unlike an ArrayList which can grow dynamically.

```java
private String[] specials;
private static final int MAX_SLOTS = 5;
```

| Variable | Type | Description |
|----------|------|-------------|
| `specials` | `String[5]` | fixed-size array holding up to 5 daily specials |
| `count` | `int` | how many slots are currently filled |
| `MAX_SLOTS` | `int` (constant) | always 5 — the board never grows |

**Operations:**

| Method | What it does |
|--------|-------------|
| `addSpecial(name)` | fills the first empty slot |
| `removeSpecial(slot)` | clears slot by number (1–5) |
| `updateSpecial(slot, name)` | rewrites a slot |
| `showAllSpecials()` | prints all 5 slots (filled or empty) |
| `isFull()` | returns true when all 5 slots are occupied |
| `clearBoard()` | empties the entire board |

---

### 4.2 Dynamic Array – `ArrayList` (inside every Manager)

**Accessed via:** All five management modules

**Why dynamic array?**
The number of orders, menu items, employees etc. is not known in advance and changes constantly. `ArrayList` grows automatically as items are added without needing to specify a size upfront.

```java
private ArrayList<Order> orders = new ArrayList<Order>();
private ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
// ...and similarly in every other Manager
```

| Variable | Type | Location |
|----------|------|----------|
| `orders` | `ArrayList<Order>` | `OrderManager` |
| `menuItems` | `ArrayList<MenuItem>` | `MenuManager` |
| `inventoryItems` | `ArrayList<InventoryItem>` | `InventoryManager` |
| `employees` | `ArrayList<Employee>` | `EmployeeManager` |
| `reservations` | `ArrayList<Reservation>` | `ReservationManager` |

Operations: `add()`, `remove(index)`, `get(index)`, `size()`, loop-based searching.

---

### 4.3 Stack – `OrderStack` (inside `OrderManager`)

**Accessed via:** Order Management → options 9 (View Recent Orders)

**Why Stack?**
The cashier always needs the most recently placed order on top. If a mistake is made entering an order, the last entry is the first thing to fix — that is LIFO (last-in, first-out).
A queue would put the oldest order at the front, which is the opposite of what staff need when reviewing what was just created.

```java
private Order[] stack;
private int top;       // index of the top element (-1 = empty)
private int maxSize;   // max capacity (10)
```

| Variable | Type | Description |
|----------|------|-------------|
| `stack` | `Order[]` | the fixed-size array used as stack storage |
| `top` | `int` | current top index (-1 means empty) |
| `maxSize` | `int` | maximum entries the stack can hold |

**Operations (5+):**

| Method | Description |
|--------|-------------|
| `push(order)` | places a new order on top |
| `pop()` | removes and returns the most recent order |
| `peek()` | returns the top order without removing it |
| `size()` | returns how many orders are in the stack |
| `isEmpty()` | returns true if there are no orders |
| `displayAll()` | shows all orders from newest to oldest |
| `clear()` | resets the stack to empty |

**Integration:** Every call to `OrderManager.addOrder()` automatically pushes the new order onto the stack.

---

### 4.4 Queue – `WaitingQueue` (inside `ReservationManager`)

**Accessed via:** Reservation Management → options 7, 8, 9

**Why Queue?**
When all tables are full, walk-in customers wait in line. The person who arrived first must be seated first — that is FIFO (first-in, first-out).
A stack here would be unfair (last person in gets seated first). A deque is more than needed since we only ever insert at the back and remove from the front.

```java
private Queue<String> waitingLine;  // backed by LinkedList
```

| Variable | Type | Description |
|----------|------|-------------|
| `waitingLine` | `Queue<String>` (LinkedList) | the ordered line of waiting customer names |

**Operations (5+):**

| Method | Description |
|--------|-------------|
| `addCustomer(name)` | customer joins the back of the line |
| `serveNextCustomer()` | seats the person at the front (removes them) |
| `peekNextCustomer()` | shows who is next without removing them |
| `isInQueue(name)` | checks if a customer is already waiting |
| `displayQueue()` | shows all waiting customers in order |
| `removeCustomer(name)` | removes a customer who left before being seated |
| `clearQueue()` | empties the waiting list |

---

### 4.5 Deque – `PrepDeque` (inside `OrderManager`)

**Accessed via:** Order Management → options 10, 11, 12

**Why Deque?**
The barista's prep queue has two types of orders:
- Regular orders join at the **back** (normal FIFO fairness)
- Priority/VIP orders are inserted at the **front** (served urgently)
- Processing always removes from the **front**
- Cancellation of the last-added order removes from the **back**

A plain Queue can only insert at the back and cannot handle priority. A Stack only accesses one end. Only a Deque supports insertion and removal from **both ends**, which is exactly what this scenario requires.

```java
private Deque<Order> prepQueue;  // backed by ArrayDeque
```

| Variable | Type | Description |
|----------|------|-------------|
| `prepQueue` | `Deque<Order>` (ArrayDeque) | the prep queue with front and back access |

**Operations (5+):**

| Method | Description |
|--------|-------------|
| `addRegularOrder(order)` | adds to the back (normal customer order) |
| `addPriorityOrder(order)` | adds to the front (VIP/urgent order) |
| `processNextOrder()` | barista takes the next order from the front |
| `removeLastOrder()` | cancels the most recently added order (from back) |
| `displayAll()` | shows the full queue from front to back |
| `peekNext()` | shows the next order without processing it |
| `peekLast()` | shows the last order in the queue |

**Integration:** Every "Current" order created via `OrderManager.addOrder()` is automatically added to the back of the prep queue.

---

## 5. Variable Reference by Class

### `Order.java`
| Variable | Type | Description |
|----------|------|-------------|
| `orderId` | `int` | unique order number |
| `customerName` | `String` | who placed the order |
| `date` | `String` | date as YYYY-MM-DD |
| `status` | `String` | Current / Completed / Canceled |
| `itemName` | `String` | what was ordered |
| `totalPrice` | `double` | total cost |

### `Reservation.java`
| Variable | Type | Description |
|----------|------|-------------|
| `reservationId` | `int` | unique reservation number |
| `customerName` | `String` | who made the booking |
| `date` | `String` | date as YYYY-MM-DD |
| `tableNumber` | `int` | which table |
| `numPeople` | `int` | group size |
| `status` | `String` | Active / Cancelled |

### `Employee.java`
| Variable | Type | Description |
|----------|------|-------------|
| `employeeId` | `int` | staff number |
| `role` | `String` | job title |
| `shift` | `String` | Morning or Evening |
| *(from Person)* `id`, `name` | `int`, `String` | inherited |

### `Customer.java`
| Variable | Type | Description |
|----------|------|-------------|
| `membershipType` | `String` | Regular or VIP |
| *(from Person)* `id`, `name` | `int`, `String` | inherited |

### `MenuItem.java`
| Variable | Type | Description |
|----------|------|-------------|
| `itemId` | `int` | menu item number |
| `category` | `String` | Coffee, Pastry, etc. |
| `price` | `double` | item price |
| *(from Item)* `id`, `name` | `int`, `String` | inherited |

### `InventoryItem.java`
| Variable | Type | Description |
|----------|------|-------------|
| `inventoryId` | `int` | stock item number |
| `quantity` | `int` | units in stock |
| `supplier` | `String` | who supplies this item |
| *(from Item)* `id`, `name` | `int`, `String` | inherited |

### `OrderStack.java`
| Variable | Type | Description |
|----------|------|-------------|
| `stack` | `Order[]` | array used as stack storage |
| `top` | `int` | index of top element (-1 = empty) |
| `maxSize` | `int` | capacity limit |

### `WaitingQueue.java`
| Variable | Type | Description |
|----------|------|-------------|
| `waitingLine` | `Queue<String>` | linked list of customer names |

### `PrepDeque.java`
| Variable | Type | Description |
|----------|------|-------------|
| `prepQueue` | `Deque<Order>` | array deque of orders |

### `SpecialsBoard.java`
| Variable | Type | Description |
|----------|------|-------------|
| `specials` | `String[5]` | fixed array of 5 special names |
| `count` | `int` | filled slot counter |
| `MAX_SLOTS` | `int` (constant) | always 5 |

---

## 6. How the Program Works

### Startup

`Main.main()` creates one instance of each manager and console, then calls `DataSeeder.seed()`.
During seeding: 3 employees, 4 menu items, 3 inventory items, 3 orders, and 3 reservations are loaded.
The 2 "Current" seeded orders are automatically pushed to the Stack and added to the PrepDeque.

### Main Menu

```
1. Order Management
2. Menu Management
3. Inventory Management
4. Employee Management
5. Reservation Management
6. System Summary
0. Exit
```

Each option delegates to its Console class, which runs its own sub-menu loop until the user types 0.

### Order Management Flow

- Creating an order → added to ArrayList, pushed to Stack, added to back of PrepDeque
- View Recent Orders (9) → Stack.displayAll() — newest first
- View Prep Queue (10) → PrepDeque.displayAll() — front to back
- Process Next Prep Order (11) → PrepDeque.processNextOrder() — barista takes the front order
- Mark as Priority (12) → PrepDeque.addPriorityOrder() — order jumps to front of queue

### Reservation Management Flow

- Create Reservation → added to ArrayList
- Add to Waiting List (8) → Queue.addCustomer() — joins back of the line
- View Waiting List (7) → Queue.displayQueue() — shows everyone in arrival order
- Seat Next Customer (9) → Queue.serveNextCustomer() — front of the line is seated

### Menu Management Flow

- Daily Specials Board (8) → opens sub-menu for SpecialsBoard (static array)
- Fixed 5 slots — cannot add more than 5 specials at once
- Slots accessed by number 1–5, empty slots display as (empty)

### System Summary

Prints total counts from all managers: orders by status, menu items, inventory items, employees, reservations.

---

## 7. Requirements Checklist

| Requirement | Where implemented |
|-------------|-------------------|
| Abstract class 1 | `Person.java` – abstract `getDetails()` |
| Abstract class 2 | `Item.java` – abstract `displayInfo()` |
| Interface 1 | `Display.java` – implemented by Employee, Customer, MenuItem, InventoryItem, Order, Reservation |
| Interface 2 | `Search.java` – implemented by all five Manager classes |
| Inheritance 1 | `Employee extends Person` |
| Inheritance 2 | `Customer extends Person` (+ `MenuItem extends Item`, `InventoryItem extends Item`) |
| Method override 1 | `getDetails()` in `Employee` |
| Method override 2 | `getDetails()` in `Customer` |
| Method override 3 | `displayInfo()` in `MenuItem` (and all other Display classes) |
| Polymorphism 1 | `Person p1 = new Employee(...)` in DataSeeder |
| Polymorphism 2 | `Person p2 = new Customer(...)` in DataSeeder |
| Polymorphism 3 | `Item itemRef = new MenuItem(...)` in DataSeeder |
| Static array | `SpecialsBoard` – `String[5]` inside `MenuManager` |
| Dynamic array | `ArrayList<T>` inside every Manager class |
| Stack (LIFO) | `OrderStack` inside `OrderManager` – recent orders, newest on top |
| Queue (FIFO) | `WaitingQueue` inside `ReservationManager` – walk-in waiting list |
| Deque (both ends) | `PrepDeque` inside `OrderManager` – barista prep queue |
| Conditions | `if/else` throughout all managers and consoles |
| Loops | `for` in all search methods, `while` in all console menu loops |
| Methods | getters, setters, search, display, add/remove in every class |
| Classes | 27 classes total |

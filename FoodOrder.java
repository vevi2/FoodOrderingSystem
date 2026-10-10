import java.util.*;

class FoodItem {
    String name;
    double price;

    FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class OrderItem {
    FoodItem item;
    int quantity;

    OrderItem(FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    double getTotal() {
        return item.price * quantity;
    }
}

public class FoodOrder {
    static List<FoodItem> menu = new ArrayList<>();
    static List<OrderItem> order = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize menu
        menu.add(new FoodItem("Burger", 120));
        menu.add(new FoodItem("Pizza", 250));
        menu.add(new FoodItem("Sandwich", 80));
        menu.add(new FoodItem("Fries", 60));
        menu.add(new FoodItem("Coke", 40));

        int choice;
        do {
            System.out.println("\n--- Food Ordering System ---");
            System.out.println("1. Display Menu");
            System.out.println("2. Search Food Item");
            System.out.println("3. Add Item to Order");
            System.out.println("4. Remove Item from Order");
            System.out.println("5. Display Complete Order");
            System.out.println("6. Calculate Total Amount");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: displayMenu(); break;
                case 2: searchItem(); break;
                case 3: addItem(); break;
                case 4: removeItem(); break;
                case 5: displayOrder(); break;
                case 6: calculateTotal(); break;
                case 0: System.out.println("Thank you! Visit again."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    static void displayMenu() {
        System.out.println("\n--- Menu ---");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i+1) + ". " + menu.get(i).name + " - Rs." + menu.get(i).price);
        }
    }

    static void searchItem() {
        System.out.print("Enter food name to search: ");
        String name = sc.next();
        boolean found = false;
        for (FoodItem f : menu) {
            if (f.name.equalsIgnoreCase(name)) {
                System.out.println("Found: " + f.name + " - Rs." + f.price);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Item not found!");
    }

    static void addItem() {
        displayMenu();
        System.out.print("Enter item number to add: ");
        int num = sc.nextInt();
        if (num < 1 || num > menu.size()) {
            System.out.println("Invalid item number!");
            return;
        }
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();
        order.add(new OrderItem(menu.get(num-1), qty));
        System.out.println("Item added to order.");
    }

    static void removeItem() {
        displayOrder();
        System.out.print("Enter order item number to remove: ");
        int num = sc.nextInt();
        if (num < 1 || num > order.size()) {
            System.out.println("Invalid order item number!");
            return;
        }
        order.remove(num-1);
        System.out.println("Item removed from order.");
    }

    static void displayOrder() {
        if (order.isEmpty()) {
            System.out.println("Order is empty!");
            return;
        }
        System.out.println("\n--- Current Order ---");
        for (int i = 0; i < order.size(); i++) {
            OrderItem oi = order.get(i);
            System.out.println((i+1) + ". " + oi.item.name + " x" + oi.quantity + " = Rs." + oi.getTotal());
        }
    }

    static void calculateTotal() {
        double total = 0;
        for (OrderItem oi : order) {
            total += oi.getTotal();
        }
        System.out.println("Total Amount = Rs." + total);
    }
}

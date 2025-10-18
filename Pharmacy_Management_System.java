import java.util.Scanner;

public class Pharmacy_Management_System {
    private static String[] medIDs = new String[100];     
    private static String[] medNames = new String[100];     
    private static int[] medStock = new int[100];           
    private static double[] medPrice = new double[100];     
    private static String[] medExpiry = new String[100];    
    private static int medCount = 0;
    private static final int LOW_STOCK = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println();
            System.out.println("Pharmacy Management System");
            System.out.println("1. Register Medicine");
            System.out.println("2. Handle Sale");
            System.out.println("3. Show Low-Stock Alerts");
            System.out.println("0. Exit");

            System.out.println();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    Medicine.registerMedicine();
                    break;
                case 2:
                    SalesTransaction.handleSale();
                    break;
                case 3:
                    Medicine.checkLowStock();
                    break;
                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    static class Medicine {
        public static void registerMedicine() {
            Scanner scanner = new Scanner(System.in);
            String id;

            while (true) {
                System.out.print("Enter Medicine ID: ");
                id = scanner.nextLine();
                boolean exists = false;
                for (int i = 0; i < medCount; i++) {
                    if (medIDs[i].equalsIgnoreCase(id)) {
                        exists = true;
                        break;
                    }
                }
                if (exists) {
                    System.out.println("Medicine ID already exists! Please enter a different ID.");
                    System.out.println();
                } else {
                    break; 
                }
            }

            System.out.print("Enter Medicine Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Stock Quantity: ");
            int stock = scanner.nextInt();
            System.out.print("Enter Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); 
            System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
            String expiry = scanner.nextLine();

            medIDs[medCount] = id;
            medNames[medCount] = name;
            medStock[medCount] = stock;
            medPrice[medCount] = price;
            medExpiry[medCount] = expiry;
            medCount++;

            System.out.println("Medicine registered successfully!");
        }

        public static void checkLowStock() {
            System.out.println("\nLow-Stock Alerts:");
            boolean hasLowStock = false;
            for (int i = 0; i < medCount; i++) {
                if (medStock[i] <= LOW_STOCK) {
                    System.out.println("Medicine: " + medNames[i] + " | Stock: " + medStock[i]);
                    hasLowStock = true;
                }
            }
            if (!hasLowStock) {
                System.out.println("No low-stock medicines.");
            }
        }
    }

    static class SalesTransaction {
        public static void handleSale() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Medicine ID to sell: ");
            String id = scanner.nextLine();

            boolean found = false; 

            for (int i = 0; i < medCount; i++) {
                if (medIDs[i].equalsIgnoreCase(id)) {
                    found = true;

                    System.out.print("Enter quantity to sell: ");
                    int qty = scanner.nextInt();
                    scanner.nextLine(); 

                    if (qty > medStock[i]) {
                        System.out.println("Insufficient stock!");
                    } else {
                        medStock[i] -= qty;
                        double total = qty * medPrice[i];

                        System.out.println();
                        System.out.println("Sale successful!");
                        System.out.println("Medicine: " + medNames[i]);
                        System.out.println("Quantity Sold: " + qty);
                        System.out.println("Total Amount: " + total);
                    }

                    break; 
                }
            }

            if (!found) {
                System.out.println("Medicine not found!");
            }
        }
    }
}

// Features implemented:
// 1. Register Medicines with stock details - Medicine.registerMedicine()
// 2. Handle Sales Transactions - SalesTransaction.handleSale()
// 3. Generate Low-Stock Alerts - Medicine.checkLowStock()

import java.util.Scanner;

// Main class controls the Pharmacy Management System
public class Pharmacy_Management_System {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Association - Main class "knows" Medicine, Customer, and SalesTransaction. Can call their methods (Association is a "uses-a" relationship)
        Medicine medicine = new Medicine();
        Customer customer = new Customer();
        SalesTransaction transaction = new SalesTransaction();

        int choice;
        do {
            System.out.println();
            System.out.println("=== Pharmacy Management System ===");
            System.out.println("1. Register Medicine");
            System.out.println("2. Add Customer");
            System.out.println("3. Handle Sale");
            System.out.println("4. Show All Medicine / Low Stock");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    medicine.registerMedicine(); // Call Medicine method (Association)
                    break;
                case 2:
                    customer.addCustomer(); // Call Customer method (Association)
                    break;
                case 3:
                    transaction.handleSale(customer, medicine); // Call SalesTransaction method (Association)
                    break;
                case 4:
                    medicine.checkStock(); // Show all medicine and low stock
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);
    }
}


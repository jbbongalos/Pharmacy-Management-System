import java.util.Scanner;

// SalesTransaction connects Customer and Medicine
// Demonstrates Aggregation and Composition
public class SalesTransaction {

    private int transactionID;
    private Customer customer;          // Aggregation - SalesTransaction "has-a" Customer (Customer can exist independently)
    private Medicine[] medicineList;    // Composition - Medicines sold are part of this transaction
    private String date;
    private double totalAmount;

    private static int transactionCounter = 1;

    public void handleSale(Customer customerObj, Medicine medicineObj) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Customer ID: ");
        int customerID = scanner.nextInt();
        scanner.nextLine();

        // Aggregation - Find customer for this sale
        Customer cust = customerObj.findCustomerByID(customerID);
        if (cust == null) {
            System.out.println("Customer not found! Please add the customer first.");
            return;
        }

        System.out.print("Enter Medicine ID: ");
        int medicineID = scanner.nextInt();
        System.out.print("Enter Quantity: ");
        int qty = scanner.nextInt();

        // Composition -  Medicines sold are part of this transaction
        this.transactionID = transactionCounter++;
        this.customer = cust;

        boolean success = medicineObj.updateStock(medicineID, qty);
        if (!success) return;

        double total = medicineObj.getPrice(medicineID) * qty;
        this.totalAmount = total;

        System.out.println();
        System.out.println("--- Receipt ---");
        System.out.println("Transaction ID: " + this.transactionID);
        System.out.println("Customer Name: " + this.customer.getName());
        System.out.println("Medicine ID: " + medicineID);
        System.out.println("Quantity: " + qty);
        System.out.println("Total Amount: ₱" + total);
        System.out.println("Sale successful!");
        System.out.println();
    }
}


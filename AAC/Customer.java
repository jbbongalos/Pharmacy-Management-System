import java.util.Scanner;

// Customer class handles adding and viewing customers
// Aggregation - Customer can exist independently of a SalesTransaction
// I add Customer class to demonstrate clearly the Aggregation.
public class Customer {
    private int customerID;
    private String name;
    private String contactInfo;

    private static Customer[] customerList = new Customer[100];
    private static int customerCount = 0;

    public void addCustomer() {
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer(); // Aggregation - Customer exists independently

        System.out.print("Enter Customer ID: ");
        customer.customerID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        customer.name = scanner.nextLine();
        System.out.print("Enter Contact Info: ");
        customer.contactInfo = scanner.nextLine();

        customerList[customerCount++] = customer;
        System.out.println("Customer added successfully!");
    }

    public Customer findCustomerByID(int id) {
        for (int i = 0; i < customerCount; i++) {
            if (customerList[i].customerID == id) {
                return customerList[i];
            }
        }
        return null;
    }

    public void viewCustomerRecord() {
        System.out.println("\n=== Customer Records ===");
        for (int i = 0; i < customerCount; i++) {
            System.out.println("ID: " + customerList[i].customerID +
                    " | Name: " + customerList[i].name +
                    " | Contact: " + customerList[i].contactInfo);
        }
    }

    public String getName() {
        return this.name;
    }
}


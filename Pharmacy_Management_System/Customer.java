import java.util.ArrayList;
import java.util.Scanner;

class Customer extends Person {
    private static ArrayList<Customer> customerList = new ArrayList<>();
    
    // Default constructor for main class
    public Customer() {}
    
    // Constructor using super keyword
    public Customer(String id, String name) {
        super(id, name); // Call to parent constructor
    }
    
    // CRUD Operations
    public void manageCustomer() {
        Scanner scanner = new Scanner(System.in);
        int customerChoice;
        
        do {
            System.out.println();
            System.out.println("--- Manage Customers ---");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back");
            System.out.println();
            System.out.print("Enter choice: ");
            customerChoice = scanner.nextInt();
            scanner.nextLine();
            
            switch(customerChoice) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    viewAllCustomers();
                    break;
                case 3:
                    updateCustomer(scanner);
                    break;
                case 4:
                    deleteCustomer(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        } while(customerChoice != 5);
    }
    
    private void addCustomer(Scanner scanner) {
        System.out.println();
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        
        Customer newCustomer = new Customer(customerId, customerName);
        customerList.add(newCustomer);
        System.out.println();
        System.out.println("Customer added successfully!");
        viewAllCustomers();
    }
    
    public void viewAllCustomers() {
        System.out.println();
        System.out.println("--- Customer List ---");
        for(Customer customer : customerList) {
            System.out.println("ID: " + customer.getPersonId() + " | Name: " + customer.getPersonName());
        }
    }
    
    private void updateCustomer(Scanner scanner) {
        System.out.println();
        System.out.print("Enter Customer ID to update: ");
        String customerId = scanner.nextLine();
        
        for(Customer customer : customerList) {
            if(customer.getPersonId().equals(customerId)) {
                System.out.print("Enter new name: ");
                customer.setPersonName(scanner.nextLine());
                System.out.println("Customer updated successfully!");
                return;
            }
        }
        System.out.println("Customer not found!");
    }
    
    private void deleteCustomer(Scanner scanner) {
        System.out.println();
        System.out.print("Enter Customer ID to delete: ");
        String customerId = scanner.nextLine();
        
        for(int i = 0; i < customerList.size(); i++) {
            if(customerList.get(i).getPersonId().equals(customerId)) {
                customerList.remove(i);
                System.out.println("Customer deleted successfully!");
                return;
            }
        }
        System.out.println("Customer not found!");
    }
    
    @Override
    public void displayInformation() {
        System.out.println("Customer: " + personName);
    }
    
    public void showAll() {
        viewAllCustomers();
    }
    
    public static Customer findCustomerByName(String name) {
        for(Customer customer : customerList) {
            if(customer.getPersonName().equals(name)) {
                return customer;
            }
        }
        return null;
    }
}
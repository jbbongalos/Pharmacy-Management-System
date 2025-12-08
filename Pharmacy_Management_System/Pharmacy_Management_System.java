import java.util.Scanner;

public class Pharmacy_Management_System {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create all objects for different modules
        Medicine medicine = new Medicine();       // Encapsulation + CRUD
        Customer customer = new Customer();       // Inheritance + CRUD
        Supplier supplier = new Supplier();       // Inheritance + CRUD
        Employee employee = new Employee();       // Inheritance + CRUD
        Prescription prescription = new Prescription(); // Prescription management
        SalesTransaction sales = new Sales();     // Polymorphism
        Report report = new DailyReport();               // Abstraction

        int choice;
        do {
            // Display main menu
            System.out.println("\n====== PHARMACY MANAGEMENT SYSTEM ======");
            System.out.println("1. Manage Medicines");
            System.out.println("2. Manage Customers");
            System.out.println("3. Manage Suppliers");
            System.out.println("4. Manage Employees");
            System.out.println("5. Create Prescription");
            System.out.println("6. Handle Sale");
            System.out.println("7. Show All Records");
            System.out.println("8. Generate Report");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch(choice) {
                case 1:
                    medicine.manageMedicine();
                    break;
                case 2:
                    customer.manageCustomer();
                    break;
                case 3:
                    supplier.manageSupplier();
                    break;
                case 4:
                    employee.manageEmployee();
                    break;
                case 5:
                    prescription.createPrescription();
                    break;
                case 6:
                    sales.handleSale(medicine);
                    break;
                case 7:
                    System.out.println("\n===== ALL RECORDS =====");
                    medicine.showAllMedicines();
                    customer.showAll();
                    supplier.showAll();
                    employee.showAll();
                    prescription.showAll();
                    sales.showAll();
                    break;
                case 8:
                    report.generateReport();
                    if(report instanceof Reportable) {
                        ((Reportable) report).printReport();
                    }
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while(choice != 0);

        scanner.close();
    }
}
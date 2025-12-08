import java.util.ArrayList;
import java.util.Scanner;

class Supplier extends Person {
    private String companyName;
    private static ArrayList<Supplier> supplierList = new ArrayList<>();
    
    // Default constructor for main class
    public Supplier() {}
    
    // Constructor using super keyword
    public Supplier(String id, String name, String company) {
        super(id, name); // Call to parent constructor
        this.companyName = company;
    }
    
    // Getter and setter
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    // CRUD Operations
    public void manageSupplier() {
        Scanner scanner = new Scanner(System.in);
        int supplierChoice;
        
        do {
            System.out.println();
            System.out.println("--- Manage Suppliers ---");
            System.out.println("1. Add Supplier");
            System.out.println("2. View Suppliers");
            System.out.println("3. Update Supplier");
            System.out.println("4. Delete Supplier");
            System.out.println("5. Back");
            System.out.println();
            System.out.print("Enter choice: ");
            supplierChoice = scanner.nextInt();
            scanner.nextLine();
            
            switch(supplierChoice) {
                case 1:
                    addSupplier(scanner);
                    break;
                case 2:
                    viewAllSuppliers();
                    break;
                case 3:
                    updateSupplier(scanner);
                    break;
                case 4:
                    deleteSupplier(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        } while(supplierChoice != 5);
    }
    
    private void addSupplier(Scanner scanner) {
        System.out.println();
        System.out.print("Enter Supplier ID: ");
        String supplierId = scanner.nextLine();
        System.out.print("Enter Supplier Name: ");
        String supplierName = scanner.nextLine();
        System.out.print("Enter Company Name: ");
        String company = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        
        Supplier newSupplier = new Supplier(supplierId, supplierName, company);
        newSupplier.setContactNumber(contact);
        supplierList.add(newSupplier);
        System.out.println();
        System.out.println("Supplier added successfully!");
        viewAllSuppliers();
    }
    
    public void viewAllSuppliers() {
        System.out.println();
        System.out.println("--- Supplier List ---");
        for(Supplier supplier : supplierList) {
            System.out.println("ID: " + supplier.getPersonId() + 
                             " | Name: " + supplier.getPersonName() + 
                             " | Company: " + supplier.getCompanyName() + 
                             " | Contact Number: " + supplier.getContactNumber());
        }
    }
    
    private void updateSupplier(Scanner scanner) {
        System.out.println();
        System.out.print("Enter Supplier ID to update: ");
        String supplierId = scanner.nextLine();
        
        for(Supplier supplier : supplierList) {
            if(supplier.getPersonId().equals(supplierId)) {
                System.out.print("Enter new name: ");
                supplier.setPersonName(scanner.nextLine());
                System.out.print("Enter new company: ");
                supplier.setCompanyName(scanner.nextLine());
                System.out.print("Enter new contact: ");
                supplier.setContactNumber(scanner.nextLine());
                System.out.println("Supplier updated successfully!");
                return;
            }
        }
        System.out.println("Supplier not found!");
    }
    
    private void deleteSupplier(Scanner scanner) {
        System.out.println();
        System.out.print("Enter Supplier ID to delete: ");
        String supplierId = scanner.nextLine();
        
        for(int i = 0; i < supplierList.size(); i++) {
            if(supplierList.get(i).getPersonId().equals(supplierId)) {
                supplierList.remove(i);
                System.out.println("Supplier deleted successfully!");
                return;
            }
        }
        System.out.println("Supplier not found!");
    }
    
    @Override
    public void displayInformation() {
        System.out.println("Supplier: " + personName + ", Company: " + companyName);
    }
    
    public void showAll() {
        viewAllSuppliers();
    }
}
import java.util.ArrayList;
import java.util.Scanner;

// =============================================
// CLASS FOR ENCAPSULATION (Medicine)
// ================================================
class Medicine {
    // Private fields for encapsulation
    private String medicineId;
    private String medicineName;
    private double medicinePrice;
    private int medicineStock;
    private String expiryDate;
    
    private static ArrayList<Medicine> medicineList = new ArrayList<>();
    private static ArrayList<String> salesRecords = new ArrayList<>();
    private static int totalSalesAmount = 0;
    private static int totalMedicinesSold = 0;
    private static String lastSoldMedicineName = "";
    
    // Default constructor for main clas656tygbvs
    public Medicine() {}
    
    // Constructor as setter for encapsulation
    public Medicine(String id, String name, double price, int stock, String expiry) {
        this.medicineId = id;
        this.medicineName = name;
        this.medicinePrice = price;
        this.medicineStock = stock;
        this.expiryDate = expiry;
    }
    
    // Getter methods for encapsulation
    public String getMedicineId() {
        return medicineId;
    }
    public String getMedicineName() {
        return medicineName;
    }
    public double getMedicinePrice() {
        return medicinePrice;
    }
    public int getMedicineStock() {
        return medicineStock;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
    
    // Setter methods for encapsulation
    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }
    
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
    
    public void setMedicinePrice(double medicinePrice) {
        this.medicinePrice = medicinePrice;
    }
    
    public void setMedicineStock(int medicineStock) {
        this.medicineStock = medicineStock;
    }
    
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    // CRUD Operations
    public void manageMedicine() {
        Scanner scanner = new Scanner(System.in);
        int medicineChoice;
        
        do {
            System.out.println();
            System.out.println("--- Manage Medicines ---");
            System.out.println("1. Add Medicine");
            System.out.println("2. View Medicines");
            System.out.println("3. Update Medicine");
            System.out.println("4. Delete Medicine");
            System.out.println("5. Back");
            System.out.println();
            System.out.print("Enter choice: ");
            medicineChoice = scanner.nextInt();
            scanner.nextLine();
            
            switch(medicineChoice) {
                case 1:
                    addMedicine(scanner);
                    break;
                case 2:
                    viewAllMedicines();
                    break;
                case 3:
                    updateMedicine(scanner);
                    break;
                case 4:
                    deleteMedicine(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        } while(medicineChoice != 5);
    }
    
    private void addMedicine(Scanner scanner) {
        System.out.println();
        System.out.print("Enter Medicine ID: ");
        String medicineId = scanner.nextLine();
        System.out.print("Enter Medicine Name: ");
        String medicineName = scanner.nextLine();
        System.out.print("Enter Medicine Price: ");
        double medicinePrice = scanner.nextDouble();
        System.out.print("Enter Medicine Stock: ");
        int medicineStock = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
        String expiryDate = scanner.nextLine();
        
        Medicine newMedicine = new Medicine(medicineId, medicineName, medicinePrice, medicineStock, expiryDate);
        medicineList.add(newMedicine);
        System.out.println("Medicine added successfully!");
        
        if(medicineStock <= 5) {
            System.out.println();
            System.out.println("--- Alert: Low stock! ---");
        }
        
        viewAllMedicines();
    }
    
    public void viewAllMedicines() {
        System.out.println();
        System.out.println("--- Medicine List ---");
        for(Medicine medicine : medicineList) {
            System.out.printf("ID: %s | Name: %s | Price: %.2f  | Stock: %d  | Expiry Date: %s \n",
                            medicine.getMedicineId(), medicine.getMedicineName(),
                            medicine.getMedicinePrice(), medicine.getMedicineStock(),
                            medicine.getExpiryDate());
        }
    }
    
    private void updateMedicine(Scanner scanner) {
        System.out.println();
        System.out.print("Enter Medicine ID to update: ");
        String medicineId = scanner.nextLine();
        
        for(Medicine medicine : medicineList) {
            if(medicine.getMedicineId().equals(medicineId)) {
                System.out.print("Enter new name: ");
                medicine.setMedicineName(scanner.nextLine());
                System.out.print("Enter new price: ");
                medicine.setMedicinePrice(scanner.nextDouble());
                System.out.print("Enter new stock: ");
                medicine.setMedicineStock(scanner.nextInt());
                scanner.nextLine();
                System.out.print("Enter new expiry date: ");
                medicine.setExpiryDate(scanner.nextLine());
                System.out.println("Medicine updated successfully!");
                return;
            }
        }
        System.out.println("Medicine not found!");
    }
    
    private void deleteMedicine(Scanner scanner) {
        System.out.println();
        System.out.print("Enter Medicine ID to delete: ");
        String medicineId = scanner.nextLine();
        
        for(int i = 0; i < medicineList.size(); i++) {
            if(medicineList.get(i).getMedicineId().equals(medicineId)) {
                medicineList.remove(i);
                System.out.println("Medicine deleted successfully!");
                return;
            }
        }
        System.out.println("Medicine not found!");
    }
    
    public void showAllMedicines() {
        viewAllMedicines();
    }
    
    // Static methods for other classes
    public static Medicine findMedicineByName(String name) {
        for(Medicine medicine : medicineList) {
            if(medicine.getMedicineName().equals(name)) {
                return medicine;
            }
        }
        return null;
    }
    
    public static boolean updateStock(String medicineName, int quantity) {
        for(Medicine medicine : medicineList) {
            if(medicine.getMedicineName().equals(medicineName)) {
                if(medicine.getMedicineStock() >= quantity) {
                    medicine.setMedicineStock(medicine.getMedicineStock() - quantity);
                    totalSalesAmount += medicine.getMedicinePrice() * quantity;
                    totalMedicinesSold += quantity;
                    lastSoldMedicineName = medicineName;
                    
                    if(medicine.getMedicineStock() <= 5) {
                        System.out.println("--- Alert: Low stock! ---");
                        System.out.println("Remaining Stock " + medicineName + " | " + medicine.getMedicineStock());
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void addSalesRecord(String customer, String medicine, int quantity, double total) {
        String record = customer + "|" + medicine + "|" + quantity + "|" + String.format("%.2f", total);
        salesRecords.add(record);
    }
    
    public static ArrayList<String> getSalesRecords() {
        return salesRecords;
    }
    
    public static int getTotalSales() {
        return totalSalesAmount;
    }
    
    public static int getTotalMedicinesSold() {
        return totalMedicinesSold;
    }
    
    public static String getLastSoldMedicineInfo() {
        if(!lastSoldMedicineName.isEmpty() && totalMedicinesSold > 0) {
            return lastSoldMedicineName + "   |   " + totalMedicinesSold;
        }
        return "";
    }
    
    public static String getLowStockInfo() {
        StringBuilder info = new StringBuilder();
        for(Medicine medicine : medicineList) {
            if(medicine.getMedicineStock() <= 5 && medicine.getMedicineStock() > 0) {
                info.append("Remaining: ").append(medicine.getMedicineName())
                    .append("  | ").append(medicine.getMedicineStock()).append("\n");
            }
        }
        return info.toString();
    }
}


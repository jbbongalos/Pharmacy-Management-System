import java.util.ArrayList;
import java.util.Scanner;

class SalesTransaction {
    // METHOD OVERLOADING - Compile-time polymorphism
    
    // First version - no parameters
    public void handleSale() {
        System.out.println("Handling general sale transaction...");
    }
    
    // Second version - with Medicine parameter
    public void handleSale(Medicine medicine) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println();
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter Medicine Name: ");
        String medicineName = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        
        Medicine foundMedicine = Medicine.findMedicineByName(medicineName);
        if(foundMedicine != null && foundMedicine.getMedicineStock() >= quantity) {
            double totalPrice = foundMedicine.getMedicinePrice() * quantity;
            if(Medicine.updateStock(medicineName, quantity)) {
                Medicine.addSalesRecord(customerName, medicineName, quantity, totalPrice);
                System.out.printf("Sale successful! Total Price: %.2f\n", totalPrice);
            }
        } else {
            System.out.println("Sale failed! Not enough stock or medicine not found.");
        }
    }
    
    // Third version - with all parameters
    public void handleSale(String customerName, String medicineName, int quantity) {
        Medicine foundMedicine = Medicine.findMedicineByName(medicineName);
        if(foundMedicine != null && foundMedicine.getMedicineStock() >= quantity) {
            double totalPrice = foundMedicine.getMedicinePrice() * quantity;
            if(Medicine.updateStock(medicineName, quantity)) {
                Medicine.addSalesRecord(customerName, medicineName, quantity, totalPrice);
                System.out.printf("Sale successful! Total Price: %.2f\n", totalPrice);
            }
        }
    }
    
    public void showAll() {
        ArrayList<String> salesRecords = Medicine.getSalesRecords();
        System.out.println();
        System.out.println("--- Sales List ---");
        for(String record : salesRecords) {
            String[] parts = record.split("\\|");
            System.out.printf("Customer: %s | Medicine: %s | Quantity: %s | Total: %s \n",
                            parts[0], parts[1], parts[2], parts[3]);
        }
    }
}


class Sales extends SalesTransaction {
    // METHOD OVERRIDING - Runtime polymorphism
    
    @Override
    public void handleSale(Medicine medicine) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println();
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter Medicine Name: ");
        String medicineName = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        
        // Call parent class method using super
        super.handleSale(customerName, medicineName, quantity);
    }
}

class DailyReport extends Report implements Reportable {
    // METHOD OVERRIDING from abstract class
    @Override
    public void generateReport() {
        System.out.println();
        System.out.println("--- Daily Sales Report ---");
        
        String lastSoldInfo = Medicine.getLastSoldMedicineInfo();
        int totalSales = Medicine.getTotalSales();
        String lowStockInfo = Medicine.getLowStockInfo();
        
        if(!lastSoldInfo.isEmpty()) {
            System.out.println("Total Medicines Sold: " + lastSoldInfo);
            System.out.println("Total Sales: " + totalSales + ".00");
        } else {
            System.out.println("Total Medicines Sold: 0");
            System.out.println("Total Sales: 0.00");
        }
        
        if(!lowStockInfo.isEmpty()) {
            System.out.println("Total of low stock: " + lowStockInfo);
        } else {
            System.out.println("Total of low stock: 0");
        }
    }
    
    // IMPLEMENTING interface method
    @Override
    public void printReport() {
        System.out.println();
        System.out.println("Report generated successfully!");
    }
}

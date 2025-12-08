import java.util.ArrayList;
import java.util.Scanner;

class Prescription {
    private static ArrayList<String[]> prescriptionList = new ArrayList<>();
    
    public void createPrescription() {
        Scanner scanner = new Scanner(System.in);
        int prescriptionChoice;
        
        do {
            System.out.println();
            System.out.println("--- Manage Prescriptions ---");
            System.out.println("1. Add Prescription");
            System.out.println("2. View Prescriptions");
            System.out.println("3. Back");
            System.out.print("\nEnter choice: ");
            prescriptionChoice = scanner.nextInt();
            scanner.nextLine();
            
            switch(prescriptionChoice) {
                case 1:
                    addPrescription(scanner);
                    break;
                case 2:
                    viewAllPrescriptions();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        } while(prescriptionChoice != 3);
    }
    
    private void addPrescription(Scanner scanner) {
        System.out.println();
        System.out.print("Enter Prescription ID: ");
        String prescriptionId = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter Medicine Name: ");
        String medicineName = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        
        Medicine medicine = Medicine.findMedicineByName(medicineName);
        if(medicine != null) {
            if(medicine.getMedicineStock() < quantity) {
                System.out.println("--- Alert: Low stock! ---");
            }
            
            String[] prescription = {prescriptionId, customerName, medicineName, String.valueOf(quantity)};
            prescriptionList.add(prescription);
            System.out.println();
            System.out.println("Prescription added successfully!");
        } else {
            System.out.println("Medicine not found!");
        }
        
        viewAllPrescriptions();
    }
    
    public void viewAllPrescriptions() {
        System.out.println();
        System.out.println("--- Prescription List ---");
        for(String[] prescription : prescriptionList) {
            System.out.println("ID: " + prescription[0] + 
                             " | Customer Name: " + prescription[1] + 
                             " | Medicine Name: " + prescription[2] + 
                             " | Quantity: " + prescription[3]);
        }
    }
    
    public void showAll() {
        viewAllPrescriptions();
    }
}
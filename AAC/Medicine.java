import java.util.Scanner;

// Medicine class handles registering medicines, stock, price, and expiry
// Composition - Medicine objects are part-of the system and cannot exist independently here.
public class Medicine {
    private int medicineID;
    private String name;
    private double price;
    private int stock;
    private String expiry;

    // Array stores all Medicine objects
    private static Medicine[] medicineList = new Medicine[100]; // Composition - Medicines are part of this system
    private static int medicineCount = 0;

    public void registerMedicine() {
        Scanner scanner = new Scanner(System.in);
        Medicine medicine = new Medicine(); // Composition - new Medicine object "part-of" system

        System.out.print("Enter Medicine ID: ");
        medicine.medicineID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Medicine Name: ");
        medicine.name = scanner.nextLine();
        System.out.print("Enter Price: ");
        medicine.price = scanner.nextDouble();
        System.out.print("Enter Stock Quantity: ");
        medicine.stock = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
        medicine.expiry = scanner.nextLine();

        medicineList[medicineCount++] = medicine;
        System.out.println("Medicine registered successfully!");
    }

    public void checkStock() {
        System.out.println("\n--- Medicine Stock ---");
        boolean lowStockExist = false;

        // Show all medicines with sufficient stock
        for (int i = 0; i < medicineCount; i++) {
            if (medicineList[i].stock > 5) {
                System.out.println("ID: " + medicineList[i].medicineID +
                        " | Name: " + medicineList[i].name +
                        " | Stock: " + medicineList[i].stock +
                        " | Price: ₱" + medicineList[i].price);
            } else {
                lowStockExist = true;
            }
        }

        // Show low stock medicines
        if (lowStockExist) {
            System.out.println("\n--- Low Stock Alert ---");
            for (int i = 0; i < medicineCount; i++) {
                if (medicineList[i].stock <= 5) {
                    System.out.println("ID: " + medicineList[i].medicineID +
                            " | Name: " + medicineList[i].name +
                            " | Stock: " + medicineList[i].stock +
                            " | Price: ₱" + medicineList[i].price);
                }
            }
        }
    }

    public boolean updateStock(int id, int qty) {
        for (int i = 0; i < medicineCount; i++) {
            if (medicineList[i].medicineID == id) {
                if (medicineList[i].stock >= qty) {
                    medicineList[i].stock -= qty;
                    return true;
                } else {
                    System.out.println("Insufficient stock!");
                    return false;
                }
            }
        }
        System.out.println("Medicine not found!");
        return false;
    }

    public double getPrice(int id) {
        for (int i = 0; i < medicineCount; i++) {
            if (medicineList[i].medicineID == id) {
                return medicineList[i].price;
            }
        }
        return 0;
    }
}


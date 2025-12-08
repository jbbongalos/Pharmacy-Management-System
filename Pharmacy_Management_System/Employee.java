import java.util.ArrayList;
import java.util.Scanner;

class Employee extends Person {
    private String position;
    private String shift;
    private static ArrayList<Employee> employeeList = new ArrayList<>();
    
    // Default constructor for main class
    public Employee() {}
    
    // Constructor using super keyword
    public Employee(String id, String name, String position, String shift) {
        super(id, name); // Call to parent constructor
        this.position = position;
        this.shift = shift;
    }
    
    // Getters and setters
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public String getShift() {
        return shift;
    }
    
    public void setShift(String shift) {
        this.shift = shift;
    }
    
    // CRUD Operations
    public void manageEmployee() {
        Scanner scanner = new Scanner(System.in);
        int employeeChoice;
        
        do {
            System.out.println();
            System.out.println("--- Manage Employees ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Back");
            System.out.println();
            System.out.print("Enter choice: ");
            employeeChoice = scanner.nextInt();
            scanner.nextLine();
            
            switch(employeeChoice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    updateEmployee(scanner);
                    break;
                case 4:
                    deleteEmployee(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        } while(employeeChoice != 5);
    }
    
    private void addEmployee(Scanner scanner) {
        System.out.println();
        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.nextLine();
        System.out.print("Enter Employee Name: ");
        String employeeName = scanner.nextLine();
        System.out.print("Enter Position: ");
        String position = scanner.nextLine();
        System.out.print("Enter Shift: ");
        String shift = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        
        Employee newEmployee = new Employee(employeeId, employeeName, position, shift);
        newEmployee.setContactNumber(contact);
        employeeList.add(newEmployee);
        System.out.println();
        System.out.println("Employee added successfully!");
        viewAllEmployees();
    }
    
    public void viewAllEmployees() {
        System.out.println();
        System.out.println("--- Employee List ---");
        for(Employee employee : employeeList) {
            System.out.println("ID: " + employee.getPersonId() + 
                             " | Name: " + employee.getPersonName() + 
                             " | Position: " + employee.getPosition() + 
                             " | Shift: " + employee.getShift() + 
                             " | Contact Number: " + employee.getContactNumber());
        }
    }
    
    private void updateEmployee(Scanner scanner) {
        System.out.println();
        System.out.print("Enter Employee ID to update: ");
        String employeeId = scanner.nextLine();
        
        for(Employee employee : employeeList) {
            if(employee.getPersonId().equals(employeeId)) {
                System.out.print("Enter new name: ");
                employee.setPersonName(scanner.nextLine());
                System.out.print("Enter new position: ");
                employee.setPosition(scanner.nextLine());
                System.out.print("Enter new shift: ");
                employee.setShift(scanner.nextLine());
                System.out.print("Enter new contact: ");
                employee.setContactNumber(scanner.nextLine());
                System.out.println("Employee updated successfully!");
                return;
            }
        }
        System.out.println("Employee not found!");
    }
    
    private void deleteEmployee(Scanner scanner) {
        System.out.println();
        System.out.print("Enter Employee ID to delete: ");
        String employeeId = scanner.nextLine();
        
        for(int i = 0; i < employeeList.size(); i++) {
            if(employeeList.get(i).getPersonId().equals(employeeId)) {
                employeeList.remove(i);
                System.out.println("Employee deleted successfully!");
                return;
            }
        }
        System.out.println("Employee not found!");
    }
    
    @Override
    public void displayInformation() {
        System.out.println("Employee: " + personName + ", Position: " + position + ", Shift: " + shift);
    }
    
    public void showAll() {
        viewAllEmployees();
    }
}
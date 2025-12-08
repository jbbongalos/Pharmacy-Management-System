
abstract class Person {
    // Protected fields for inheritance
    protected String personId;
    protected String personName;
    protected String contactNumber;
    
    // Default constructor
    public Person() {}
    
    // Constructor for child classes
    public Person(String id, String name) {
        this.personId = id;
        this.personName = name;
    }
    // Getters and setters
    public String getPersonId() {
        return personId;
    }
    public void setPersonId(String personId) {
        this.personId = personId;
    }
    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String personName) {
        this.personName = personName;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
    // Abstract method
    public abstract void displayInformation();
}
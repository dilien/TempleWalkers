package items;

public class EmployeeID extends Item{
    public String department;
    public String name;
    public String id;

    public EmployeeID(String id, String department){
        this.department = department;
        this.id = id;
        //TODO: implement random name generation
        this.name = "Joe Swanson";
    }

    public String getName() {
        return "ID number: " + id;
    }

    public String describe() {
        return "This is the ID card for " + name + " with an employee ID of '" + id + "'. They appear to work in the " + department + " department.";
    }
}

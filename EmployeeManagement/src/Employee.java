public class Employee {

    private int id;
    private String name;
    private double salary;
    private String department;
    private Integer managerId; // 🔥 NEW FIELD

    // Constructor
    public Employee(int id, String name, double salary, String department, Integer managerId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.managerId = managerId;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public Integer getManagerId() { return managerId; }
}
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAO();

        while (true) {

            System.out.println("\n==== Employee Management ====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. Search Employee");
            System.out.println("6. Add Bonus (All Employees with Manager)");
            System.out.println("7. Assign Manager");
            System.out.println("8. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // 🔥 important (avoid input skip)

            switch (choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();

                    System.out.print("Enter Manager ID (or 0 if none): ");
                    int mgrId = sc.nextInt();
                    sc.nextLine();

                    if (salary <= 0) {
                        System.out.println("Invalid Salary!");
                        break;
                    }

                    Integer managerId = (mgrId == 0) ? null : mgrId;

                    dao.addEmployee(new Employee(id, name, salary, dept, managerId));
                    break;

                case 2:
                    dao.viewEmployees();
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    int uid = sc.nextInt();

                    System.out.print("New Salary: ");
                    double sal = sc.nextDouble();

                    dao.updateSalary(uid, sal);
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    int did = sc.nextInt();

                    dao.deleteEmployee(did);
                    break;

                case 5:
                    System.out.print("Enter ID: ");
                    int sid = sc.nextInt();

                    dao.searchEmployee(sid);
                    break;

                case 6:
                    System.out.print("Enter Bonus Percentage: ");
                    double percent = sc.nextDouble();

                    if (percent <= 0) {
                        System.out.println("Invalid percentage!");
                        break;
                    }

                    dao.addBonusToEmployeesWithManager(percent);
                    break;

                case 7:
                    System.out.print("Enter Employee ID: ");
                    int empId = sc.nextInt();

                    System.out.print("Enter Manager ID: ");
                    int managerIdInput = sc.nextInt();

                    dao.assignManager(empId, managerIdInput);
                    break;

                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
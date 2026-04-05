import java.sql.*;

public class EmployeeDAO {
    public void addEmployee(Employee e) {

        String sql = "INSERT INTO employees (id, name, salary, department, manager_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, e.getId());
            ps.setString(2, e.getName());
            ps.setDouble(3, e.getSalary());
            ps.setString(4, e.getDepartment());

            if (e.getManagerId() == null) {
                ps.setNull(5, Types.INTEGER);
            } else {
                ps.setInt(5, e.getManagerId());
            }

            ps.executeUpdate();
            System.out.println("Employee Added!");

        } catch (SQLException ex) {
            System.out.println("Error adding employee");
            ex.printStackTrace();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    // VIEW WITH MANAGER NAME (JOIN)
    public void viewEmployees() {

        String sql = "SELECT e.id, e.name, e.salary, e.department, m.name AS manager_name " +
                "FROM employees e LEFT JOIN employees m ON e.manager_id = m.id";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getDouble("salary") + " | " +
                                rs.getString("department") + " | Manager: " +
                                rs.getString("manager_name")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching employees");
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // UPDATE SALARY
    public void updateSalary(int id, double salary) {

        String sql = "UPDATE employees SET salary=? WHERE id=?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, salary);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Updated Successfully!");
            else
                System.out.println("Employee Not Found!");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // DELETE
    public void deleteEmployee(int id) {

        String sql = "DELETE FROM employees WHERE id=?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Deleted Successfully!");
            else
                System.out.println("Employee Not Found!");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // SEARCH WITH MANAGER
    public void searchEmployee(int id) {

        String sql = "SELECT e.id, e.name, e.salary, e.department, m.name AS manager_name " +
                "FROM employees e LEFT JOIN employees m ON e.manager_id = m.id WHERE e.id=?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + " | " +
                                    rs.getString("name") + " | " +
                                    rs.getDouble("salary") + " | " +
                                    rs.getString("department") + " | Manager: " +
                                    rs.getString("manager_name")
                    );
                } else {
                    System.out.println("Employee Not Found!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ASSIGN MANAGER
    public void assignManager(int empId, int managerId) {

        if (empId == managerId) {
            System.out.println("Employee cannot be their own manager!");
            return;
        }

        String sql = "UPDATE employees SET manager_id=? WHERE id=?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, managerId);
            ps.setInt(2, empId);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Manager assigned!");
            else
                System.out.println("Employee not found!");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // BONUS FOR EMPLOYEES WITH MANAGER
    public void addBonusToEmployeesWithManager(double percent) {

        String sql = "UPDATE employees SET salary = salary + (salary * ? / 100) WHERE manager_id IS NOT NULL";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, percent);

            int rows = ps.executeUpdate();

            System.out.println(rows + " employees got " + percent + "% bonus!");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
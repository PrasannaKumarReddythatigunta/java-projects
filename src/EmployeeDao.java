import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private final String url = "jdbc:mysql://localhost:3306/employees";
    private final String username = "root";
    private final String password = "Admin@123";

    private final String INSERT_EMPLOYEE = "INSERT INTO employee (employeeid,name, age, designation) VALUES (?,?, ?, ?)";
    private final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employee";
    private final String UPDATE_EMPLOYEE = "UPDATE employee SET name=?, age=?, designation=? WHERE employeeid=?";
    private final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE employeeid=?";
    private final String FETCH_EMPLOYEE = "SELECT * FROM employee WHERE employeeid=?";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public void insertEmployee(Employee employee) {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERT_EMPLOYEE)) {
        	statement.setInt(1, employee.getEmployeeid());
            statement.setString(2, employee.getName());
            statement.setInt(3, employee.getAge());
            statement.setString(4, employee.getdesignation());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Employee> getEmployeeById(int empId) {
        List<Employee> employeeData = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(FETCH_EMPLOYEE)) {
            statement.setInt(1, empId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
              Employee  employee = new Employee(
                        resultSet.getInt("employeeid"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("designation"));
              employeeData.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeData;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_EMPLOYEES)) {
            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("employeeid"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("designation"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public void updateEmployee(Employee employee) {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_EMPLOYEE)) {
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getAge());
            statement.setString(3, employee.getdesignation());
            statement.setInt(4, employee.getEmployeeid());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int employeeid) {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_EMPLOYEE)) {
            statement.setInt(1, employeeid);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

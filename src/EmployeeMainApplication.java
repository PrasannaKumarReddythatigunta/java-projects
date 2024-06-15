import java.util.List;

public class EmployeeMainApplication {
	public static void main(String[] args) {
		EmployeeDao employeeDAO = new EmployeeDao();

		Employee new1 = new Employee(15, "Naveen", 23, "QA Engineer");
		Employee new6 = new Employee(43,"kalyan",30,"Test Engineer");
		Employee new10 = new Employee(33,"Riya",31,"Program Designer");
		Employee new13 = new Employee(45,"chandhu",30,"Software Devoloper");
		/** Insert Employee Data **/
	    employeeDAO.insertEmployee(new13);
	
		/** Update Employee Data **/
//		employeeDAO.updateEmployee(new10);

		/** Fetch All the Employees Data **/
		List<Employee> empDetails = employeeDAO.getAllEmployees();
//		System.out.println(empDetails);

		/** Fetch Employee Data Based On ID **/
		List<Employee> data = employeeDAO.getEmployeeById(45);
		System.out.println(data.get(0));

		/** Delete Employee Data Based on ID **/
//		employeeDAO.deleteEmployee(56);
	}
}

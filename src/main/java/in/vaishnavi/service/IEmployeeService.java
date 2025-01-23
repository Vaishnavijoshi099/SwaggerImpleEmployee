package in.vaishnavi.service;

import java.util.List;

import in.vaishnavi.entity.Employee;

public interface IEmployeeService {
	Long createEmployee(Employee employee);
	List<Employee> findAllEmployees();
	Employee findOneEmployee(Long id);
	void deleteOneEmployee(Long id);
	void updateEmployee(Employee employee);
	int updateEmployeeName(String ename,Long eid);

}

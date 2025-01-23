package in.vaishnavi.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.vaishnavi.entity.Employee;
import in.vaishnavi.exception.EmployeeNotFoundException;
import in.vaishnavi.repo.EmployeeRepository;
import in.vaishnavi.service.IEmployeeService;



@Service
public class EmployeeServiceImple implements IEmployeeService {
	
	@Autowired
	private EmployeeRepository repo;

	@Override
	public Long createEmployee(Employee employee) {
		employee = repo.save(employee);
		return employee.getEmpId();
	}

	@Override
	public List<Employee> findAllEmployees() {
		List<Employee> list = repo.findAll();
		return list;
	}

	@Override
	public Employee findOneEmployee(Long id) {
		Optional<Employee> opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new EmployeeNotFoundException("Employee"+id+"not exists");
		}
	}

	@Override
	public void deleteOneEmployee(Long id) {
		repo.delete(findOneEmployee(id));
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		Long id = employee.getEmpId();
		if(id!=null && repo.existsById(id)) {
			repo.save(employee);
		}else {
			throw new EmployeeNotFoundException("Employee" + id +"does not exists");
			
		}
		
	}

	@Override
	@javax.transaction.Transactional
	public int updateEmployeeName(String ename, Long eid) {
		if(eid!=null && repo.existsById(eid)) {
			return repo.updateEmployeeName(ename, eid);
		}else {
			throw new EmployeeNotFoundException("Employee"+eid+"not exist");
		}
	}

}

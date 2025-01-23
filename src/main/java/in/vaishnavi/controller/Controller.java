package in.vaishnavi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.vaishnavi.entity.Employee;
import in.vaishnavi.exception.EmployeeNotFoundException;
import in.vaishnavi.service.IEmployeeService;

@RestController
@RequestMapping("/api/employee")
public class Controller {

    @Autowired
    private IEmployeeService service;

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        Long id = service.createEmployee(employee);
        String message = "Employee " + id + " created";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> findAllEmployees() {
        List<Employee> list = service.findAllEmployees();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findOneEmployee(@PathVariable Long id) {
        try {
            Employee employee = service.findOneEmployee(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            service.deleteOneEmployee(id);
            return new ResponseEntity<>("Employee deleted", HttpStatus.NO_CONTENT);
        } catch (EmployeeNotFoundException e) {
            throw e;
        }
    }

    @PutMapping
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
        try {
            service.updateEmployee(employee);
            return new ResponseEntity<>("Employee Updated", HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            throw e;
        }
    }

    @PatchMapping("/{id}/name/{name}")
    public ResponseEntity<String> updateEmployeeName(@PathVariable Long id, @PathVariable String name) {
        try {
            service.updateEmployeeName(name, id);
            return new ResponseEntity<>("Employee Name Updated", HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            throw e;
        }
    }
}

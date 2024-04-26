package com.yasinenessisik.javaspringJPAm1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id){
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody SaveEmployeeDto employee) {
        Employee newEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(newEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employeeDetails)  {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{employeeId}/workplaces")
    public ResponseEntity<Workplace> addWorkplaceToEmployee(@PathVariable Integer employeeId, @RequestBody Workplace workplace){
        Workplace newWorkplace = employeeService.addWorkplaceToEmployee(employeeId, workplace);
        return ResponseEntity.ok(newWorkplace);
    }

    @GetMapping("/{employeeId}/workplaces")
    public ResponseEntity<List<Workplace>> getEmployeeWorkplaces(@PathVariable Integer employeeId){
        List<Workplace> workplaces = employeeService.getEmployeeWorkplaces(employeeId);
        return ResponseEntity.ok(workplaces);
    }
}

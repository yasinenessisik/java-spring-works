package com.yasinenessisik.javaspringJPAm1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WorkPlaceRepository workplaceRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow();
        return employee;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer employeeId, Employee employeeDetails)  {
        Employee existingEmployee = getEmployeeById(employeeId);
        existingEmployee.setEmployeeName(employeeDetails.getEmployeeName());
        // Update other fields as needed

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Integer employeeId){
        getEmployeeById(employeeId); // Throws ResourceNotFoundException if not found
        employeeRepository.deleteById(employeeId);
    }

    public Workplace addWorkplaceToEmployee(Integer employeeId, Workplace workplace){
        Employee employee = getEmployeeById(employeeId);
        workplace.setEmployee(employee);
        workplaceRepository.save(workplace);
        return workplace;
    }

    public List<Workplace> getEmployeeWorkplaces(Integer employeeId){
        Employee employee = getEmployeeById(employeeId);
        return employee.getWorkPlaces();
    }


    // Additional methods for workplace CRUD operations can be added here
}

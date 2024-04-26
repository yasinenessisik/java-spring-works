package com.yasinenessisik.javaspringredis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeRedisRepository employeeRedisRepository;
    private final WorkPlaceRepository workPlaceRepository;
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        saveEmployeeRedis(convertToEmployeeRedis(employee));
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
        employeeRedisRepository.deleteById(id);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
        Optional<EmployeeRedis> employeeRedis = getEmployeeRedisById(employee.getId());
        if (employeeRedis.isPresent()) {
            saveEmployeeRedis(convertToEmployeeRedis(employee));
        }
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        Optional<EmployeeRedis> employeeRedis = getEmployeeRedisById(id);
        if (employeeRedis.isPresent()) {
            System.out.println("Redis'ten geldi.");
            return Optional.of(convertToEmployee(employeeRedis.get()));
        } else {
            System.out.println("Database'ten geldi");
            Optional<Employee> employeeFromDatabase = employeeRepository.findById(id);
            employeeFromDatabase.ifPresent(emp -> saveEmployeeRedis(convertToEmployeeRedis(emp)));
            return employeeFromDatabase;
        }
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    private void saveEmployeeRedis(EmployeeRedis employeeRedis) {
        employeeRedisRepository.save(employeeRedis);
    }

    private Optional<EmployeeRedis> getEmployeeRedisById(Integer id) {
        return employeeRedisRepository.findById(id);
    }

    private EmployeeRedis convertToEmployeeRedis(Employee employee) {
        EmployeeRedis employeeRedis = new EmployeeRedis();
        employeeRedis.setId(employee.getId());
        employeeRedis.setFirstName(employee.getFirstName());
        employeeRedis.setLastName(employee.getLastName());
        employeeRedis.setEmail(employee.getEmail());
        employeeRedis.setWorkPlaces(employee.getWorkPlaces());
        employeeRedis.setExpration(30L);
        return employeeRedis;
    }

    private Employee convertToEmployee(EmployeeRedis employeeRedis) {
        Employee employee = new Employee();
        employee.setId(employeeRedis.getId());
        employee.setFirstName(employeeRedis.getFirstName());
        employee.setLastName(employeeRedis.getLastName());
        employee.setEmail(employeeRedis.getEmail());
        employee.setWorkPlaces(employeeRedis.getWorkPlaces());
        return employee;
    }
}
package com.yasinenessisik.javaspringredis;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeRedisRepository employeeRedisRepository;
    private final WorkPlaceRepository workPlaceRepository;
    @Transactional
    public Employee saveEmployee(EmployeeSaveDto from) {
        Employee employee = new Employee();
        employee.setEmail(from.getEmail());
        employee.setFirstName(from.getFirstName());
        employee.setLastName(from.getLastName());
        WorkPlace workPlace = new WorkPlace();
        workPlace.setWorkplaceName(from.getWorkplaceName());
        employee.getWorkPlaces().add(workPlace);
        Employee save = employeeRepository.save(employee);
        saveEmployeeRedis(convertToEmployeeRedis(save));
        return save;
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
        employeeRedisRepository.deleteById(id);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
        Optional<EmployeeRedis> employeeRedis = getEmployeeRedisById(employee.getEmployee_id());
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
            System.out.println(employeeFromDatabase.get().toString());

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
        Optional<EmployeeRedis> byId = employeeRedisRepository.findById(id);
        return byId;
    }

    private EmployeeRedis convertToEmployeeRedis(Employee employee) {
        EmployeeRedis employeeRedis = new EmployeeRedis();
        employeeRedis.setId(employee.getEmployee_id());
        employeeRedis.setFirstName(employee.getFirstName());
        employeeRedis.setLastName(employee.getLastName());
        employeeRedis.setEmail(employee.getEmail());
        employeeRedis.setWorkPlaces(employee.getWorkPlaces());
        employeeRedis.setExpration(30L);
        return employeeRedis;
    }

    private Employee convertToEmployee(EmployeeRedis employeeRedis) {
        Employee employee = new Employee();
        employee.setEmployee_id(employeeRedis.getId());
        employee.setFirstName(employeeRedis.getFirstName());
        employee.setLastName(employeeRedis.getLastName());
        employee.setEmail(employeeRedis.getEmail());
        employee.setWorkPlaces(employeeRedis.getWorkPlaces());
        return employee;
    }
}
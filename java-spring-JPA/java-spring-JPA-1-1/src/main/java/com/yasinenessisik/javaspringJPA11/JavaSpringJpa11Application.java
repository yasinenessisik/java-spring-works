package com.yasinenessisik.javaspringJPA11;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaSpringJpa11Application implements ApplicationRunner {
	private final EmployeeRepository employeeRepository;
	private final WorkStationRepository workStationRepository;

    public JavaSpringJpa11Application(EmployeeRepository employeeRepository, WorkStationRepository workStationRepository) {
        this.employeeRepository = employeeRepository;
        this.workStationRepository = workStationRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(JavaSpringJpa11Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Employee employee = new Employee();
		employee.setEmployeeName("Enes");

		WorkStation workStation = new WorkStation();
		workStation.setWorkStationName("Malzeme");
		workStation.setEmployee(employee);

		employee.setWorkStation(workStation);


		Employee updated = employeeRepository.save(employee);
		System.out.println(updated.toString());
	}
}

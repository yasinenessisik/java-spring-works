package com.yasinenessisik.javaspringJPA11;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@ToString
public class Employee {
    @Id
    @GeneratedValue
    private Integer employeeId;

    private String employeeName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "workstationId")
    private WorkStation workStation;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public WorkStation getWorkStation() {
        return workStation;
    }

    public void setWorkStation(WorkStation workStation) {
        this.workStation = workStation;
    }
}

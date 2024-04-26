package com.yasinenessisik.javaspringredis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class WorkPlace{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workplaceId;
    private String workplaceName;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public WorkPlace() {
    }
}

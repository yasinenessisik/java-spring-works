package com.yasinenessisik.javaspringJPAm1;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workplace_id;
    private String workplaceName;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}

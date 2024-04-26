package com.yasinenessisik.javaspringredis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.jdbc.Work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Entity
public class Employee implements Serializable {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<WorkPlace> workPlaces;

    public Employee() {

    }
}
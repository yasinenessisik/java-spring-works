package com.yasinenessisik.javaspringredis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@ToString
public class WorkPlace implements Serializable {
    @Id
    private Integer workplaceId;
    private String workplaceName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

}

package com.yasinenessisik.javaspringredis;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RedisHash("employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRedis implements Serializable {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private List<WorkPlaceRedis> workPlaces;
    @TimeToLive
    private Long expration;
}

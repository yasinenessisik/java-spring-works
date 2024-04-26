package com.yasinenessisik.javaspringredis;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RedisHash("employee")
@Data
public class EmployeeRedis implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private List<WorkPlace> workPlaces;
    @TimeToLive
    private Long expration;
}

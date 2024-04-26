package com.yasinenessisik.javaspringredis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRedisRepository  extends CrudRepository<EmployeeRedis,Integer> {
}

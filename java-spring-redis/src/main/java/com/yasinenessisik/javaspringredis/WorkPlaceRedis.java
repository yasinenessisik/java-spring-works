package com.yasinenessisik.javaspringredis;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("workplace")
@Data
public class WorkPlaceRedis {
    @Id
    private Integer workplaceId;
    private String workplaceName;
}

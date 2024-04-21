package com.yasinenessisik.javaspringredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class JavaSpringRedisApplication{


    public static void main(String[] args) {
		SpringApplication.run(JavaSpringRedisApplication.class, args);
	}

}

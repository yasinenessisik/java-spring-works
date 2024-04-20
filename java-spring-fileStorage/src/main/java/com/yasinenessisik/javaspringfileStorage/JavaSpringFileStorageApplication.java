package com.yasinenessisik.javaspringfileStorage;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;

@SpringBootApplication
@EnableJpaRepositories
public class JavaSpringFileStorageApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringFileStorageApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String currentWorkingDirectory = System.getProperty("user.dir");
		String folderPath = currentWorkingDirectory + File.separator + "MyFiles";
		System.out.println(folderPath);
	}
}

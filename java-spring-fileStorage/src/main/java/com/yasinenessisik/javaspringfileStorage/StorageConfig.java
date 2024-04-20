package com.yasinenessisik.javaspringfileStorage;

import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class StorageConfig {

    public String folderPath() {
        String currentWorkingDirectory = System.getProperty("user.dir");
        return currentWorkingDirectory + File.separator + "MyFiles";
    }
}

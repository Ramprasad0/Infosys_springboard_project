package com.infosys.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Module1Application {

    public static void main(String[] args) {
        SpringApplication.run(Module1Application.class, args);
        System.out.println("Module_1 loaded...");
    }
}

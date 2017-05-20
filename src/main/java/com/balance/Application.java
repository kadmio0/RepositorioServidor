package com.balance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println("Hola soy Toki");
        System.out.println("Hola soy Daniel");
        System.out.println("Hola soy Jorge");
        System.out.println("Hola soy Kevin");
        SpringApplication.run(Application.class, args);
    }
}

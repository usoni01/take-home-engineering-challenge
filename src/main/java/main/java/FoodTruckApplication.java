package main.java;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class FoodTruckApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringApplication.run(FoodTruckApplication.class), args);
    }

    }


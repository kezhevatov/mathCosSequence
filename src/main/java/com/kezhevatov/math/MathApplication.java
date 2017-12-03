package com.kezhevatov.math;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Andrey Kezhevatov.
 * Date: 10.04.2016
 */
@SpringBootApplication
@EnableCaching
public class MathApplication {

    public static void main(String[] args) {
        SpringApplication.run(MathApplication.class, args);
    }
}
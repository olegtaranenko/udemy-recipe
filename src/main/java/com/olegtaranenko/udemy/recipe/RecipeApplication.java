package com.olegtaranenko.udemy.recipe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class RecipeApplication {

    public static void main(String[] args) {

        log.debug("start using Lombok logger");
        SpringApplication.run(RecipeApplication.class, args);
    }

}

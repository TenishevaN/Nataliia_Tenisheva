package com.epam.spring.homework1;

import com.epam.spring.homework1.config.BeansConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        ApplicationContext contextBeans = new AnnotationConfigApplicationContext(BeansConfig.class);
        contextBeans.getBean(Pet.class).printPets();
    }
}

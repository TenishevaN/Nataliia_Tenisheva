package com.epam.spring.homework1;

import com.epam.spring.homework1.pet.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Pet {
    @Autowired
    private List<Animal> animals;

    public void printPets() {
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}


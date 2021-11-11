package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class BeanE implements Validator{

    private String name;
    private int value;

    public BeanE() {
        System.out.println(this);
    }

    public BeanE(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println(" preDestroy BeanE");
    }

    @Override
    public String toString() {
        return "BeanE";
    }

    @Override
    public void validate() {
        String result = ValidateUtil.vaidateResult(name, value);
        if (!"".equals(result)){
            System.out.println(this.getClass().getSimpleName() +" " + result);
        }
    }
}

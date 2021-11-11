package com.epam.spring.homework2.beans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class BeanF implements Validator{

    private String name;
    private int value;

    public BeanF() {
        System.out.println(this);
    }

    public BeanF(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanF";
    }

    @Override
    public void validate() {
        String result = ValidateUtil.vaidateResult(name, value);
        if (!"".equals(result)){
            System.out.println(this.getClass().getSimpleName() +" " + result);
        }
    }
}

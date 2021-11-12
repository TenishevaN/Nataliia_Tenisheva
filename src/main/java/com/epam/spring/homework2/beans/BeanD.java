package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
public class BeanD implements Validator{

    @Value("${BeanD.name}")
    private String name;

    @Value("${BeanD.value}")
    private int value;


    public BeanD() {

        System.out.println(this);
    }

    public BeanD(String name, int value) {
        this.name = name;
        this.value = value;
    }

    private void customInitMethodD() {
        System.out.println(" custom init method for BeanD " + this);
    }

    private void customDestroyMethodD(){
        System.out.println(" custom destroy method for BeanD");
    }

    @Override
    public String toString() {
        return "BeanD{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public void validate() {
        String result = ValidateUtil.vaidateResult(name, value);
        if (!"".equals(result)){
            System.out.println(this.getClass().getSimpleName() +" " + result);
        }
    }
}

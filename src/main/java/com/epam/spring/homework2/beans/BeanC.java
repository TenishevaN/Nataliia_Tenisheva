package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn(value = {"beanD", "beanB"})
public class BeanC implements Validator{

    @Value("${BeanC.name}")
    private String name;

    @Value("${BeanC.value}")
    private int value;

    public BeanC() {
        System.out.println(this);
    }

    public BeanC(String name, int value) {
        this.name = name;
        this.value = value;
    }
    @PostConstruct
    public void postConstruct(){
        System.out.println(" postConstruct BeanE");
    }
    private void customInitMethodC() {
        System.out.println(" custom init method for BeanC " + this);
    }

    private void customDestroyMethodC(){
        System.out.println(" custom destroy method for BeanC");
    }

    @Override
    public String toString() {
        return "BeanC{" +
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

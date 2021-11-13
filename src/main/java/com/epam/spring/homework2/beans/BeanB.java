package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@DependsOn("beanD")
@PropertySource("application.properties")
public class BeanB implements Validator{

    @Value("${BeanB.name}")
    private String name;

    @Value("${BeanB.value}")
    private int value;

    public BeanB() {
        System.out.println(this);
    }

    public BeanB(String name, int value) {
        this.name = name;
        this.value = value;
    }

    private void customInitMethodB() {
        System.out.println(" custom init method for BeanB "  + this);
    }

    private void changedCustomInitMethodB() {
        System.out.println(" changed custom init method for BeanB "  + this);
    }



    private void customDestroyMethodB(){
        System.out.println(" custom destroy method for BeanB");
    }

    @Override
    public String toString() {
        return "BeanB{" +
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

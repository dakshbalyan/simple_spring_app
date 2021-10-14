package org.example.beanExample;

import org.springframework.stereotype.Component;

@Component
public class BasicBean {
    private String name;
    private String address;

    public void printHello() {
        System.out.println("Hello ! "+name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printAddress() {
        System.out.println("Address : "+address);
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

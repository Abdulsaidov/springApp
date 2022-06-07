package com.artur.spring.models;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Need name")
    @Size(min =  2, max = 30,message = "no way")
    @Column(name = "name")
    private String name;
    @Min(value = 0, message = "no no no")
    @Column(name = "age")
    private int age;
    @NotEmpty (message = "need smth")
    @Email
    @Column(name = "email")

    private String email;

    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Format address: Country, City, Postal Code(111111)")
    @Column(name = "address")
    private String address;

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

}
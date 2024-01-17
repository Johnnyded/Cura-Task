package com.curapatient.employeeSystem.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// Annotations for Spring boot and Lombok for boilerplate accessor methods
@Entity
@Getter
@Setter
@Data
@Table(name = "EMPLOYEES")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 45)
    private String name;

    @Column(name = "PHONE_NUMBER", length = 45)
    private String phoneNumber;

    @Column(name = "SUPERVISORS", length = 45)
    private String supervisors;

    //Default constructor
    public Employee() {
    }

    // Assuming you have a constructor for initial setup
    public Employee(String name, String phoneNumber, String supervisors) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.supervisors = supervisors;
    }


    // toString method for debugging
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", supervisors='" + supervisors + '\'' +
                '}';
    }
}


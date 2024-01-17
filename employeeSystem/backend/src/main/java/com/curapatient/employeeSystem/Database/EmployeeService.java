package com.curapatient.employeeSystem.Database;

import com.curapatient.employeeSystem.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    //Returns all employees using the findAll() method provided by JPA
    public List<Employee> findAllEmployees() {
//        System.out.println("find all called!");
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee createEmployee(Employee employee) {
        // Additional business logic and validations can be added here

        // Save the new employee to the database using the repository
        System.out.println("Employee saved!");
        return employeeRepository.save(employee);
    }

    // Other service methods for handling business logic related to employees
    // such as find, update, delete, etc., can be added here.
}

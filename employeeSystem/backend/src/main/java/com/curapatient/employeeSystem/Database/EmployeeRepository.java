package com.curapatient.employeeSystem.Database;

import com.curapatient.employeeSystem.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}

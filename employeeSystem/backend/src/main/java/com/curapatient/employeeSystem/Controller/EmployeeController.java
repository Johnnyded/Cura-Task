package com.curapatient.employeeSystem.Controller;

import com.curapatient.employeeSystem.Database.EmployeeRepository;
import com.curapatient.employeeSystem.Database.EmployeeService;
import com.curapatient.employeeSystem.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import java.net.URI;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin(origins = "*", methods = {GET, POST, PUT, DELETE, OPTIONS})  // Apply CORS settings
public class EmployeeController {


    // Inject the Employee Repository to interact with the database
    @Autowired
    EmployeeRepository employeeRepository;



    /** Time to define REST methods!! */

    // Returns an iterable object of the entire employees table
    @GetMapping("/employees")
    public Iterable<Employee> findAllEmployees() {
        System.out.println("HELLO!");
        return this.employeeRepository.findAll();
    }

    // Returns employee given a certain id
    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id){
        Optional<Employee> employeeData = employeeRepository.findById(id);

        if (employeeData.isPresent()){
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Method to ADD an employee to the database
    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee newEmployee) {
        try {
            Employee employee = employeeRepository.save(newEmployee);
            return ResponseEntity.created(URI.create("employees/" + employee.getId())).body(employee);
        } catch (Exception e) {
            // Handle the exception based on your application's requirements
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Failed to create new employee", e);
        }
    }

    // UPDATE: REST mapping to update an entry
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id") int id, @RequestBody Employee newEmployee) {
        //check if employee exists
        Optional<Employee> employeeData = employeeRepository.findById(id);

        //if employee exists, perform update
        if(employeeData.isPresent()){
            Employee employee = employeeData.get();
            employee.setName(newEmployee.getName());
            employee.setPhoneNumber(newEmployee.getPhoneNumber());
            employee.setSupervisors(newEmployee.getSupervisors());
            return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE: REST mapping to delete an entry
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("id") int id){
        try{
            employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE-ALL: REST mapping to delete all entries. Don't think I'll have to use this(hopefully)
    @DeleteMapping("/employees")
    public ResponseEntity<Employee> deleteAllEmployees(){
        try{
            employeeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

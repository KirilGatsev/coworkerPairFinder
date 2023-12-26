package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(this.employeeService.getAllEntities(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(this.employeeService.getEntityById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@RequestBody Employee employee,
                                       @PathVariable(name = "id") int id){
        return new ResponseEntity<>(this.employeeService.updateEntity(employee, id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(this.employeeService.saveEntity(employee), HttpStatus.CREATED);
    }

    //reason for no validation if employee exists with this id can be skipped because
    //the end result will be the same regardless of if an employee exists or not
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "id") int id){
        return new ResponseEntity<>(this.employeeService.deleteEntity(id), HttpStatus.OK);
    }

}

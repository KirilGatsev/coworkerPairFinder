package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.WorkHistory;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return this.employeeService.getAllEntities();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable(name = "id") int id){
        return this.employeeService.getEntityById(id);
    }

    @PutMapping
    public Employee updateEmployeeById(@RequestBody Employee employee){
        return this.employeeService.updateEntity(employee);
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee){
        return this.employeeService.saveEntity(employee);
    }

    //reason for no validation if employee exists with this id can be skipped because
    //the end result will be the same regardless of if an employee exists or not
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable(name = "id") int id){
        return this.employeeService.deleteEntity(id);
    }

}

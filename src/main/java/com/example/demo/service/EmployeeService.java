package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.WorkHistory;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.crudServices.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements CrudService<Employee> {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEntity(Employee entity) {
        return this.employeeRepository.save(entity);
    }

    //reason for no validation if employee exists with this id can be skipped because
    //the end result will be the same regardless of if an employee exists or not
    @Override
    public String deleteEntity(int id) {
        this.employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }

    @Override
    public Employee getEntityById(int id) {
        Employee emp = this.employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("placeholder"));
        for(WorkHistory wh: emp.getWorkHistory()){
            System.out.println(wh.toString());
        }
        System.out.println(emp.getFirstName());
        return emp;
    }

    @Override
    public List<Employee> getAllEntities() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee updateEntity(Employee entity) {
        if(!this.employeeRepository.existsById(entity.getId())){
            throw new RuntimeException("placeholder");
        }
        return this.employeeRepository.save(entity);
    }
}

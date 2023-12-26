package com.example.demo;

import com.example.demo.controller.EmployeeController;
import com.example.demo.model.Employee;
import com.example.demo.model.WorkHistory;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class LongestCoworkerFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(LongestCoworkerFinderApplication.class, args);
	}

}

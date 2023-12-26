package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Integer id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @OneToMany(mappedBy = "employeeId")
    @JsonManagedReference
    private final List<WorkHistory> workHistory = new ArrayList<>();

    public Employee(String firstName, String lastName, WorkHistory workHistory){
        this.firstName = firstName;
        this.lastName = lastName;
        this.workHistory.add(workHistory);
    }
}

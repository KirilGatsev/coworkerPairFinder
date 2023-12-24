package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//not needed for the scope of the final exam
// TODO implement this functionality and controller/service that deals with this dto
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    private Integer id;
    private String name;
    private LocalDate start;
    private LocalDate end;

}

package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
@ToString
public class WorkHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Integer id;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId")
    @Setter
    private Employee employeeId;
    @NotNull
    private Integer projectId;
    @NotNull
    private LocalDate start;
    @Setter
    private LocalDate end;

    public WorkHistory(Integer project_id, LocalDate start){
        this.projectId = project_id;
        this.start = start;
    }
}

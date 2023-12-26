package com.example.demo.repository;

import com.example.demo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//not needed for the scope of the final exam
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}

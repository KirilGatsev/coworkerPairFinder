package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Integer> {

    Optional<WorkHistory> findByEmployeeIdIsNotAndProjectIdIs(Employee employee, int projectId);

}

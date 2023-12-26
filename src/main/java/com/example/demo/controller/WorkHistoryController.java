package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.WorkHistory;
import com.example.demo.service.WorkHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/work_history")
public class WorkHistoryController {
    private final WorkHistoryService workHistoryService;

    public WorkHistoryController(WorkHistoryService workHistoryService){
        this.workHistoryService = workHistoryService;
    }

    @PostMapping("/user/{user_id}")
    public ResponseEntity<WorkHistory> saveWorkHistory(@RequestBody WorkHistory workHistory,
                                                       @PathVariable(name = "user_id") int user_id){
       return new ResponseEntity<>(this.workHistoryService.saveEntity(workHistory, user_id), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/find_pair")
    public ResponseEntity<String> findLongestPartner(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(this.workHistoryService.findLongestPartner(id), HttpStatus.OK);
    }

    @PostMapping("/file/{filename}")
    public ResponseEntity<String> saveWorkHistoriesFromFile(@PathVariable(name = "filename") String filename){
        return new ResponseEntity<>(this.workHistoryService.saveWorkHistoriesFromFile(filename), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkHistory> updateWorkHistoryById(@RequestBody WorkHistory workHistory,
                                       @PathVariable(name = "id") int id){
        return new ResponseEntity<>(this.workHistoryService.updateEntity(workHistory, id), HttpStatus.OK);
    }
}

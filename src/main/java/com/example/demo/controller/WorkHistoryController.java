package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.WorkHistory;
import com.example.demo.service.WorkHistoryService;
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
    public WorkHistory saveWorkHistory(@RequestBody WorkHistory workHistory,
                                       @PathVariable(name = "user_id") int user_id){
        System.out.println(workHistory);
       return this.workHistoryService.saveEntity(workHistory, user_id);
    }

    @GetMapping("/{id}/find_pair")
    public String findLongestPartner(@PathVariable(name = "id") Integer id){
        return this.workHistoryService.findLongestPartner(id);
    }

    @PostMapping("/file/{filename}")
    public String saveWorkHistoriesFromFile(@PathVariable(name = "filename") String filename){
        return this.workHistoryService.saveWorkHistoriesFromFile(filename);
    }

    @PutMapping("/{id}")
    public WorkHistory updateWorkHistoryById(@RequestBody WorkHistory workHistory,
                                       @PathVariable(name = "id") int id){
        return this.workHistoryService.updateEntity(workHistory, id);
    }
}

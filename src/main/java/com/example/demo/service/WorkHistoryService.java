package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.WorkHistory;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.WorkHistoryRepository;
import com.example.demo.service.crudServices.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class WorkHistoryService implements CrudService<WorkHistory> {

    private final WorkHistoryRepository workHistoryRepository;
    private final EmployeeRepository employeeRepository;

    public WorkHistoryService(WorkHistoryRepository workHistoryRepository,
                              EmployeeRepository employeeRepository){
        this.workHistoryRepository = workHistoryRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public WorkHistory saveEntity(WorkHistory entity) {
        if(entity.getEnd() == null){
            entity.setEnd(LocalDate.now());
        }
        return this.workHistoryRepository.save(entity);
    }

    //not sure how to implement that since this is entity is in many to one relationship
    //and need to get tthe id through either requestparam or pathvariable and i don't want
    //to set id in controller since that seems like a task for the service layer
    //another one would be to not implement Creatable interface at all but it is still creatable
    //so not sure which one is better/more correct
    public WorkHistory saveEntity(WorkHistory entity, int user_id){
        entity.setEmployeeId(this.employeeRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("placeholder")));
        return this.saveEntity(entity);
    }

    //reason for no validation if employee exists with this id can be skipped because
    //the end result will be the same regardless of if an employee exists or not
    @Override
    public String deleteEntity(int id) {
        this.workHistoryRepository.deleteById(id);
        return "Work history deleted successfully";
    }

    @Override
    public WorkHistory getEntityById(int id) {
        return this.workHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("placeholder"));
    }

    @Override
    public List<WorkHistory> getAllEntities() {
        return this.workHistoryRepository.findAll();
    }

    @Override
    public WorkHistory updateEntity(WorkHistory entity) {
        return null;
    }

    public String saveWorkHistoriesFromFile(String filename){
        String CSV_SEPARATOR = ",";

        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while(line != null){
                String[] objectAsStringArr = line.split(", ");
                int idx = 0;
                int emp_id = Integer.parseInt(objectAsStringArr[idx++]);
                int project_id = Integer.parseInt(objectAsStringArr[idx++]);
                LocalDate start = LocalDate.parse(objectAsStringArr[idx++]);
                String endAsString = objectAsStringArr[idx++];
                LocalDate end = endAsString.equals("NULL")? LocalDate.now(): LocalDate.parse(endAsString);
                WorkHistory wh = new WorkHistory(project_id, start);
                wh.setEmployeeId(this.employeeRepository.findById(emp_id).orElseThrow(
                        () -> new RuntimeException("placeholder")
                ));
                wh.setEnd(end);
                this.workHistoryRepository.save(wh);
                line = reader.readLine();
            }
        }catch(IOException e){

        }
        return "Successful upload of work history from file " + filename;
    }

    public String findLongestPartner(Integer id){
        Employee emp1 = this.employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("placeholder"));
        DataHolder res = findCommonProjects(emp1);
        return res != null? res.employeeId + " " + res.days : "No overlap found";
    }

    private DataHolder findCommonProjects(Employee employee){
        WorkHistory longestCommon = null;
        long longest = 0;
        int employeeId = 0;
        for(WorkHistory wh: employee.getWorkHistory()){
            WorkHistory commonWh = this.workHistoryRepository.findByEmployeeIdIsNotAndProjectIdIs(employee,
                            wh.getProjectId())
                    .orElseThrow(() -> new RuntimeException("placeholder"));
            if(overlap(wh, commonWh)){
                long currOverlap = periodFinder(wh, commonWh);
                if(longest < currOverlap){
                    longest = currOverlap;
                    employeeId = commonWh.getEmployeeId().getId();
                }
            }
        }
        return longest != 0? new DataHolder(employeeId, longest) : null;
    }

    private boolean overlap(WorkHistory wh1, WorkHistory wh2){
        return wh1.getStart().isBefore(wh2.getEnd()) && wh2.getStart().isBefore(wh1.getEnd());
    }

    private long periodFinder(WorkHistory wh1, WorkHistory wh2){
        LocalDate start = wh1.getStart().isAfter(wh2.getStart()) ? wh1.getStart() : wh2.getStart();
        LocalDate end = wh1.getEnd().isBefore(wh2.getEnd()) ? wh1.getEnd() : wh2.getEnd();
        return DAYS.between(start, end);
    }

    @AllArgsConstructor
    private class DataHolder{
        protected int employeeId;
        protected long days;
    }

}

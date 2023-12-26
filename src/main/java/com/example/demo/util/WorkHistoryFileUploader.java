package com.example.demo.util;

import com.example.demo.model.WorkHistory;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.WorkHistoryRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

@Component
public class WorkHistoryFileUploader implements FileUploader<WorkHistory>{

    private final WorkHistoryRepository workHistoryRepository;
    private final EmployeeRepository employeeRepository;

    private final String CSV_SEPARATOR = ", ";

    public WorkHistoryFileUploader(WorkHistoryRepository workHistoryRepository, EmployeeRepository employeeRepository){
        this.workHistoryRepository = workHistoryRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void readAndUploadFromFile(String filename) {
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while(line != null){
                String[] objectAsStringArr = line.split(CSV_SEPARATOR);
                int idx = 0;
                int emp_id = Integer.parseInt(objectAsStringArr[idx++]);
                int project_id = Integer.parseInt(objectAsStringArr[idx++]);
                LocalDate start = LocalDate.parse(objectAsStringArr[idx++]);
                String endAsString = objectAsStringArr[idx++];//++ left here in case we have more properties than that in the future
                LocalDate end = endAsString.equals("NULL")? LocalDate.now(): LocalDate.parse(endAsString);
                WorkHistory wh = new WorkHistory(project_id, start);
                wh.setEmployeeId(this.employeeRepository.findById(emp_id).orElseThrow(
                        () -> new RuntimeException("placeholder")
                ));
                wh.setEnd(end);
                saveEntityToDB(wh);
                line = reader.readLine();
            }
        }catch(IOException e){

        }
    }

    //overkill at first glance when all we do is save, but maybe needed if/when we have to place validations here
    @Override
    public void saveEntityToDB(WorkHistory entity) {
        this.workHistoryRepository.save(entity);
    }
}

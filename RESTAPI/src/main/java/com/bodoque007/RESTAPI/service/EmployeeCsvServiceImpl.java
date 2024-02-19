package com.bodoque007.RESTAPI.service;

import com.bodoque007.RESTAPI.model.EmployeeCSVRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Component
public class EmployeeCsvServiceImpl implements EmployeeCsvService {
    @Override
    public List<EmployeeCSVRecord> convertCSV(File file) {
        try {
            return new CsvToBeanBuilder<EmployeeCSVRecord>(new FileReader(file))
                    .withType(EmployeeCSVRecord.class)
                    .build().parse();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}

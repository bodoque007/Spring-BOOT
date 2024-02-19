package com.bodoque007.RESTAPI.data;

import com.bodoque007.RESTAPI.entity.Employee;
import com.bodoque007.RESTAPI.model.EmployeeCSVRecord;
import com.bodoque007.RESTAPI.repository.EmployeeRepository;
import com.bodoque007.RESTAPI.service.EmployeeCsvService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class loadData implements CommandLineRunner {
    private final EmployeeCsvService employeeCsvService;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        loadCsvData();
    }

    // Made with the goal of showcasing paging and sorting by adding plenty of data to the database.
    private void loadCsvData() throws FileNotFoundException {
        // Arbitrary 10, as CSV file has at least 10 elements.
        if (employeeRepository.count() < 10) {
            File file = ResourceUtils.getFile("classpath:csvData/employeeCsvData.csv");
            List<EmployeeCSVRecord> employeeCSVRecords = employeeCsvService.convertCSV(file);

            employeeCSVRecords.forEach(employeeCSVRecord -> {
                employeeRepository.save(Employee.builder()
                        .firstName(employeeCSVRecord.getFirstName())
                        .lastName(employeeCSVRecord.getLastName())
                        .email(employeeCSVRecord.getEmail()).build());
            });
        }
    }
}


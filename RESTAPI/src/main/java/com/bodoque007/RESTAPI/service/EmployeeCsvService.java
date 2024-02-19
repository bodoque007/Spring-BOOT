package com.bodoque007.RESTAPI.service;

import com.bodoque007.RESTAPI.model.EmployeeCSVRecord;

import java.io.File;
import java.util.List;

public interface EmployeeCsvService {
    List<EmployeeCSVRecord> convertCSV(File csvFile);
}

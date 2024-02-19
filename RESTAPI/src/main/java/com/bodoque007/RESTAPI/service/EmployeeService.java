package com.bodoque007.RESTAPI.service;

import com.bodoque007.RESTAPI.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeService {
    Employee findById(int id);
    Employee save(Employee employee);
    void deleteById(int id);
    Employee updateById(int id, Employee employee);
    Page<Employee> findAll(Integer pageNumber, Integer pageSize);
}

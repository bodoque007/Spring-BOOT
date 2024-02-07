package com.bodoque007.mvcCrud.service;

import com.bodoque007.mvcCrud.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    void deleteById(int id);
}

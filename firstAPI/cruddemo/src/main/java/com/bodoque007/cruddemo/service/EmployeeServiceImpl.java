package com.bodoque007.cruddemo.service;


import com.bodoque007.cruddemo.entity.Employee;
import com.bodoque007.cruddemo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
//        Optional<Employee> result = employeeRepository.findById(id);
//        Employee employee = null;
//        if (result.isPresent()) {
//            employee = result.get();
//        } else {
//            throw new RuntimeException("Didn't find employee with id " + id);
//        }
//        return employee;
        return employeeRepository.findById(id).orElse(null);
    }


    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}

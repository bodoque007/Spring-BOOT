package com.bodoque007.RESTAPI.service;


import com.bodoque007.RESTAPI.entity.Employee;
import com.bodoque007.RESTAPI.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    @Override
    @Transactional
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Employee updateById(int id, Employee employee) {
        Employee foundEmployee = employeeRepository.findById(id).orElse(null);

        if (foundEmployee != null) {
            foundEmployee.setFirstName(employee.getFirstName());
            foundEmployee.setLastName(employee.getLastName());
            foundEmployee.setEmail(employee.getEmail());
            return employeeRepository.save(foundEmployee);
        } else {
            return null;
        }
    }
}

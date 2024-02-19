package com.bodoque007.RESTAPI.service;


import com.bodoque007.RESTAPI.entity.Employee;
import com.bodoque007.RESTAPI.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Integer DEFAULT_PAGE = 0;
    private final Integer DEFAULT_PAGE_SIZE = 10;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Page<Employee> findAll(Integer pageNumber, Integer pageSize) {
        PageRequest page = createPageRequest(pageNumber, pageSize);
        return employeeRepository.findAll(page);
    }

    private PageRequest createPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;
        if (pageNumber != null && pageNumber > 0) {
            // Sets proper offset, as pageNumber in Spring Data starts from 0, not 1.
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }
        if (pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > 100) {
                queryPageSize = 100;
            } else {
                queryPageSize = pageSize;
            }
        }
        Sort sort = Sort.by("lastName").descending();
        return PageRequest.of(queryPageNumber, queryPageSize, sort);
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

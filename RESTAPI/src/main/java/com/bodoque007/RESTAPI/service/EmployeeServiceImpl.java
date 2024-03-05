package com.bodoque007.RESTAPI.service;


import com.bodoque007.RESTAPI.entity.Employee;
import com.bodoque007.RESTAPI.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "employee")
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Integer DEFAULT_PAGE = 0;
    private final Integer DEFAULT_PAGE_SIZE = 10;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Cacheable(value = "allemployeecache")
    @Override
    public Page<Employee> findAll(Integer pageNumber, Integer pageSize) {
        PageRequest page = createPageRequest(pageNumber, pageSize);
        return employeeRepository.findAll(page);
    }

    private PageRequest createPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber = DEFAULT_PAGE;
        int queryPageSize = DEFAULT_PAGE_SIZE;
        if (pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber;
        }
        if (pageSize != null && pageSize > 100) {
            queryPageSize = 100;
        } else if (pageSize != null) {
            queryPageSize = pageSize;
        }
        Sort sort = Sort.by("lastName").descending();
        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }


    @Cacheable(value = "employeecache", key = "#id")
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



    @Caching(evict = {@CacheEvict(value = "allemployeecache", allEntries = true),
    @CacheEvict(value = "employeecache", key = "#employee.id")})
    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    @Override
    @Transactional
    @Caching(evict = {@CacheEvict(value = "allemployeecache", allEntries = true),
            @CacheEvict(value = "employeecache", key = "#id")
    })
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    @CachePut(value = "employeecache", key = "#id")
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

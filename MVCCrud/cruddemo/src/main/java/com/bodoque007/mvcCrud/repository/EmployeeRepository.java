package com.bodoque007.mvcCrud.repository;

import com.bodoque007.mvcCrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //Bang!

    // method to sort by last name
    public List<Employee> findAllByOrderByLastNameAsc();
}

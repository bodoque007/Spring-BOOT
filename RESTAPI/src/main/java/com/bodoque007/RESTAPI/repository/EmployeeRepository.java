package com.bodoque007.RESTAPI.repository;

import com.bodoque007.RESTAPI.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //Bang!
}

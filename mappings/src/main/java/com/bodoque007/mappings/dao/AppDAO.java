package com.bodoque007.mappings.dao;

import com.bodoque007.mappings.entity.Instructor;


public interface AppDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);

}

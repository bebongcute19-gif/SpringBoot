package re.session03.repository;


import re.session03.model.Instructor;

import java.util.*;

public interface InstructorRepository {
    List<Instructor> findAll();
    Optional<Instructor> findById(String id);
    Instructor create(Instructor i);
    Instructor update(String id, Instructor i);
    Instructor deleteById(String id);
}
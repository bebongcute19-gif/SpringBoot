package re.session03.repository;

import re.session03.model.Enrollment;

import java.util.*;

public interface EnrollmentRepository {
    List<Enrollment> findAll();
    Optional<Enrollment> findById(String id);
    Enrollment create(Enrollment e);
    Enrollment update(String id, Enrollment e);
    Enrollment deleteById(String id);
}
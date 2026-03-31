package re.session03.repository;

import re.session03.model.Course;

import java.util.*;

public interface CourseRepository {
    List<Course> findAll();

    Optional<Course> findById(String id);

    Course create(Course c);

    Course update(String id, Course c);

    Course deleteById(String id);
}
package re.session03.repository.impl;

import org.springframework.stereotype.Repository;
import re.session03.model.Course;
import re.session03.model.Status;
import re.session03.repository.CourseRepository;

import java.util.*;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private final List<Course> list = new ArrayList<>();

    public CourseRepositoryImpl() {
        list.add(new Course("1L", "Java", Status.ACTIVE, "1L"));
        list.add(new Course("2L", "Spring", Status.INACTIVE, "1L"));
    }

    public List<Course> findAll() { return list; }

    public Optional<Course> findById(String id) {
        return list.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Course create(Course c) { list.add(c); return c; }

    public Course update(String id, Course c) {
        Course old = findById(id).orElseThrow();
        old.setTitle(c.getTitle());
        old.setStatus(c.getStatus());
        return old;
    }

    public Course deleteById(String id) {
        Course c = findById(id).orElseThrow();
        list.remove(c);
        return c;
    }
}
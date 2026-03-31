package re.session03.repository.impl;

import org.springframework.stereotype.Repository;
import re.session03.model.Enrollment;
import re.session03.repository.EnrollmentRepository;

import java.util.*;

@Repository
public class EnrollmentRepositoryImpl implements EnrollmentRepository {

    private final List<Enrollment> list = new ArrayList<>();

    public EnrollmentRepositoryImpl() {
        list.add(new Enrollment("1L", "Student A", "1L"));
        list.add(new Enrollment("2L", "Student B", "1L"));
    }

    public List<Enrollment> findAll() { return list; }

    public Optional<Enrollment> findById(String id) {
        return list.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public Enrollment create(Enrollment e) { list.add(e); return e; }

    public Enrollment update(String id, Enrollment e) {
        Enrollment old = findById(id).orElseThrow();
        return old;
    }

    public Enrollment deleteById(String id) {
        Enrollment e = findById(id).orElseThrow();
        list.remove(e);
        return e;
    }
}
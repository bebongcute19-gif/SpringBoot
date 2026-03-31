package re.session03.repository.impl;
import org.springframework.stereotype.Repository;
import re.session03.model.Instructor;
import re.session03.repository.InstructorRepository;

import java.util.*;

@Repository
public class InstructorRepositoryImpl implements InstructorRepository {

    private final List<Instructor> list = new ArrayList<>();

    public InstructorRepositoryImpl() {
        list.add(new Instructor("1L", "A", "a@gmail.com"));
        list.add(new Instructor("2L", "B", "b@gmail.com"));
    }

    public List<Instructor> findAll() { return list; }

    public Optional<Instructor> findById(String id) {
        return list.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Instructor create(Instructor i) { list.add(i); return i; }

    public Instructor update(String id, Instructor i) {
        Instructor old = findById(id).orElseThrow();
        old.setName(i.getName());
        old.setEmail(i.getEmail());
        return old;
    }

    public Instructor deleteById(String id) {
        Instructor i = findById(id).orElseThrow();
        list.remove(i);
        return i;
    }
}
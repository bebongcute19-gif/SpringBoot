package re.edu.service.impl;

import org.springframework.stereotype.Service;
import re.edu.dto.InstructorCreateRequest;
import re.edu.entity.Instructor;
import re.edu.repository.InstructorRepository;
import re.edu.service.InstructorService;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository repo;

    public InstructorServiceImpl(InstructorRepository repo) {
        this.repo = repo;
    }

    @Override
    public Instructor createInstructor(InstructorCreateRequest req) {
        Instructor i = new Instructor();
        i.setName(req.getName());
        i.setEmail(req.getEmail());
        return repo.save(i);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return repo.findAll();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
    }
}

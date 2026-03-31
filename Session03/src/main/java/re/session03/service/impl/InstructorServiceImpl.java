package re.session03.service.impl;

import org.springframework.stereotype.Service;
import re.session03.exception.ResourceNotFoundException;
import re.session03.model.Instructor;
import re.session03.repository.InstructorRepository;
import re.session03.service.InstructorService;

import java.util.List;
import java.util.UUID;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository insRepo;

    public InstructorServiceImpl(InstructorRepository insRepo) {
        this.insRepo = insRepo;
    }

    @Override
    public List<Instructor> getAll() {
        return insRepo.findAll();
    }

    @Override
    public Instructor getById(String id) {
        return insRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));
    }

    @Override
    public Instructor create(Instructor instructor) {
        instructor.setId(UUID.randomUUID().toString());
        return insRepo.create(instructor);
    }

    @Override
    public Instructor update(String id, Instructor instructor) {

        Instructor existing = insRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

        existing.setName(instructor.getName());
        existing.setEmail(instructor.getEmail());

        return insRepo.update(id, existing);
    }

    @Override
    public Instructor deleteById(String id) {
        return insRepo.deleteById(id);
    }
}
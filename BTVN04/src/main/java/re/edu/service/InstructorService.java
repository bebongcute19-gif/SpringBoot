package re.edu.service;

import re.edu.dto.InstructorCreateRequest;
import re.edu.entity.Instructor;

import java.util.List;

public interface InstructorService {

    Instructor createInstructor(InstructorCreateRequest req);

    List<Instructor> getAllInstructors();

    Instructor getInstructorById(Long id);
}
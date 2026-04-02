package re.edu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.ApiResponse;
import re.edu.entity.Student;
import re.edu.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    // POST /students
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createStudent(@RequestBody Student student) {

        studentRepository.save(student);

        return ResponseEntity.ok(
                new ApiResponse<>("Create student successfully", null)
        );
    }
}
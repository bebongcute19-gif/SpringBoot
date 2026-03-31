package re.session03.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.session03.model.Instructor;
import re.session03.response.ApiResponse;
import re.session03.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Success", instructorService.getAll())
        );
    }
}
package re.edu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.ApiResponse;
import re.edu.dto.InstructorCreateRequest;
import re.edu.service.InstructorService;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class Instructor {

    private final InstructorService instructorService;

    // POST /instructors
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createInstructor(
            @RequestBody InstructorCreateRequest request
    ) {
        instructorService.createInstructor(request);

        return ResponseEntity.ok(
                new ApiResponse<>("Create instructor successfully", null)
        );
    }
}
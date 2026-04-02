package re.edu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.*;
import re.edu.service.CourseService;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    // POST /courses
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createCourse(
            @RequestBody CourseCreateRequest request
    ) {
        courseService.createCourse(request);

        return ResponseEntity.ok(
                new ApiResponse<>("Create course successfully", null)
        );
    }

    // PUT /courses/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseUpdateRequest request
    ) {
        courseService.updateCourse(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>("Update course successfully", null)
        );
    }
}
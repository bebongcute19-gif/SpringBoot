package re.edu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.dto.*;
import re.edu.service.StudentEnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class StudentEnrollmentController {

    private final StudentEnrollmentService enrollmentService;

    // POST /courses/{courseId}/enrollments
    @PostMapping("/{courseId}/enrollments")
    public ResponseEntity<ApiResponse<CourseEnrollmentResponse>> enroll(
            @PathVariable Long courseId,
            @RequestBody CourseEnrollmentRequest request
    ) {

        CourseEnrollmentResponse response =
                enrollmentService.enroll(courseId, request.getStudentId());

        return ResponseEntity.ok(
                new ApiResponse<>("Enroll successfully", response)
        );
    }

    // DELETE /courses/{courseId}/enrollments/students/{studentId}
    @DeleteMapping("/{courseId}/enrollments/students/{studentId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long courseId,
            @PathVariable Long studentId
    ) {

        enrollmentService.delete(courseId, studentId);

        return ResponseEntity.status(204).body(
                new ApiResponse<>("Delete successfully", null)
        );
    }

    // GET /courses/{courseId}/enrollments/students?search=name
    @GetMapping("/{courseId}/enrollments/students")
    public ResponseEntity<ApiResponse<List<CourseEnrollmentResponse>>> search(
            @PathVariable Long courseId,
            @RequestParam(required = false) String search
    ) {

        List<CourseEnrollmentResponse> result =
                enrollmentService.search(courseId, search);

        return ResponseEntity.ok(
                new ApiResponse<>("Get students successfully", result)
        );
    }
}
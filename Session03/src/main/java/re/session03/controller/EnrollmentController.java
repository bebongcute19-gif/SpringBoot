package re.session03.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.session03.dto.EnrollCourseRequest;
import re.session03.dto.EnrollmentDetail;
import re.session03.model.Enrollment;
import re.session03.response.ApiResponse;
import re.session03.service.EnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // ===== GET ALL =====
    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAll() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Get all enrollments success",
                            enrollmentService.getAll())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // ===== GET BY ID =====
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Get enrollment success",
                            enrollmentService.getById(id))
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // ===== CREATE =====
    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> create(@RequestBody Enrollment enrollment) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Create enrollment success",
                            enrollmentService.create(enrollment)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // ===== UPDATE =====
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> update(@PathVariable String id,
                                                          @RequestBody Enrollment enrollment) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Update enrollment success",
                            enrollmentService.update(id, enrollment))
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // ===== DELETE =====
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> delete(@PathVariable String id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Delete enrollment success",
                            enrollmentService.deleteById(id))
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // ===== NGHIỆP VỤ =====
    @PostMapping("/enroll-course")
    public ResponseEntity<ApiResponse<EnrollmentDetail>> enrollCourse(
            @RequestBody EnrollCourseRequest request) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Enroll success",
                            enrollmentService.enrollCourse(request)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}
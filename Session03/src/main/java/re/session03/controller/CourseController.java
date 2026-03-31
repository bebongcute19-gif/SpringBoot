package re.session03.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.session03.model.Course;
import re.session03.response.ApiResponse;
import re.session03.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // ===== GET ALL =====
    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAll() {
        try {
            List<Course> courses = courseService.getAllCourses();
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Get all courses success", courses)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // ===== GET BY ID =====
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getById(@PathVariable String id) {
        try {
            Course course = courseService.getCourseById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Get course success", course)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // ===== CREATE =====
    @PostMapping
    public ResponseEntity<ApiResponse<Course>> create(@RequestBody Course course) {
        try {
            Course newCourse = courseService.createCourse(course);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Create course success", newCourse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // ===== UPDATE =====
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> update(@PathVariable String id,
                                                      @RequestBody Course course) {
        try {
            Course updated = courseService.updateCourse(id, course);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Update course success", updated)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    // ===== DELETE =====
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> delete(@PathVariable String id) {
        try {
            Course deleted = courseService.deleteCourseById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Delete course success", deleted)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}
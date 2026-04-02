package re.edu.service;

import re.edu.dto.*;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(CourseCreateRequest req);

    CourseResponse updateCourse(Long id, CourseUpdateRequest req);

    List<CourseResponse> getAllCourses();

    CourseResponse getCourseById(Long id);

    void deleteCourse(Long id);

    CourseEnrollmentResponse enrollCourse(Long courseId, CourseEnrollmentRequest request);

    List<CourseEnrollmentResponse> getStudents(Long courseId, String name);

    void removeStudent(Long courseId, Long studentId);
}
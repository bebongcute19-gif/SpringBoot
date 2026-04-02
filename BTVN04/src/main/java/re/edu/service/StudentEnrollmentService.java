package re.edu.service;

import re.edu.dto.CourseEnrollmentResponse;

import java.util.List;

public interface StudentEnrollmentService {
    CourseEnrollmentResponse enroll(Long courseId, Long studentId);

    void delete(Long courseId, Long studentId);

    List<CourseEnrollmentResponse> search(Long courseId, String name);

}

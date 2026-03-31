package re.session03.service;

import re.session03.dto.EnrollCourseRequest;
import re.session03.dto.EnrollmentDetail;
import re.session03.model.Enrollment;

import java.util.List;

public interface EnrollmentService {

    // ===== CRUD =====

    List<Enrollment> getAll();

    Enrollment getById(String id);

    Enrollment create(Enrollment enrollment);

    Enrollment update(String id, Enrollment enrollment);

    Enrollment deleteById(String id);

    // ===== NGHIỆP VỤ =====

    EnrollmentDetail enrollCourse(EnrollCourseRequest request);
}
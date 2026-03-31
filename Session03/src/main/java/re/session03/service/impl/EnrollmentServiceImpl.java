package re.session03.service.impl;

import org.springframework.stereotype.Service;
import re.session03.dto.EnrollCourseRequest;
import re.session03.dto.EnrollmentDetail;
import re.session03.exception.BadRequestException;
import re.session03.exception.ResourceNotFoundException;
import re.session03.model.Course;
import re.session03.model.Enrollment;
import re.session03.model.Status;
import re.session03.repository.CourseRepository;
import re.session03.repository.EnrollmentRepository;
import re.session03.repository.InstructorRepository;
import re.session03.service.EnrollmentService;

import java.util.List;
import java.util.UUID;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enRepo;
    private final CourseRepository courseRepo;
    private final InstructorRepository insRepo;

    public EnrollmentServiceImpl(EnrollmentRepository enRepo,
                                 CourseRepository courseRepo,
                                 InstructorRepository insRepo) {
        this.enRepo = enRepo;
        this.courseRepo = courseRepo;
        this.insRepo = insRepo;
    }

    // ===== CRUD =====

    @Override
    public List<Enrollment> getAll() {
        return enRepo.findAll();
    }

    @Override
    public Enrollment getById(String id) {
        return enRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
    }

    @Override
    public Enrollment create(Enrollment enrollment) {
        enrollment.setId(UUID.randomUUID().toString());
        return enRepo.create(enrollment);
    }

    @Override
    public Enrollment update(String id, Enrollment enrollment) {

        Enrollment existing = enRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        existing.setStudentName(enrollment.getStudentName());
        existing.setCourseId(enrollment.getCourseId());

        return enRepo.update(id, existing);
    }

    @Override
    public Enrollment deleteById(String id) {
        return enRepo.deleteById(id);
    }

    // ===== NGHIỆP VỤ CHÍNH =====

    @Override
    public EnrollmentDetail enrollCourse(EnrollCourseRequest req) {

        // 1. Check Course tồn tại
        Course course = courseRepo.findById(req.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        // 2. Check trạng thái ACTIVE
        if (course.getStatus() != Status.ACTIVE) {
            throw new BadRequestException("Course is not ACTIVE");
        }

        // 3. Check Instructor tồn tại
        insRepo.findById(course.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

        // 4. Tạo Enrollment
        Enrollment enrollment = new Enrollment(
                UUID.randomUUID().toString(),
                req.getStudentName(),
                req.getCourseId()
        );

        // 5. Lưu
        enRepo.create(enrollment);

        // 6. Trả về DTO
        return new EnrollmentDetail(enrollment, course);
    }
}
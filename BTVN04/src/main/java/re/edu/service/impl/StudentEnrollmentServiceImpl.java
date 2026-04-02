package re.edu.service.impl;

import org.springframework.stereotype.Service;
import re.edu.dto.CourseEnrollmentResponse;
import re.edu.entity.Course;
import re.edu.entity.Student;
import re.edu.entity.StudentEnrollment;
import re.edu.entity.CourseStatus;
import re.edu.repository.CourseRepository;
import re.edu.repository.StudentEnrollmentRepository;
import re.edu.repository.StudentRepository;
import re.edu.service.StudentEnrollmentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final StudentEnrollmentRepository enrollRepo;

    public StudentEnrollmentServiceImpl(StudentRepository studentRepo,
                                        CourseRepository courseRepo,
                                        StudentEnrollmentRepository enrollRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.enrollRepo = enrollRepo;
    }

    @Override
    public CourseEnrollmentResponse enroll(Long courseId, Long studentId) {

        // 1. check course tồn tại & đang ACTIVE
        if (!courseRepo.existsByIdAndStatus(courseId, CourseStatus.ACTIVE)) {
            throw new RuntimeException("Course not active or not found");
        }

        // 2. check đã đăng ký chưa
        if (enrollRepo.existsByCourseIdAndStudentId(courseId, studentId)) {
            throw new RuntimeException("Student already enrolled");
        }

        // 3. lấy student
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // 4. lấy course
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // 5. tạo enrollment
        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());

        // 6. save
        enrollRepo.save(enrollment);

        // 7. trả response
        return new CourseEnrollmentResponse(
                student.getId(),
                course.getId(),
                enrollment.getEnrolledAt()
        );
    }

    @Override
    public void delete(Long courseId, Long studentId) {

        if (!enrollRepo.existsByCourseIdAndStudentId(courseId, studentId)) {
            throw new RuntimeException("Enrollment not found");
        }

        enrollRepo.deleteByCourseIdAndStudentId(courseId, studentId);
    }

    @Override
    public List<CourseEnrollmentResponse> search(Long courseId, String name) {

        return enrollRepo.searchStudents(courseId, name).stream()
                .map(enrollment -> new CourseEnrollmentResponse(
                        enrollment.getStudent().getId(),
                        enrollment.getCourse().getId(),
                        enrollment.getEnrolledAt()
                ))
                .toList();
    }
}
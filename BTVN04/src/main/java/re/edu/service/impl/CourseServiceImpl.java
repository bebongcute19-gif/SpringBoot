package re.edu.service.impl;

import org.springframework.stereotype.Service;
import re.edu.dto.*;
import re.edu.entity.Course;
import re.edu.entity.Instructor;
import re.edu.repository.CourseRepository;
import re.edu.repository.InstructorRepository;
import re.edu.service.CourseService;
import re.edu.service.StudentEnrollmentService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepo;
    private final InstructorRepository instructorRepo;
    private final StudentEnrollmentService enrollmentService;

    public CourseServiceImpl(CourseRepository c,
                             InstructorRepository i,
                             StudentEnrollmentService e) {
        this.courseRepo = c;
        this.instructorRepo = i;
        this.enrollmentService = e;
    }

    // ================= CREATE =================
    @Override
    public CourseResponse createCourse(CourseCreateRequest req) {

        Instructor instructor = instructorRepo.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        Course c = new Course();
        c.setTitle(req.getTitle());
        c.setStatus(req.getStatus());
        c.setInstructor(instructor);

        courseRepo.save(c);

        return mapToResponse(c);
    }

    // ================= UPDATE =================
    @Override
    public CourseResponse updateCourse(Long id, CourseUpdateRequest req) {

        Course c = courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Instructor instructor = instructorRepo.findById(req.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        c.setTitle(req.getTitle());
        c.setStatus(req.getStatus());
        c.setInstructor(instructor);

        courseRepo.save(c);

        return mapToResponse(c);
    }

    // ================= GET ALL =================
    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ================= GET BY ID =================
    @Override
    public CourseResponse getCourseById(Long id) {
        Course c = courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return mapToResponse(c);
    }

    // ================= DELETE =================
    @Override
    public void deleteCourse(Long id) {
        if (!courseRepo.existsById(id)) {
            throw new RuntimeException("Course not found");
        }
        courseRepo.deleteById(id);
    }

    // ================= ENROLL =================
    @Override
    public CourseEnrollmentResponse enrollCourse(Long courseId, CourseEnrollmentRequest request) {
        return enrollmentService.enroll(courseId, request.getStudentId());
    }

    // ================= SEARCH STUDENTS =================
    @Override
    public List<CourseEnrollmentResponse> getStudents(Long courseId, String name) {
        return enrollmentService.search(courseId, name);
    }

    // ================= REMOVE STUDENT =================
    @Override
    public void removeStudent(Long courseId, Long studentId) {
        enrollmentService.delete(courseId, studentId);
    }

    // ================= MAPPER =================
    private CourseResponse mapToResponse(Course c) {
        return new CourseResponse(
                c.getId(),
                c.getTitle(),
                c.getStatus(),
                new CourseInstructorResponse(
                        c.getInstructor().getId(),
                        c.getInstructor().getName()
                )
        );
    }
}
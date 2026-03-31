package re.session03.service.impl;

import org.springframework.stereotype.Service;
import re.session03.exception.ResourceNotFoundException;
import re.session03.model.Course;
import re.session03.repository.CourseRepository;
import re.session03.service.CourseService;

import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepo;

    public CourseServiceImpl(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    // ===== GET ALL =====
    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    // ===== GET BY ID =====
    @Override
    public Course getCourseById(String id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    // ===== CREATE =====
    @Override
    public Course createCourse(Course course) {
        course.setId(UUID.randomUUID().toString());
        return courseRepo.create(course);
    }

    // ===== UPDATE =====
    @Override
    public Course updateCourse(String id, Course course) {

        Course existing = courseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        existing.setTitle(course.getTitle());
        existing.setStatus(course.getStatus()); // enum
        existing.setInstructorId(course.getInstructorId());

        return courseRepo.update(id, existing);
    }

    // ===== DELETE =====
    @Override
    public Course deleteCourseById(String id) {
        return courseRepo.deleteById(id);
    }
}
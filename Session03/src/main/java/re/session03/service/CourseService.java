package re.session03.service;

import re.session03.model.Course;

import java.util.List;

public interface CourseService {

    // ===== CRUD =====

    List<Course> getAllCourses();

    Course getCourseById(String id);

    Course createCourse(Course course);

    Course updateCourse(String id, Course course);

    Course deleteCourseById(String id);
}
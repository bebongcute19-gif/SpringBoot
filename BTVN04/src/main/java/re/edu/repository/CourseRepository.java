package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.entity.Course;
import re.edu.entity.CourseStatus;

public interface CourseRepository extends JpaRepository<Course,Long> {
    boolean existsByIdAndStatus(Long id, CourseStatus status);
}

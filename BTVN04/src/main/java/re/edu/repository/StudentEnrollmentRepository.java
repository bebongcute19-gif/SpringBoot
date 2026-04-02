package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import re.edu.entity.StudentEnrollment;

import java.util.List;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment,Long> {
    boolean existsByCourseIdAndStudentId(Long courseId, Long studentId);
    @Query("""
SELECT se FROM StudentEnrollment se
WHERE se.course.id = :courseId
AND (:name IS NULL OR se.student.name LIKE %:name%)
""")
    List<StudentEnrollment> searchStudents(Long courseId, String name);
    void deleteByCourseIdAndStudentId(Long courseId, Long studentId);
}

package re.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

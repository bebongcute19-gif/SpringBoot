package re.session03.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.session03.model.Course;
import re.session03.model.Instructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InstructorDetail {
    private Instructor instructor;
    private List<Course> courses;
}

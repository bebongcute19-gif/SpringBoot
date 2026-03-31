package re.session03.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.session03.model.Course;
import re.session03.model.Enrollment;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnrollmentDetail {
    private Enrollment enrollment;
    private Course course;
}

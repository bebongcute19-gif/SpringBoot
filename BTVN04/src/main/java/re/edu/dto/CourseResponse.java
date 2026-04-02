package re.edu.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.edu.entity.CourseStatus;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseResponse {
    private Long id;
    private String title;
    private CourseStatus status;
    private CourseInstructorResponse instructor;

}

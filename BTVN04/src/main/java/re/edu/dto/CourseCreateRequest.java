package re.edu.dto;

import lombok.*;
import re.edu.entity.CourseStatus;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseCreateRequest {
    private String title;
    private CourseStatus status;
    private Long instructorId;
}

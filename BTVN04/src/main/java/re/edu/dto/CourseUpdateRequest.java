package re.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.edu.entity.CourseStatus;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CourseUpdateRequest {

    private String title;
    private CourseStatus status;
    private Long instructorId;
}
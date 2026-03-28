package re.btvn02.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.btvn02.enums.Priority;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Task {
    private String id;
    private String title;
    private String description;
    private Priority priority;
    private String assignedTo;
}

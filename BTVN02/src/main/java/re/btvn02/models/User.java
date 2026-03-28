package re.btvn02.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.btvn02.enums.Role;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private String id;
    private String username;
    private String email;
    private Role role;
}

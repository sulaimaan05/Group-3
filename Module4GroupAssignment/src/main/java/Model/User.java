package Model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class User {
    //Instance variables:
    private int userId;
    private String username;
    private String email;
    private String password;
    private String role; //Role can be: 'viewer', 'creator', or 'moderator'.
}

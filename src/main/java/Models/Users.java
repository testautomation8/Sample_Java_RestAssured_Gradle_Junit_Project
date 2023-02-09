package Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Users {

    public int id;
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String phone;
    public int userStatus;

}

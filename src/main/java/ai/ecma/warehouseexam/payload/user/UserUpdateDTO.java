package ai.ecma.warehouseexam.payload.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    private Integer roleId;

    public UserUpdateDTO(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public UserUpdateDTO(Integer roleId) {
        this.roleId = roleId;
    }

}

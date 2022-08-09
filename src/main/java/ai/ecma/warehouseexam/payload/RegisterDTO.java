package ai.ecma.warehouseexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDTO {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String prePassword;

}
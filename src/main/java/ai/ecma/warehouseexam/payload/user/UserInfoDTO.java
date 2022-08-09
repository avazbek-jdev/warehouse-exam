package ai.ecma.warehouseexam.payload.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoDTO {

    private String firstName;

    private String lastname;

    private String phoneNumber;

    private String email;

    private Integer roleId;


}

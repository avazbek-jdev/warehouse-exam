package ai.ecma.warehouseexam.payload.role;

import ai.ecma.warehouseexam.enums.PermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleUpdateDTO {
    private String name;
    private String description;
    private List<PermissionEnum> permissionEnums;

}

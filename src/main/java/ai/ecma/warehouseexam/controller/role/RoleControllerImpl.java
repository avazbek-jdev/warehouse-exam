package ai.ecma.warehouseexam.controller.role;

import ai.ecma.warehouseexam.entity.Role;
import ai.ecma.warehouseexam.enums.PermissionEnum;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.role.ManageRoleUserDTO;
import ai.ecma.warehouseexam.payload.role.RoleAddDTO;
import ai.ecma.warehouseexam.payload.role.RoleInfoDTO;
import ai.ecma.warehouseexam.payload.role.RoleUpdateDTO;
import ai.ecma.warehouseexam.payload.user.UserInfoDTO;
import ai.ecma.warehouseexam.service.interfaces.RoleService;
import ai.ecma.warehouseexam.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController{

    private final RoleService roleService;
    private final UserService userService;

    @Override
    public ApiResult<List<RoleInfoDTO>> getAll(int page, int size) {
        return roleService.getAll(page,size);
    }

    @Override
    public ApiResult<RoleInfoDTO> getOne(Integer id) {
        return roleService.getOne(id);
    }

    @Override
    public ApiResult<RoleInfoDTO> add(RoleAddDTO roleAddDTO) {
        return roleService.add(roleAddDTO);
    }

    @Override
    public ApiResult<RoleInfoDTO> update(RoleUpdateDTO roleUpdateDTO, Integer id) {
        return roleService.update(roleUpdateDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return roleService.delete(id);
    }

    @Override
    public ApiResult<RoleInfoDTO> deletePermissionsFromRole(List<PermissionEnum> permissionEnums, Integer id) {
        return roleService.deletePermissionsFromRole(permissionEnums,id);
    }

    @Override
    public ApiResult<UserInfoDTO> manageRole(Integer id, ManageRoleUserDTO manageRoleInfoDTO) {
        Role role = roleService.getByIdOrElseThrow(manageRoleInfoDTO.getRoleId());
        return userService.manageRoleToUser(id,role);
    }
}
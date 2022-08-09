package ai.ecma.warehouseexam.controller.role;

import ai.ecma.warehouseexam.enums.PermissionEnum;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.role.ManageRoleUserDTO;
import ai.ecma.warehouseexam.payload.role.RoleAddDTO;
import ai.ecma.warehouseexam.payload.role.RoleInfoDTO;
import ai.ecma.warehouseexam.payload.role.RoleUpdateDTO;
import ai.ecma.warehouseexam.payload.user.UserInfoDTO;
import ai.ecma.warehouseexam.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(RoleController.ROLE_CONTROLLER_PATH)
public interface RoleController {
    String ROLE_CONTROLLER_PATH = AppConstant.BASE_PATH + "/role/";
    String ADD = "add";
    String UPDATE = "update";
    String DELETE = "delete";
    String GET = "all";
    String DELETE_PERMISSIONS = "delete-permission";
    String ADD_ROLE = "add-role";

    @PreAuthorize(value = "hasAuthority('VIEW_ROLES')")
    @GetMapping(GET)
    ApiResult<List<RoleInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('VIEW_ROLE')")
    @GetMapping(GET + "/{id}")
    ApiResult<RoleInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping(ADD)
    ApiResult<RoleInfoDTO> add(@RequestBody RoleAddDTO roleAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_ROLE')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<RoleInfoDTO> update(RoleUpdateDTO roleUpdateDTO, @PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('DELETE_ROLE')")
    @DeleteMapping(DELETE + "/{id}")
    String delete(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('DELETE_PERMISSIONS_ROLE')")
    @DeleteMapping(DELETE_PERMISSIONS + "/{id}")
    ApiResult<RoleInfoDTO> deletePermissionsFromRole(List<PermissionEnum> permissionEnums, @PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('MANAGE_ROLE_TO_USER')")
    @PostMapping(ADD_ROLE + "/{id}")
    ApiResult<UserInfoDTO> manageRole(@PathVariable Integer id, @RequestBody ManageRoleUserDTO manageRoleInfoDTO);


}

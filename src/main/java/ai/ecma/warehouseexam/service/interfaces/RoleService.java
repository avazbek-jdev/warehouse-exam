package ai.ecma.warehouseexam.service.interfaces;

import ai.ecma.warehouseexam.entity.Role;
import ai.ecma.warehouseexam.enums.PermissionEnum;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.role.RoleAddDTO;
import ai.ecma.warehouseexam.payload.role.RoleInfoDTO;
import ai.ecma.warehouseexam.payload.role.RoleUpdateDTO;

import java.util.List;

public interface RoleService {
    ApiResult<List<RoleInfoDTO>> getAll(int page, int size);

    ApiResult<RoleInfoDTO> add(RoleAddDTO roleAddDTO);

    ApiResult<RoleInfoDTO> update(RoleUpdateDTO roleUpdateDTO, Integer id);

    String delete(Integer id);

    ApiResult<RoleInfoDTO> deletePermissionsFromRole(List<PermissionEnum> permissionEnums,Integer roleId);

    ApiResult<RoleInfoDTO> getOne(Integer id);

    Role getByIdOrElseThrow(Integer roleId);
}

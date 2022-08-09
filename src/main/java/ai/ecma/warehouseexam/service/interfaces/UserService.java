package ai.ecma.warehouseexam.service.interfaces;

import ai.ecma.warehouseexam.entity.Role;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.user.UserInfoDTO;
import ai.ecma.warehouseexam.payload.user.UserUpdateDTO;


public interface UserService {

    ApiResult<UserInfoDTO> manageRoleToUser(Integer id, Role role);

    void setEnabledFalse(Integer id);

    ApiResult<UserInfoDTO> roleUser(UserUpdateDTO userUpdateDTO, Integer userId);

    String delete(Integer id);

    ApiResult<UserInfoDTO> update(UserUpdateDTO userUpdateDTO, Integer id);

    ApiResult<?> getOne(Integer id);

    ApiResult<?> getAll(int page, int size);


}
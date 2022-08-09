package ai.ecma.warehouseexam.controller.user;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.user.UserInfoDTO;
import ai.ecma.warehouseexam.payload.user.UserUpdateDTO;
import ai.ecma.warehouseexam.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ApiResult<?> getOne(Integer id) {
        return userService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return userService.getAll(page,size);
    }

    @Override
    public ApiResult<UserInfoDTO> roleUser(UserUpdateDTO userUpdateDTO, Integer id) {
        return userService.roleUser(userUpdateDTO,id);
    }

    @Override
    public ApiResult<UserInfoDTO> update(UserUpdateDTO userUpdateDTO, Integer id) {
        return userService.update(userUpdateDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return userService.delete(id);
    }
}

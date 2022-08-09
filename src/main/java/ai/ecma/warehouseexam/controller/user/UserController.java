package ai.ecma.warehouseexam.controller.user;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.user.UserInfoDTO;
import ai.ecma.warehouseexam.payload.user.UserUpdateDTO;
import ai.ecma.warehouseexam.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(UserController.USER_CONTROLLER_PATH)
public interface UserController {
    String USER_CONTROLLER_PATH = AppConstant.BASE_PATH + "/users/";

    String GET_ALL_PATH = "all";

    String ADD_USER_ROLE = "add-user-role";
    String UPDATE_USER_PATH = "update";
    String DELETE_USER_PATH = "delete";
    String GET_ONE = "get-one";



    @PreAuthorize(value = "hasAuthority('VIEW_USER')")
    @GetMapping(GET_ONE+"/{id}")
    ApiResult<?> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('VIEW_USER')")
    @GetMapping(GET_ALL_PATH)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('MANAGE_ROLE_TO_USER')")
    @PostMapping(ADD_USER_ROLE + "/{id}")
    ApiResult<UserInfoDTO> roleUser(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('EDIT_USER')")
    @PutMapping(UPDATE_USER_PATH + "/{id}")
    ApiResult<UserInfoDTO> update(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Integer id);
    @PreAuthorize(value = "hasAuthority('DELETE_USER')")
    @DeleteMapping(DELETE_USER_PATH + "/{id}")
    String delete(@PathVariable Integer id);

}

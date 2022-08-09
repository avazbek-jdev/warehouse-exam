package ai.ecma.warehouseexam.mapper;

import ai.ecma.warehouseexam.entity.User;
import ai.ecma.warehouseexam.payload.user.UserInfoDTO;
import ai.ecma.warehouseexam.payload.user.UserUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    void updateEntity(UserUpdateDTO userUpdateDTO, @MappingTarget User user);

    UserInfoDTO mapEntityToInfoDTO(User user);
}

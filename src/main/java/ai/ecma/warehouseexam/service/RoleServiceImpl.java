package ai.ecma.warehouseexam.service;

import ai.ecma.warehouseexam.entity.Role;
import ai.ecma.warehouseexam.enums.PermissionEnum;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.payload.role.RoleAddDTO;
import ai.ecma.warehouseexam.payload.role.RoleInfoDTO;
import ai.ecma.warehouseexam.payload.role.RoleUpdateDTO;
import ai.ecma.warehouseexam.repository.RoleRepository;
import ai.ecma.warehouseexam.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Override
    public ApiResult<List<RoleInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Role> rolePage = roleRepository.findAll(pageable);
        List<Role> roles = rolePage.getContent();
        List<RoleInfoDTO> roleInfoDTO = roles
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(roleInfoDTO);
    }

    @Override
    public ApiResult<RoleInfoDTO> add(RoleAddDTO roleAddDTO) {
        checkName(roleAddDTO.getName());
        Role role = new Role(
                roleAddDTO.getName(),
                roleAddDTO.getDescription(),
                roleAddDTO.getPermissionEnums()
        );
        roleRepository.save(role);
        return returnApiResult(role,true,"Success");
    }

    @Override
    public ApiResult<RoleInfoDTO> update(RoleUpdateDTO roleUpdateDTO, Integer id) {
        checkName(roleUpdateDTO.getName(),id);
        Role role = getByIdOrElseThrow(id);
        role.setName(roleUpdateDTO.getName());
        role.setDescription(roleUpdateDTO.getDescription());
        role.setPermissions(roleUpdateDTO.getPermissionEnums());
        roleRepository.save(role);
        return returnApiResult(role,true,"Success - 404");
    }

    @Override
    public String delete(Integer id) {
        Role role = getByIdOrElseThrow(id);
        roleRepository.delete(role);
        return "Successfully deleted!";
    }

    @Override
    public ApiResult<RoleInfoDTO> deletePermissionsFromRole(List<PermissionEnum> permissionEnums,Integer roleId) {
        for (PermissionEnum permissionEnum: permissionEnums) {
            roleRepository.deletePermissions(String.valueOf(permissionEnum),roleId);
        }
        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<RoleInfoDTO> getOne(Integer id) {
        Role role = getByIdOrElseThrow(id);
        RoleInfoDTO roleInfoDTO = entityToInfoDTO(role);
        return ApiResult.successResponse(roleInfoDTO);
    }


    private RoleInfoDTO entityToInfoDTO(Role role) {
        return new RoleInfoDTO(
                role.getName(),
                role.getDescription(),
                role.getPermissions()
        );
    }

    private void checkName(String name) {
        boolean exists = roleRepository.existsByName(name);
        if (exists) throw RestException.alreadyExist("Role");
    }

    private void checkName(String name, Integer id) {
        boolean exists = roleRepository.existsByNameAndIdNot(name,id);
        if (exists) throw RestException.alreadyExist("Role");
    }

    public Role getByIdOrElseThrow(Integer id) {
        return roleRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Warehouse")
        );
    }

    private ApiResult<RoleInfoDTO> returnApiResult(Role role, boolean success, String msg) {
        RoleInfoDTO roleInfoDTO = entityToInfoDTO(role);
        return new ApiResult<>(roleInfoDTO,success,msg);
    }

}
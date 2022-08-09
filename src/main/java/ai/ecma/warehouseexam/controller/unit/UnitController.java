package ai.ecma.warehouseexam.controller.unit;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.unit.UnitAddDTO;
import ai.ecma.warehouseexam.payload.unit.UnitInfoDTO;
import ai.ecma.warehouseexam.payload.unit.UnitUpdateDTO;
import ai.ecma.warehouseexam.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(UnitController.UNITCONTROLLER_PATH)
public interface UnitController {
    String UNITCONTROLLER_PATH = AppConstant.BASE_PATH + "/unit/";

    String GET_ALL_PATH = "all";
    String ADD_PATH = "add";
    String UPDATE_PATH = "update";
    String DELETE_PATH = "delete";

    @PreAuthorize(value = "hasAnyAuthority('VIEW_UNIT')")
    @GetMapping(GET_ALL_PATH)
    ApiResult<List<UnitInfoDTO>> getAll();  // name , status

    @PreAuthorize(value = "hasAnyAuthority('ADD_UNIT')")
    @PostMapping(ADD_PATH)
    ApiResult<UnitInfoDTO> add(@RequestBody UnitAddDTO unitAddDTO);

    @PreAuthorize(value = "hasAnyAuthority('EDIT_UNIT')")
    @PutMapping(UPDATE_PATH + "/{id}")
    ApiResult<UnitInfoDTO> update(@RequestBody UnitUpdateDTO unitUpdateDTO, @PathVariable Integer id);

    @PreAuthorize(value = "hasAnyAuthority('DELETE_UNIT')")
    @DeleteMapping(DELETE_PATH + "/{id}")
    String delete(@PathVariable Integer id);


}

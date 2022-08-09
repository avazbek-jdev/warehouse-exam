package ai.ecma.warehouseexam.controller.warehouse;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseAddDTO;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseInfoDTO;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseUpdateDTO;
import ai.ecma.warehouseexam.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(WarehouseController.WAREHOUSECONTROLLER_PATH)
public interface WarehouseController {

    String WAREHOUSECONTROLLER_PATH = AppConstant.BASE_PATH + "/warehouse/";
    String  GET_ALL_PATH = "all";
    String  GET_ONE = "one";

    String  ADD_PATH = "add";
    String  UPDATE_PATH = "update";
    String  DELETE_PATH = "delete";


    @PreAuthorize(value = "hasAuthority('VIEW_WAREHOUSES')")
    @GetMapping(GET_ALL_PATH)
    ApiResult<List<WarehouseInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('VIEW_WAREHOUSE')")
    @GetMapping(GET_ONE + "/{id}")
    ApiResult<WarehouseInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('ADD_WAREHOUSE')")
    @PostMapping(ADD_PATH)
    ApiResult<WarehouseInfoDTO> add(@RequestBody WarehouseAddDTO warehouseAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_WAREHOUSE')")
    @PutMapping(UPDATE_PATH + "/{id}")
    ApiResult<WarehouseInfoDTO> update(@RequestBody WarehouseUpdateDTO warehouseUpdateDTO, @PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('DELETE_WAREHOUSE')")
    @DeleteMapping(DELETE_PATH + "/{id}")
    String delete(@PathVariable Integer id);
}

package ai.ecma.warehouseexam.controller.outcomeWarehouse;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.outcomeWarehouse.OutcomeWarehouseAddDTO;
import ai.ecma.warehouseexam.payload.outcomeWarehouse.OutcomeWarehouseInfoDTO;
import ai.ecma.warehouseexam.payload.outcomeWarehouse.OutcomeWarehouseUpdateDTO;
import ai.ecma.warehouseexam.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(OutcomeWarehouseController.OUTCOME_WAREHOUSE_CONTROLLER_PATH)
public interface OutcomeWarehouseController {

    String OUTCOME_WAREHOUSE_CONTROLLER_PATH = AppConstant.BASE_PATH + "/outcome-warehouse/";
    String ADD = "add";
    String UPDATE = "update";
    String VIEW = "view";

    @PreAuthorize(value = "hasAuthority('VIEW_OUTCOME_WAREHOUSES')")
    @GetMapping(VIEW)
    ApiResult<List<OutcomeWarehouseInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('VIEW_OUTCOME_WAREHOUSE')")
    @GetMapping(VIEW + "/{id}")
    ApiResult<OutcomeWarehouseInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('ADD_OUTCOME_WAREHOUSE')")
    @PostMapping(ADD)
    ApiResult<OutcomeWarehouseInfoDTO> add(@RequestBody OutcomeWarehouseAddDTO outcomeWarehouseAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_OUTCOME_WAREHOUSE')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<OutcomeWarehouseInfoDTO> update(OutcomeWarehouseUpdateDTO outcomeWarehouseUpdateDTO, @PathVariable Integer id);


}

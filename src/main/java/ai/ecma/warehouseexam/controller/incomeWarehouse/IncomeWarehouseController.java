package ai.ecma.warehouseexam.controller.incomeWarehouse;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.incomeWarehouse.IncomeWarehouseAddDTO;
import ai.ecma.warehouseexam.payload.incomeWarehouse.IncomeWarehouseInfoDTO;
import ai.ecma.warehouseexam.payload.incomeWarehouse.IncomeWarehouseUpdateDTO;
import ai.ecma.warehouseexam.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(IncomeWarehouseController.INCOME_WAREHOUSE_CONTROLLER_PATH)
public interface IncomeWarehouseController {

    String INCOME_WAREHOUSE_CONTROLLER_PATH = AppConstant.BASE_PATH + "/income-warehouse/";

    String ADD = "add";
    String UPDATE = "update";
    String VIEW = "view";

    @PreAuthorize("hasAuthority('VIEW_INCOMEWAREHOUSE')")
    @GetMapping(VIEW + "/{id}")
    ApiResult<IncomeWarehouseInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize("hasAuthority('VIEW_INCOMEWAREHOUSES')")
    @GetMapping(VIEW)
    ApiResult<List<IncomeWarehouseInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size);

    @PreAuthorize("hasAuthority('ADD_INCOMEWAREHOUSE')")
    @PostMapping(ADD)
    ApiResult<IncomeWarehouseInfoDTO> add(@RequestBody IncomeWarehouseAddDTO incomeWarehouseAddDTO);

    @PreAuthorize("hasAuthority('UPDATE_INCOMEWAREHOUSE')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<IncomeWarehouseInfoDTO> update(IncomeWarehouseUpdateDTO incomeWarehouseUpdateDTO, @PathVariable Integer id);


}

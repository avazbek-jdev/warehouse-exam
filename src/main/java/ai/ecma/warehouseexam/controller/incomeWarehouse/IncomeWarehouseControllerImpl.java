package ai.ecma.warehouseexam.controller.incomeWarehouse;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.incomeWarehouse.IncomeWarehouseAddDTO;
import ai.ecma.warehouseexam.payload.incomeWarehouse.IncomeWarehouseInfoDTO;
import ai.ecma.warehouseexam.payload.incomeWarehouse.IncomeWarehouseUpdateDTO;
import ai.ecma.warehouseexam.service.interfaces.IncomeWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IncomeWarehouseControllerImpl implements IncomeWarehouseController{

    private final IncomeWarehouseService incomeWarehouseService;

    @Override
    public ApiResult<IncomeWarehouseInfoDTO> getOne(Integer id) {
        return incomeWarehouseService.getOne(id);
    }

    @Override
    public ApiResult<List<IncomeWarehouseInfoDTO>> getAll(int page, int size) {
        return incomeWarehouseService.getAll(page,size);
    }

    @Override
    public ApiResult<IncomeWarehouseInfoDTO> add(IncomeWarehouseAddDTO incomeWarehouseAddDTO) {
        return incomeWarehouseService.add(incomeWarehouseAddDTO);
    }

    @Override
    public ApiResult<IncomeWarehouseInfoDTO> update(IncomeWarehouseUpdateDTO incomeWarehouseUpdateDTO, Integer id) {
        return incomeWarehouseService.update(incomeWarehouseUpdateDTO,id);
    }

}

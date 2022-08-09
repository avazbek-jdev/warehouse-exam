package ai.ecma.warehouseexam.service.interfaces;

import ai.ecma.warehouseexam.entity.IncomeWarehouse;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.incomeWarehouse.IncomeWarehouseAddDTO;
import ai.ecma.warehouseexam.payload.incomeWarehouse.IncomeWarehouseInfoDTO;
import ai.ecma.warehouseexam.payload.incomeWarehouse.IncomeWarehouseUpdateDTO;

import java.util.List;

public interface IncomeWarehouseService {

    ApiResult<IncomeWarehouseInfoDTO> getOne(Integer id);

    ApiResult<List<IncomeWarehouseInfoDTO>> getAll(int page, int size);

    ApiResult<IncomeWarehouseInfoDTO> update(IncomeWarehouseUpdateDTO incomeWarehouseUpdateDTO, Integer id);

    ApiResult<IncomeWarehouseInfoDTO> add(IncomeWarehouseAddDTO incomeWarehouseAddDTO);

    IncomeWarehouse getByIdOrElseThrow(Integer incomeWarehouseId);
}

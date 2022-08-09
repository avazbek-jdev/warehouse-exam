package ai.ecma.warehouseexam.service.interfaces;

import ai.ecma.warehouseexam.entity.OutcomeWarehouse;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.outcomeWarehouse.OutcomeWarehouseAddDTO;
import ai.ecma.warehouseexam.payload.outcomeWarehouse.OutcomeWarehouseInfoDTO;
import ai.ecma.warehouseexam.payload.outcomeWarehouse.OutcomeWarehouseUpdateDTO;

import java.util.List;

public interface OutcomeWarehouseService {

    ApiResult<OutcomeWarehouseInfoDTO> getOne(Integer id);

    ApiResult<List<OutcomeWarehouseInfoDTO>> getAll(int page, int size);

    ApiResult<OutcomeWarehouseInfoDTO> add(OutcomeWarehouseAddDTO outcomeWarehouseAddDTO);

    ApiResult<OutcomeWarehouseInfoDTO> update(OutcomeWarehouseUpdateDTO outcomeWarehouseUpdateDTO, Integer id);

    OutcomeWarehouse getByIdOrElseThrow(Integer outcomeWarehouseId);
}

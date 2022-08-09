package ai.ecma.warehouseexam.service.interfaces;

import ai.ecma.warehouseexam.entity.Warehouse;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseAddDTO;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseInfoDTO;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseUpdateDTO;

import java.util.List;

public interface WarehouseService {

    ApiResult<List<WarehouseInfoDTO>> getAll(int page, int size);

    ApiResult<WarehouseInfoDTO> add(WarehouseAddDTO warehouseAddDTO);

    ApiResult<WarehouseInfoDTO> update(WarehouseUpdateDTO warehouseUpdateDTO, Integer id);

    String delete(Integer id);

    Warehouse getByIdOrElseThrow(Integer id);

    ApiResult<WarehouseInfoDTO> getOne(Integer id);

}

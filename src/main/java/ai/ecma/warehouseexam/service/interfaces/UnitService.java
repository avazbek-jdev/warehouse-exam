package ai.ecma.warehouseexam.service.interfaces;

import ai.ecma.warehouseexam.entity.Unit;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.unit.UnitAddDTO;
import ai.ecma.warehouseexam.payload.unit.UnitInfoDTO;
import ai.ecma.warehouseexam.payload.unit.UnitUpdateDTO;

import java.util.List;


public interface UnitService {

    ApiResult<List<UnitInfoDTO>> getAll();


    ApiResult<UnitInfoDTO> add(UnitAddDTO unitAddDTO);

    ApiResult<UnitInfoDTO> update(UnitUpdateDTO unitUpdateDTO, Integer id);

    String delete(Integer id);

    Unit getByIdOrElseThrow(Integer id);

    ApiResult<UnitInfoDTO> getOne(Integer id);
}

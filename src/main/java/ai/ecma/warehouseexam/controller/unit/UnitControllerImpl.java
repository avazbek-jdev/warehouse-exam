package ai.ecma.warehouseexam.controller.unit;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.unit.UnitAddDTO;
import ai.ecma.warehouseexam.payload.unit.UnitInfoDTO;
import ai.ecma.warehouseexam.payload.unit.UnitUpdateDTO;
import ai.ecma.warehouseexam.service.interfaces.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UnitControllerImpl implements UnitController{

    private final UnitService unitService;

    @Override
    public ApiResult<List<UnitInfoDTO>> getAll() {
        return unitService.getAll();
    }

    @Override
    public ApiResult<UnitInfoDTO> add(UnitAddDTO unitAddDTO) {
        return unitService.add(unitAddDTO);
    }

    @Override
    public ApiResult<UnitInfoDTO> update(UnitUpdateDTO unitUpdateDTO, Integer id) {
        return unitService.update(unitUpdateDTO, id);
    }

    @Override
    public String delete(Integer id) {
        return unitService.delete(id);
    }
}

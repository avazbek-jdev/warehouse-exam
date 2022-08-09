package ai.ecma.warehouseexam.controller.outcomeWarehouse;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.outcomeWarehouse.OutcomeWarehouseAddDTO;
import ai.ecma.warehouseexam.payload.outcomeWarehouse.OutcomeWarehouseInfoDTO;
import ai.ecma.warehouseexam.payload.outcomeWarehouse.OutcomeWarehouseUpdateDTO;
import ai.ecma.warehouseexam.service.interfaces.OutcomeWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OutcomeWarehouseControllerImpl implements OutcomeWarehouseController{

    private final OutcomeWarehouseService outcomeWarehouseService;

    @Override
    public ApiResult<List<OutcomeWarehouseInfoDTO>> getAll(int page, int size) {
        return outcomeWarehouseService.getAll(page,size);
    }

    @Override
    public ApiResult<OutcomeWarehouseInfoDTO> getOne(Integer id) {
        return outcomeWarehouseService.getOne(id);
    }

    @Override
    public ApiResult<OutcomeWarehouseInfoDTO> add(OutcomeWarehouseAddDTO outcomeWarehouseAddDTO) {
        return outcomeWarehouseService.add(outcomeWarehouseAddDTO);
    }

    @Override
    public ApiResult<OutcomeWarehouseInfoDTO> update(OutcomeWarehouseUpdateDTO outcomeWarehouseUpdateDTO, Integer id) {
        return outcomeWarehouseService.update(outcomeWarehouseUpdateDTO,id);
    }
}

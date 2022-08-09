package ai.ecma.warehouseexam.controller.warehouse;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseAddDTO;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseInfoDTO;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseUpdateDTO;
import ai.ecma.warehouseexam.service.interfaces.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class WarehouseControllerImpl implements WarehouseController{

    private final WarehouseService warehouseService;

    @Override
    public ApiResult<List<WarehouseInfoDTO>> getAll(int page, int size) {
        return warehouseService.getAll(page,size);
    }

    @Override
    public ApiResult<WarehouseInfoDTO> getOne(Integer id) {
        return warehouseService.getOne(id);
    }

    @Override
    public ApiResult<WarehouseInfoDTO> add(WarehouseAddDTO warehouseAddDTO) {
        return warehouseService.add(warehouseAddDTO);
    }

    @Override
    public ApiResult<WarehouseInfoDTO> update(WarehouseUpdateDTO warehouseUpdateDTO, Integer id) {
        return warehouseService.update(warehouseUpdateDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return warehouseService.delete(id);
    }
}

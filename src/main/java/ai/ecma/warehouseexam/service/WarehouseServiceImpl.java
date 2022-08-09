package ai.ecma.warehouseexam.service;

import ai.ecma.warehouseexam.entity.Warehouse;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.mapper.WarehouseMapper;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseAddDTO;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseInfoDTO;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseUpdateDTO;
import ai.ecma.warehouseexam.repository.WarehouseRepository;
import ai.ecma.warehouseexam.service.interfaces.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService{

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    @Override
    public ApiResult<List<WarehouseInfoDTO>> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);

        Page<Warehouse> warehousePage = warehouseRepository.findAll(pageable);

        List<Warehouse> warehouses = warehousePage.getContent();

        List<WarehouseInfoDTO> warehouseInfoDTOS = warehouses
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());


        return ApiResult.successResponse(warehouseInfoDTOS);
    }

    @Override
    public ApiResult<WarehouseInfoDTO> add(WarehouseAddDTO warehouseAddDTO) {
        checkName(warehouseAddDTO.getName());
        Warehouse warehouse = warehouseMapper.mapAddDTOToEntity(warehouseAddDTO);
        warehouseRepository.save(warehouse);
        return returnApiResult(warehouse,true,"Success");
    }

    @Override
    public ApiResult<WarehouseInfoDTO> update(WarehouseUpdateDTO warehouseUpdateDTO, Integer id) {
        checkName(warehouseUpdateDTO.getName(),id);
        Warehouse warehouse = getByIdOrElseThrow(id);
        warehouseMapper.updateEntity(warehouseUpdateDTO,warehouse);
        warehouseRepository.save(warehouse);
        return returnApiResult(warehouse,true,"Success");
    }

    @Override
    public String delete(Integer id) {
        Warehouse warehouse = getByIdOrElseThrow(id);
        warehouseRepository.delete(warehouse);
        return "Success";
    }

    private WarehouseInfoDTO entityToInfoDTO(Warehouse warehouse) {
        return warehouseMapper.mapEntityToInfoDTO(warehouse);
    }

    private ApiResult<WarehouseInfoDTO> returnApiResult(Warehouse warehouse,boolean success,String msg) {
        WarehouseInfoDTO warehouseInfoDTO = entityToInfoDTO(warehouse);
        return new ApiResult<>(warehouseInfoDTO,true,msg);
    }

    private void checkName(String name) {
        boolean exists = warehouseRepository.existsByNameAndActiveTrue(name);
        if (exists) throw RestException.alreadyExist("Warehouse");
    }

    @Override
    public Warehouse getByIdOrElseThrow(Integer id) {
        return warehouseRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Warehouse")
        );
    }

    @Override
    public ApiResult<WarehouseInfoDTO> getOne(Integer id) {
        Warehouse warehouse = getByIdOrElseThrow(id);
        WarehouseInfoDTO warehouseInfoDTO = entityToInfoDTO(warehouse);
        return ApiResult.successResponse(warehouseInfoDTO);
    }

    private void checkName(String name, Integer id) {
        boolean exists = warehouseRepository.existsByNameAndIdNotAndActiveTrue(name,id);
        if (exists) throw RestException.alreadyExist("Warehouse");
    }

}

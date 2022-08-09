package ai.ecma.warehouseexam.service;

import ai.ecma.warehouseexam.entity.Unit;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.mapper.UnitMapper;
import ai.ecma.warehouseexam.payload.unit.UnitAddDTO;
import ai.ecma.warehouseexam.payload.unit.UnitInfoDTO;
import ai.ecma.warehouseexam.payload.unit.UnitUpdateDTO;
import ai.ecma.warehouseexam.repository.UnitRepository;
import ai.ecma.warehouseexam.service.interfaces.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {
    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;

    @Override
    public ApiResult<UnitInfoDTO> getOne(Integer id) {
        Unit unit = getByIdOrElseThrow(id);
        UnitInfoDTO unitInfoDTO = entityToInfoDTO(unit);
        return ApiResult.successResponse(unitInfoDTO);
    }


    @Override
    public ApiResult<List<UnitInfoDTO>> getAll() {
        List<Unit> unitList = unitRepository.findAll();
        List<UnitInfoDTO> unitInfoDTOList = unitList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(unitInfoDTOList);
    }

    @Override
    public ApiResult<UnitInfoDTO> add(UnitAddDTO unitAddDTO) {
        checkExistingName(unitAddDTO.getName());
        Unit unit = unitMapper.mapAddDTOToEntity(unitAddDTO);
        unitRepository.save(unit);

        return returnApiResult(unit, true, "success");
    }

    @Override
    public ApiResult<UnitInfoDTO> update(UnitUpdateDTO unitUpdateDTO, Integer id) {
        checkExistingName(unitUpdateDTO.getName(), id);
        Unit unit = getByIdOrElseThrow(id);

        unitMapper.update(unitUpdateDTO, unit);

        unitRepository.save(unit);

        return returnApiResult(unit, true, "success");
    }

    @Override
    public String delete(Integer id) {
        Unit unit = getByIdOrElseThrow(id);
        unitRepository.delete(unit);
        return "deleted";
    }


    private UnitInfoDTO entityToInfoDTO(Unit unit) {
       return unitMapper.entityToInfoDTO(unit);
    }

    public Unit getByIdOrElseThrow(Integer unitId) {
        return unitRepository.findById(unitId).orElseThrow(
                () -> RestException.notFound("Unit")
        );
    }



    private ApiResult<UnitInfoDTO> returnApiResult(Unit unit, boolean success, String msg) {
        UnitInfoDTO unitInfoDTO = entityToInfoDTO(unit);
        return new ApiResult<>(unitInfoDTO, true, msg);
    }

    private void checkExistingName(String name, Integer id) {
        boolean exists = unitRepository.existsByNameAndIdAndActiveTrue(name, id);
        if (exists) throw RestException.alreadyExist("Unit");
//        Optional<Unit> optionalUnit = unitRepository.findById(id);
//        if (optionalUnit.isEmpty()) throw RestException.notFound("Unit");
    }

    private void checkExistingName(String name) {
        boolean exist = unitRepository.existsByNameAndIdNot(name);
        if (exist) throw RestException.alreadyExist("Unit");
    }


}

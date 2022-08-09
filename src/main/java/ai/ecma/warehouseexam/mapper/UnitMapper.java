package ai.ecma.warehouseexam.mapper;

import ai.ecma.warehouseexam.entity.Unit;
import ai.ecma.warehouseexam.payload.unit.UnitAddDTO;
import ai.ecma.warehouseexam.payload.unit.UnitInfoDTO;
import ai.ecma.warehouseexam.payload.unit.UnitUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UnitMapper {

    UnitInfoDTO entityToInfoDTO(Unit unit);

    Unit mapAddDTOToEntity(UnitAddDTO unitAddDTO);

    void update(UnitUpdateDTO unitUpdateDTO, @MappingTarget Unit unit);
}

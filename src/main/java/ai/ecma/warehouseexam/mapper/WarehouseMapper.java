package ai.ecma.warehouseexam.mapper;

import ai.ecma.warehouseexam.entity.Warehouse;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseAddDTO;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseInfoDTO;
import ai.ecma.warehouseexam.payload.warehouse.WarehouseUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    void updateEntity(WarehouseUpdateDTO warehouseUpdateDTO, @MappingTarget Warehouse warehouse);

    Warehouse mapAddDTOToEntity(WarehouseAddDTO warehouseAddDTO);

    WarehouseInfoDTO mapEntityToInfoDTO(Warehouse warehouse);

}

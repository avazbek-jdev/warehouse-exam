package ai.ecma.warehouseexam.mapper;

import ai.ecma.warehouseexam.entity.Supplier;
import ai.ecma.warehouseexam.payload.supplier.SupplierAddDTO;
import ai.ecma.warehouseexam.payload.supplier.SupplierInfoDTO;
import ai.ecma.warehouseexam.payload.supplier.SupplierUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierInfoDTO entityToInfoDTO(Supplier supplier);

    void update(SupplierUpdateDTO supplierUpdateDTO, @MappingTarget Supplier supplier);

    Supplier insertAddDTO(SupplierAddDTO supplierAddDTO);

}

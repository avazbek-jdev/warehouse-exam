package ai.ecma.warehouseexam.service.interfaces;

import ai.ecma.warehouseexam.entity.Supplier;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.supplier.SupplierAddDTO;
import ai.ecma.warehouseexam.payload.supplier.SupplierInfoDTO;
import ai.ecma.warehouseexam.payload.supplier.SupplierUpdateDTO;

import java.util.List;

public interface SupplierService {

    ApiResult<SupplierInfoDTO> getOne(Integer id);

    ApiResult<List<SupplierInfoDTO>> getAll(int page, int size);

    ApiResult<SupplierInfoDTO> add(SupplierAddDTO supplierAddDTO);

    ApiResult<SupplierInfoDTO> update(SupplierUpdateDTO supplierUpdateDTO, Integer id);

    String delete(Integer id);

    Supplier getByIdOrElseThrow(Integer id);

}

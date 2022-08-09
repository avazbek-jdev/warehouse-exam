package ai.ecma.warehouseexam.controller.supplier;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.supplier.SupplierAddDTO;
import ai.ecma.warehouseexam.payload.supplier.SupplierInfoDTO;
import ai.ecma.warehouseexam.payload.supplier.SupplierUpdateDTO;
import ai.ecma.warehouseexam.service.interfaces.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SupplierControllerImpl implements SupplierController{

    private final SupplierService supplierService;

    @Override
    public ApiResult<SupplierInfoDTO> getOne(Integer id) {
        return supplierService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return supplierService.getAll(page, size);
    }

    @Override
    public ApiResult<SupplierInfoDTO> add(SupplierAddDTO supplierAddDTO) {
        return supplierService.add(supplierAddDTO);
    }

    @Override
    public ApiResult<SupplierInfoDTO> update(SupplierUpdateDTO supplierUpdateDTO, Integer id) {
        return supplierService.update(supplierUpdateDTO, id);
    }

    @Override
    public String delete(Integer id) {
        return supplierService.delete(id);
    }
}

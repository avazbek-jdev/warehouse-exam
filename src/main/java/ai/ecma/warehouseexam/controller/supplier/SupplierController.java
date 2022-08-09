package ai.ecma.warehouseexam.controller.supplier;


import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.supplier.SupplierAddDTO;
import ai.ecma.warehouseexam.payload.supplier.SupplierInfoDTO;
import ai.ecma.warehouseexam.payload.supplier.SupplierUpdateDTO;
import ai.ecma.warehouseexam.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(SupplierController.SUPPLIERCONTROLLER_PATH)
public interface SupplierController {
    String SUPPLIERCONTROLLER_PATH = AppConstant.BASE_PATH + "/supplier/";
    String ALL = "all";
    String ADD = "add";
    String UPDATE = "update";
    String DELETE = "delete";

    String GET_ONE = "get-one";

    @PreAuthorize(value = "hasAuthority('VIEW_SUPPLIER')")
    @GetMapping(GET_ONE)
    ApiResult<SupplierInfoDTO> getOne(Integer id);

    @PreAuthorize(value = "hasAuthority('VIEW_SUPPLIER')")
    @GetMapping(ALL)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('ADD_SUPPLIER')")
    @PostMapping(ADD)
    ApiResult<SupplierInfoDTO> add(@RequestBody SupplierAddDTO supplierAddDTO);

    @PreAuthorize(value = "hasAuthority('EDIT_SUPPLIER')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<SupplierInfoDTO> update(@RequestBody SupplierUpdateDTO supplierUpdateDTO, @PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('DELETE_SUPPLIER')")
    @DeleteMapping(DELETE + "/{id}")
    String delete(@PathVariable Integer id);
}

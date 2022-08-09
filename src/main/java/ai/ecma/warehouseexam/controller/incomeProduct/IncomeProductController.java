package ai.ecma.warehouseexam.controller.incomeProduct;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.incomeProduct.IncomeProductAddDTO;
import ai.ecma.warehouseexam.payload.incomeProduct.IncomeProductInfoDTO;
import ai.ecma.warehouseexam.payload.incomeProduct.IncomeProductUpdateDTO;
import ai.ecma.warehouseexam.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(IncomeProductController.INCOME_PRODUCT_CONTROLLER_PATH)
public interface IncomeProductController {

    String INCOME_PRODUCT_CONTROLLER_PATH = AppConstant.BASE_PATH + "/income-product";
    String GET_ALL_PATH = "all";
    String ADD = "add";
    String UPDATE = "update";

    String GET_ONE = "get-one";

    @PreAuthorize(value = "hasAuthority('VIEW_INCOME_PRODUCT')")
    @GetMapping(GET_ONE+ "/{id}")
    ApiResult<IncomeProductInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('VIEW_INCOME_PRODUCTS')")
    @GetMapping(GET_ALL_PATH)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);


    // ApiResult<List<IncomeProductInfoDTO>> getAll();


    @PreAuthorize(value = "hasAuthority('ADD_INCOME_PRODUCT')")
    @PostMapping(ADD)
    ApiResult<IncomeProductInfoDTO> add(@RequestBody IncomeProductAddDTO incomeProductAddDTO);


    @PreAuthorize(value = "hasAuthority('EDIT_INCOME_PRODUCT')")
    @PutMapping(UPDATE+ "/{id}")
    ApiResult<IncomeProductInfoDTO> update(@RequestBody IncomeProductUpdateDTO incomeProductUpdateDTO,
                                           @PathVariable Integer id);

}

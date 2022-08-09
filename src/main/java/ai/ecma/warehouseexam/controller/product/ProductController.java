package ai.ecma.warehouseexam.controller.product;


import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.product.ProductAddDTO;
import ai.ecma.warehouseexam.payload.product.ProductInfoDTO;
import ai.ecma.warehouseexam.payload.product.ProductUpdateDTO;
import ai.ecma.warehouseexam.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ProductController.PRODUCTCONTROLLER_PATH)
public interface ProductController {
    String PRODUCTCONTROLLER_PATH = AppConstant.BASE_PATH + "/product/";

    String GET_ALL_PATH = "all";
    String ADD_PATH = "add";
    String UPDATE_PATH = "/{id}";
    String DELETE_PATH = "/{id}";

    @PreAuthorize(value = "hasAuthority('VIEW_PRODUCTS')")
    @GetMapping(GET_ALL_PATH)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping(ADD_PATH)
    ApiResult<ProductInfoDTO> add(@RequestBody ProductAddDTO productAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_PRODUCT')")
    @PutMapping(UPDATE_PATH + "/{id}")
    ApiResult<ProductInfoDTO> update(@RequestBody ProductUpdateDTO productUpdateDTO, @PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(DELETE_PATH + "/{id}")
    String delete(@PathVariable Integer id);

}

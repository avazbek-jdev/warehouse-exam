package ai.ecma.warehouseexam.controller.product;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.product.ProductAddDTO;
import ai.ecma.warehouseexam.payload.product.ProductInfoDTO;
import ai.ecma.warehouseexam.payload.product.ProductUpdateDTO;
import ai.ecma.warehouseexam.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;


    @Override
    public ApiResult<?> getAll(int page, int size) {
        return productService.getAll(page, size);
    }

    @Override
    public ApiResult<ProductInfoDTO> add(ProductAddDTO productAddDTO) {
        return productService.add(productAddDTO);
    }

    @Override
    public ApiResult<ProductInfoDTO> update(ProductUpdateDTO productUpdateDTO, Integer id) {
        return productService.update(productUpdateDTO, id);
    }

    @Override
    public String delete(Integer id) {
        return productService.delete(id);
    }
}

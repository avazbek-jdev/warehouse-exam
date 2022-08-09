package ai.ecma.warehouseexam.service.interfaces;

import ai.ecma.warehouseexam.entity.Product;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.product.ProductAddDTO;
import ai.ecma.warehouseexam.payload.product.ProductInfoDTO;
import ai.ecma.warehouseexam.payload.product.ProductUpdateDTO;

public interface ProductService {

    ApiResult<?> getAll(int page, int size);

    ApiResult<ProductInfoDTO> add(ProductAddDTO productAddDTO);

    ApiResult<ProductInfoDTO> update(ProductUpdateDTO productUpdateDTO, Integer id);

    String delete(Integer id);

    Product getByIdOrElseThrow(Integer id);

    ApiResult<ProductInfoDTO> getOne(Integer id);


}

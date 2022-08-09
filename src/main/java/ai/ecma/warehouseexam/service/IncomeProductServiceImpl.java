package ai.ecma.warehouseexam.service;

import ai.ecma.warehouseexam.entity.IncomeProduct;
import ai.ecma.warehouseexam.entity.IncomeWarehouse;
import ai.ecma.warehouseexam.entity.Product;
import ai.ecma.warehouseexam.entity.Unit;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.payload.incomeProduct.IncomeProductAddDTO;
import ai.ecma.warehouseexam.payload.incomeProduct.IncomeProductInfoDTO;
import ai.ecma.warehouseexam.payload.incomeProduct.IncomeProductUpdateDTO;
import ai.ecma.warehouseexam.repository.IncomeProductRepository;
import ai.ecma.warehouseexam.service.interfaces.IncomeProductService;
import ai.ecma.warehouseexam.service.interfaces.ProductService;
import ai.ecma.warehouseexam.service.interfaces.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeProductServiceImpl implements IncomeProductService {

    private final ProductService productService;
    private final IncomeWarehouseServiceImpl incomeWarehouseService;
    private final UnitService unitService;
    private final IncomeProductRepository incomeProductRepository;

    public static List<IncomeProduct> incomeProductList=new ArrayList<>();

    @Override
    public ApiResult<IncomeProductInfoDTO> getOne(Integer id) {
        IncomeProduct incomeProduct = getByIdOrElseThrow(id);
        IncomeProductInfoDTO incomeProductInfoDTO = entityToInfoDTO(incomeProduct);
        return ApiResult.successResponse(incomeProductInfoDTO);
    }

//    @Override
//    public ApiResult<List<IncomeProductInfoDTO>> getAll() {
//        List<IncomeProduct> incomeProductList = incomeProductRepository.findAll();
//        List<IncomeProductInfoDTO> incomeProductInfoDTOS = incomeProductList
//                .stream()
//                .map(this::entityToInfoDTO)
//                .collect(Collectors.toList());
//        return ApiResult.successResponse(incomeProductInfoDTOS);
//    }

    @Override
    public ApiResult<List<IncomeProductInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<IncomeProduct> incomeProductPage = incomeProductRepository.findAll(pageable);
        List<IncomeProduct> incomeProductList = incomeProductPage.getContent();
        List<IncomeProductInfoDTO> incomeProductInfoDTOS = incomeProductList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());

        return ApiResult.successResponse(incomeProductInfoDTOS);
    }

    @Override
    public ApiResult<IncomeProductInfoDTO> add(IncomeProductAddDTO incomeProductAddDTO) {
        Unit unit = unitService.getByIdOrElseThrow(incomeProductAddDTO.getUnitId());
        Product product = productService.getByIdOrElseThrow(incomeProductAddDTO.getProductId());
        IncomeWarehouse incomeWarehouse = incomeWarehouseService.getByIdOrElseThrow(incomeProductAddDTO.getWarehouseId());
        IncomeProduct incomeProduct = new IncomeProduct(
                product,
                incomeWarehouse,
                unit,
                incomeProductAddDTO.getUnitAmount(),
                incomeProductAddDTO.getPrice(),
                incomeProductAddDTO.getExpirationDate()
        );
        incomeProductList.add(incomeProduct);
        incomeProductRepository.save(incomeProduct);
        return ApiResult.successResponse(entityToInfoDTO(incomeProduct));
    }

    @Override
    public ApiResult<IncomeProductInfoDTO> update(IncomeProductUpdateDTO incomeProductUpdateDTO, Integer id) {
        IncomeProduct incomeProduct = getByIdOrElseThrow(id);
        Product product = productService.getByIdOrElseThrow(incomeProductUpdateDTO.getProductId());
        Unit unit = unitService.getByIdOrElseThrow(incomeProductUpdateDTO.getUnitId());
        incomeProduct.setProduct(product);
        incomeProduct.setUnit(unit);
        incomeProduct.setPrice(incomeProductUpdateDTO.getPrice());
        incomeProduct.setUnitAmount(incomeProductUpdateDTO.getUnitAmount());
        incomeProductRepository.save(incomeProduct);
        return ApiResult.successResponse(entityToInfoDTO(incomeProduct));
    }

    public IncomeProduct getByIdOrElseThrow(Integer id) {
        return incomeProductRepository.findById(id).orElseThrow(
                () -> RestException.notFound("IncomeProduct")
        );
    }

    public IncomeProductInfoDTO entityToInfoDTO(IncomeProduct incomeProduct) {
        return new IncomeProductInfoDTO(
                incomeProduct.getId(),
                incomeProduct.getIncomeWarehouse().getId(),
                incomeProduct.getUnit().getId(),
                incomeProduct.getUnitAmount(),
                incomeProduct.getPrice(),
                incomeProduct.getExpiredDate()
        );
    }
}

package ai.ecma.warehouseexam.service;

import ai.ecma.warehouseexam.entity.OutcomeProduct;
import ai.ecma.warehouseexam.entity.OutcomeWarehouse;
import ai.ecma.warehouseexam.entity.Product;
import ai.ecma.warehouseexam.entity.Unit;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.payload.outcomeProduct.OutcomeProductAddDTO;
import ai.ecma.warehouseexam.payload.outcomeProduct.OutcomeProductInfoDTO;
import ai.ecma.warehouseexam.payload.outcomeProduct.OutcomeProductUpdateDTO;
import ai.ecma.warehouseexam.repository.OutcomeProductRepository;
import ai.ecma.warehouseexam.service.interfaces.OutcomeProductService;
import ai.ecma.warehouseexam.service.interfaces.OutcomeWarehouseService;
import ai.ecma.warehouseexam.service.interfaces.ProductService;
import ai.ecma.warehouseexam.service.interfaces.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutcomeProductServiceImpl implements OutcomeProductService {

    private final OutcomeProductRepository outcomeProductRepository;
    private final ProductService productService;
    private final UnitService unitService;
    private final OutcomeWarehouseService outcomeWarehouseService;

    @Override
    public ApiResult<OutcomeProductInfoDTO> getOne(Integer id) {
        OutcomeProduct outcomeProduct = getByIdOrElseThrow(id);
        OutcomeProductInfoDTO outcomeProductInfoDTO = entityToInfoDTO(outcomeProduct);
        return ApiResult.successResponse(outcomeProductInfoDTO);
    }

    @Override
    public ApiResult<List<OutcomeProductInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<OutcomeProduct> outcomeProductPage = outcomeProductRepository.findAll(pageable);
        List<OutcomeProduct> outcomeProducts = outcomeProductPage.getContent();
        List<OutcomeProductInfoDTO> outcomeProductInfoDTOList = outcomeProducts
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(outcomeProductInfoDTOList);
    }

    @Override
    public ApiResult<OutcomeProductInfoDTO> add(OutcomeProductAddDTO outcomeProductAddDTO) {
        Unit unit = unitService.getByIdOrElseThrow(outcomeProductAddDTO.getUnitId());
        OutcomeWarehouse outcomeWarehouse = outcomeWarehouseService.getByIdOrElseThrow(outcomeProductAddDTO.getOutcomeWarehouseId());
        Product product = productService.getByIdOrElseThrow(outcomeProductAddDTO.getProductId());
        OutcomeProduct outcomeProduct = new OutcomeProduct(
                product,
                outcomeWarehouse,
                unit,
                outcomeProductAddDTO.getUnitAmount(),
                outcomeProductAddDTO.getPrice()
        );
        outcomeProductRepository.save(outcomeProduct);
        return ApiResult.successResponse(entityToInfoDTO(outcomeProduct));
    }

    @Override
    public ApiResult<OutcomeProductInfoDTO> update(OutcomeProductUpdateDTO outcomeProductUpdateDTO, Integer id) {
        Unit unit = unitService.getByIdOrElseThrow(outcomeProductUpdateDTO.getUnitId());
        OutcomeWarehouse outcomeWarehouse = outcomeWarehouseService.getByIdOrElseThrow(outcomeProductUpdateDTO.getOutcomeWarehouseId());
        Product product = productService.getByIdOrElseThrow(outcomeProductUpdateDTO.getProductId());
        OutcomeProduct outcomeProduct = getByIdOrElseThrow(id);
        outcomeProduct.setProduct(product);
        outcomeProduct.setOutcomeWarehouse(outcomeWarehouse);
        outcomeProduct.setUnit(unit);
        outcomeProduct.setPrice(outcomeProductUpdateDTO.getPrice());
        outcomeProduct.setUnitAmount(outcomeProductUpdateDTO.getUnitAmount());
        outcomeProductRepository.save(outcomeProduct);
        return ApiResult.successResponse(entityToInfoDTO(outcomeProduct));
    }

    private OutcomeProduct getByIdOrElseThrow(Integer id) {
        return outcomeProductRepository.findById(id).orElseThrow(
                () -> RestException.notFound("OutcomeProduct")
        );
    }

    private OutcomeProductInfoDTO entityToInfoDTO(OutcomeProduct outcomeProduct) {
        return new OutcomeProductInfoDTO(
                outcomeProduct.getOutcomeWarehouse().getId(),
                outcomeProduct.getProduct().getId(),
                outcomeProduct.getUnit().getId(),
                outcomeProduct.getUnitAmount(),
                outcomeProduct.getPrice()
        );
    }

}

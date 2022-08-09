package ai.ecma.warehouseexam.service;


import ai.ecma.warehouseexam.entity.Category;
import ai.ecma.warehouseexam.entity.Product;
import ai.ecma.warehouseexam.entity.Unit;
import ai.ecma.warehouseexam.entity.attachment.Attachment;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.payload.product.ProductAddDTO;
import ai.ecma.warehouseexam.payload.product.ProductInfoDTO;
import ai.ecma.warehouseexam.payload.product.ProductUpdateDTO;
import ai.ecma.warehouseexam.repository.ProductRepository;
import ai.ecma.warehouseexam.service.interfaces.AttachmentService;
import ai.ecma.warehouseexam.service.interfaces.CategoryService;
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
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final UnitService unitService;
    private final AttachmentService attachmentService;

//    public ProductServiceImpl(@Lazy ProductRepository productRepository,
//                              @Lazy CategoryService categoryService,
//                              @Lazy UnitService unitService,
//                              @Lazy AttachmentService attachmentService) {
//        this.productRepository = productRepository;
//        this.categoryService = categoryService;
//        this.unitService = unitService;
//        this.attachmentService = attachmentService;
//    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> products = productPage.getContent();
        List<ProductInfoDTO> productInfoDTOS = products
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(productInfoDTOS);
    }

    @Override
    public ApiResult<ProductInfoDTO> add(ProductAddDTO productAddDTO) {
        checkName(productAddDTO.getName());
        Category category = categoryService.getByIdOrElseThrow(productAddDTO.getCategoryId());
        Unit unit = unitService.getByIdOrElseThrow(productAddDTO.getUnitId());
        /*Attachment attachment = attachmentService.getByOrElseThrow(productAddDTO.getAttachmentId());*/
        Product product = new Product(
                productAddDTO.getName(),
                category,
                null,
                randomForUniqueId(),
                unit
        );
        productRepository.save(product);
        return returnApiResult(product);
    }

    @Override
    public ApiResult<ProductInfoDTO> update(ProductUpdateDTO productUpdateDTO, Integer id) {
        checkName(productUpdateDTO.getName(),id);
        Product product = getByIdOrElseThrow(id);
        Attachment attachment = attachmentService.getByOrElseThrow(productUpdateDTO.getUnitId());
        Unit unit = unitService.getByIdOrElseThrow(productUpdateDTO.getUnitId());
        Category category = categoryService.getByIdOrElseThrow(productUpdateDTO.getCategoryId());
        product.setAttachment(attachment);
        product.setCategory(category);
        product.setUnit(unit);
        product.setName(productUpdateDTO.getName());
        productRepository.save(product);
        return returnApiResult(product);
    }

    @Override
    public String delete(Integer id) {
        Product product = getByIdOrElseThrow(id);
        productRepository.delete(product);
        return "SUCCESS";
    }

    private void checkName(String name) {
        boolean exists = productRepository.existsByName(name);
        if (exists) throw RestException.alreadyExist("Product");
    }

    private void checkName(String name, Integer id) {
        boolean exists = productRepository.existsByNameAndIdNot(name,id);
        if (exists) throw RestException.alreadyExist("Product");
    }

    @Override
    public Product getByIdOrElseThrow(Integer id) {
        return productRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Warehouse")
        );
    }

    @Override
    public ApiResult<ProductInfoDTO> getOne(Integer id) {
        Product product = getByIdOrElseThrow(id);
        ProductInfoDTO productInfoDTO = entityToInfoDTO(product);
        return ApiResult.successResponse(productInfoDTO);
    }

    private ApiResult<ProductInfoDTO> returnApiResult(Product product) {
        ProductInfoDTO productInfoDTO = new ProductInfoDTO(
                product.getName(),
                product.getCategory().getId(),
                null,
                product.getUnit().getId()
        );
        return new ApiResult<>(productInfoDTO,true);
    }

    public Long randomForUniqueId() {

        double random = Math.random();

        Double a = random * 1_000_000_000;

        return a.longValue();
    }

    private ProductInfoDTO entityToInfoDTO(Product product) {
        return new ProductInfoDTO(
                product.getName(),
                product.getCategory().getId(),
                product.getAttachment().getId(),
                product.getUnit().getId()
        );
    }

}

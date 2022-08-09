package ai.ecma.warehouseexam.service;

import ai.ecma.warehouseexam.entity.Category;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.mapper.CategoryMapper;
import ai.ecma.warehouseexam.payload.category.CategoryAddDTO;
import ai.ecma.warehouseexam.payload.category.CategoryInfoDTO;
import ai.ecma.warehouseexam.payload.category.CategoryUpdateDTO;
import ai.ecma.warehouseexam.repository.CategoryRepository;
import ai.ecma.warehouseexam.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public ApiResult<CategoryInfoDTO> getOne(Integer id) {
        Category category = getByIdOrElseThrow(id);
        CategoryInfoDTO categoryInfoDTO = entityToInfoDTO(category);
        return ApiResult.successResponse(categoryInfoDTO);
    }


    @Override
    public ApiResult<List<CategoryInfoDTO>> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryInfoDTO> categoryInfoDTOList = categoryList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(categoryInfoDTOList);
    }

//
//    @Override
//    public ApiResult<?> getAll(int page, int size) {
//        Sort sort = Sort.by(Sort.Direction.ASC, Category.Fields.name);
//
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        Page<Category> categoryPage = categoryRepository.findAll(pageable);
//
//        List<Category> categoryList = categoryPage.getContent();
//
//        List<CategoryInfoDTO> categoryDTOList = categoryList
//                .stream()
//                .map(this::entityToInfoDTO)
//                .collect(Collectors.toList());
//
//        Page<CategoryInfoDTO> myPage = new PageImpl<>(
//                categoryDTOList,
//
//                categoryPage.getPageable(),
//                categoryPage.getTotalElements()
//        );
//        return ApiResult.successResponse(myPage);
//    }


    @Override
    public ApiResult<CategoryInfoDTO> add(CategoryAddDTO categoryAddDTO) {
        checkExistingName(categoryAddDTO.getName());
        Category category = new Category(
                categoryAddDTO.getName()
        );
        categoryRepository.save(category);

        return returnApiResult(category, true, "success");
    }

    @Override
    public ApiResult<CategoryInfoDTO> update(CategoryUpdateDTO categoryUpdateDTO, Integer id) {
        checkExistingName(categoryUpdateDTO.getName(), id);
        Category category = getByIdOrElseThrow(id);

        categoryMapper.update(categoryUpdateDTO, category);

        categoryRepository.save(category);

        return returnApiResult(category, true, "success");
    }

    @Override
    public String delete(Integer id) {
        Category category = getByIdOrElseThrow(id);
        categoryRepository.delete(category);
        return "Deleted";
    }

//    @Override
//    public ApiResult<CategoryInfoDTO> add(CategoryAddWithProductDTO categoryAddWithProductDTO) {
//        checkExistingName(categoryAddWithProductDTO.getName());
//        Category category = categoryMapper.mapAddDTOToProductAndCategory(categoryAddWithProductDTO);
//
//        List<Product> productList = new ArrayList<>();
//        for (ProductAddDTO productAddDTO : categoryAddWithProductDTO.getProducts()) {
//            Product product=productMapper.mapAddDTOToEntity(productAddDTO);
//            productList.add(product);
//        }
//        category.setProductList(productList);
//
//        categoryRepository.save(category);
//
//        return returnApiResult(category, true, "success");
//    }

    private CategoryInfoDTO entityToInfoDTO(Category category) {
        CategoryInfoDTO categoryInfoDTO = categoryMapper.mapEntityToInfoDTO(category);
        return categoryInfoDTO;
    }

    public Category getByIdOrElseThrow(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> RestException.notFound("Category")
        );
    }



    private void checkExistingName(String name, Integer id) {
        boolean exists = categoryRepository.existsByNameAndId(name, id);
        if (exists) throw RestException.alreadyExist("Category");
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) throw RestException.notFound("Category");
    }

    private void checkExistingName(String name) {
        boolean exist = categoryRepository.existsByName(name);
        if (exist) throw RestException.alreadyExist("Category");
    }

    private ApiResult<CategoryInfoDTO> returnApiResult(Category category, boolean success, String msg) {
        CategoryInfoDTO categoryInfoDTO = entityToInfoDTO(category);
        return new ApiResult<>(categoryInfoDTO, true, msg);
    }
}

package ai.ecma.warehouseexam.controller.category;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.category.CategoryAddDTO;
import ai.ecma.warehouseexam.payload.category.CategoryInfoDTO;
import ai.ecma.warehouseexam.payload.category.CategoryUpdateDTO;
import ai.ecma.warehouseexam.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {
    private final CategoryService categoryService;

    @Override
    public ApiResult<List<CategoryInfoDTO>> getAll() {
        return categoryService.getAll();
    }

//    @Override
//    public ApiResult<CategoryInfoDTO> add(CategoryAddDTO categoryAddDTO) {
//        return categoryService.add(categoryAddDTO);
//    }

    @Override
    public ApiResult<CategoryInfoDTO> add(CategoryAddDTO categoryAddDTO) {
        return categoryService.add(categoryAddDTO);
    }

    @Override
    public ApiResult<CategoryInfoDTO> update(CategoryUpdateDTO categoryUpdateDTO, Integer id) {
        return categoryService.update(categoryUpdateDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return categoryService.delete(id);
    }
}

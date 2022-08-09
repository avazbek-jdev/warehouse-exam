package ai.ecma.warehouseexam.service.interfaces;

import ai.ecma.warehouseexam.entity.Category;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.category.CategoryAddDTO;
import ai.ecma.warehouseexam.payload.category.CategoryInfoDTO;
import ai.ecma.warehouseexam.payload.category.CategoryUpdateDTO;

import java.util.List;

public interface CategoryService {

    ApiResult<List<CategoryInfoDTO>> getAll();


    ApiResult<CategoryInfoDTO> add(CategoryAddDTO categoryAddDTO);

    ApiResult<CategoryInfoDTO> update(CategoryUpdateDTO categoryUpdateDTO, Integer id);

    String delete(Integer id);


    Category getByIdOrElseThrow(Integer categoryId);

    ApiResult<CategoryInfoDTO> getOne(Integer id);
}


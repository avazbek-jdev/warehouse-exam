package ai.ecma.warehouseexam.mapper;

import ai.ecma.warehouseexam.entity.Category;
import ai.ecma.warehouseexam.payload.category.CategoryInfoDTO;
import ai.ecma.warehouseexam.payload.category.CategoryUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryInfoDTO mapEntityToInfoDTO(Category category);

    void update(CategoryUpdateDTO categoryUpdateDTO, @MappingTarget Category category);

}

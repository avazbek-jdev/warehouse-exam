package ai.ecma.warehouseexam.controller.category;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.category.CategoryAddDTO;
import ai.ecma.warehouseexam.payload.category.CategoryInfoDTO;
import ai.ecma.warehouseexam.payload.category.CategoryUpdateDTO;
import ai.ecma.warehouseexam.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CategoryController.CATEGORYCONTROLLER_PATH)
public interface CategoryController {
    String CATEGORYCONTROLLER_PATH = AppConstant.BASE_PATH + "/category/";

    String GET_ALL_PATH = "all";
    String ADD_PATH = "add";
    String UPDATE_PATH = "update";
    String DELETE_PATH = "delete";

    @PreAuthorize(value = "hasAuthority('VIEW_CATEGORIES')")
    @GetMapping(GET_ALL_PATH)
    ApiResult<List<CategoryInfoDTO>> getAll();


//    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
//                        @RequestParam(defaultValue = "10") int size);


    @PreAuthorize(value = "hasAuthority('ADD_CATEGORY')")
    @PostMapping(ADD_PATH)
    ApiResult<CategoryInfoDTO> add(@RequestBody CategoryAddDTO categoryAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_CATEGORY')")
    @PutMapping(UPDATE_PATH + "/{id}")
    ApiResult<CategoryInfoDTO> update(@RequestBody CategoryUpdateDTO categoryUpdateDTO, @PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('DELETE_CATEGORY')")
    @DeleteMapping(DELETE_PATH + "/{id}")
    String delete(@PathVariable Integer id);


//    @RequestMapping(value = {"all", "/search"}, method = RequestMethod.GET)
//     default String search(
//            @RequestParam Map<String, String> allRequestParams, ModelMap model) {
//        return "viewName";
//    }

}

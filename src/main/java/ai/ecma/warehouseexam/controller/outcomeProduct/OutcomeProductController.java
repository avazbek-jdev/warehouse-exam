package ai.ecma.warehouseexam.controller.outcomeProduct;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.outcomeProduct.OutcomeProductAddDTO;
import ai.ecma.warehouseexam.payload.outcomeProduct.OutcomeProductInfoDTO;
import ai.ecma.warehouseexam.payload.outcomeProduct.OutcomeProductUpdateDTO;
import ai.ecma.warehouseexam.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(OutcomeProductController.OUTCOME_PRODUCT_CONTROLLER_PATH)
public interface OutcomeProductController {

    String OUTCOME_PRODUCT_CONTROLLER_PATH = AppConstant.BASE_PATH + "/outcome-product/";
    String ADD = "add";
    String UPDATE = "update";
    String VIEW = "view";

    @PreAuthorize(value = "hasAuthority('VIEW_OUTCOME_PRODUCT')")
    @GetMapping(VIEW + "/{id}")
    ApiResult<OutcomeProductInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('VIEW_OUTCOME_PRODUCTS')")
    @GetMapping(VIEW)
    ApiResult<List<OutcomeProductInfoDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('ADD_OUTCOME_PRODUCT')")
    @PostMapping(ADD)
    ApiResult<OutcomeProductInfoDTO> add(@RequestBody OutcomeProductAddDTO outcomeProductAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_OUTCOME_PRODUCT')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<OutcomeProductInfoDTO> update(OutcomeProductUpdateDTO outcomeProductUpdateDTO, @PathVariable Integer id);


}

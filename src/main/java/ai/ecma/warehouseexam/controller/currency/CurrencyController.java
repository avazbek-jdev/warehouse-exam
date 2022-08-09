package ai.ecma.warehouseexam.controller.currency;


import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.currency.CurrencyAddDTO;
import ai.ecma.warehouseexam.payload.currency.CurrencyInfoDTO;
import ai.ecma.warehouseexam.payload.currency.CurrencyUpdateDTO;
import ai.ecma.warehouseexam.utils.AppConstant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(CurrencyController.CURRENCYCONTROLLER_PATH)
public interface CurrencyController {
    String CURRENCYCONTROLLER_PATH = AppConstant.BASE_PATH + "/currency/";

    String GET_ALL_PATH = "all";
    String ADD_PATH = "add";
    String UPDATE_PATH = "update";
    String DELETE_PATH = "delete";

    @PreAuthorize(value = "hasAuthority('VIEW_CURRENCIES')")
    @GetMapping(GET_ALL_PATH)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('ADD_CURRENCY')")
    @PostMapping(ADD_PATH)
    ApiResult<CurrencyInfoDTO> add(@RequestBody CurrencyAddDTO currencyAddDTO);

    @PreAuthorize(value = "hasAuthority('UPDATE_CURRENCY')")
    @PutMapping(UPDATE_PATH + "/{id}")
    ApiResult<CurrencyInfoDTO> update(@RequestBody CurrencyUpdateDTO currencyUpdateDTO, @PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('DELETE_CURRENCY')")
    @DeleteMapping(DELETE_PATH + "/{id}")
    String delete(@PathVariable Integer id);

}


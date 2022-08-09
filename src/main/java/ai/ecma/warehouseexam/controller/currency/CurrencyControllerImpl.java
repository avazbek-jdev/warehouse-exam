package ai.ecma.warehouseexam.controller.currency;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.currency.CurrencyAddDTO;
import ai.ecma.warehouseexam.payload.currency.CurrencyInfoDTO;
import ai.ecma.warehouseexam.payload.currency.CurrencyUpdateDTO;
import ai.ecma.warehouseexam.service.interfaces.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyControllerImpl implements CurrencyController{
    private final CurrencyService currencyService;

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return currencyService.getAll(page, size);
    }

    @Override
    public ApiResult<CurrencyInfoDTO> add(CurrencyAddDTO currencyAddDTO) {
        return currencyService.add(currencyAddDTO);
    }

    @Override
    public ApiResult<CurrencyInfoDTO> update(CurrencyUpdateDTO currencyUpdateDTO, Integer id) {
        return currencyService.update(currencyUpdateDTO, id);
    }

    @Override
    public String delete(Integer id) {
        return currencyService.delete(id);
    }
}

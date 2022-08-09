package ai.ecma.warehouseexam.service.interfaces;

import ai.ecma.warehouseexam.entity.Currency;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.currency.CurrencyAddDTO;
import ai.ecma.warehouseexam.payload.currency.CurrencyInfoDTO;
import ai.ecma.warehouseexam.payload.currency.CurrencyUpdateDTO;

public interface CurrencyService {
    ApiResult<?> getAll(int page, int size);

    ApiResult<CurrencyInfoDTO> add(CurrencyAddDTO currencyAddDTO);

    ApiResult<CurrencyInfoDTO> update(CurrencyUpdateDTO currencyUpdateDTO, Integer id);

    String delete(Integer id);

    Currency getByIdOrElseThrow(Integer id);

    ApiResult<CurrencyInfoDTO> getOne(Integer id);
}


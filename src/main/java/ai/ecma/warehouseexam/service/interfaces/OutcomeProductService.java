package ai.ecma.warehouseexam.service.interfaces;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.outcomeProduct.OutcomeProductAddDTO;
import ai.ecma.warehouseexam.payload.outcomeProduct.OutcomeProductInfoDTO;
import ai.ecma.warehouseexam.payload.outcomeProduct.OutcomeProductUpdateDTO;

import java.util.List;

public interface OutcomeProductService {

    ApiResult<OutcomeProductInfoDTO> getOne(Integer id);

    ApiResult<List<OutcomeProductInfoDTO>> getAll(int page, int size);

    ApiResult<OutcomeProductInfoDTO> add(OutcomeProductAddDTO outcomeProductAddDTO);

    ApiResult<OutcomeProductInfoDTO> update(OutcomeProductUpdateDTO outcomeProductUpdateDTO, Integer id);

}

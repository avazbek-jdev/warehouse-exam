package ai.ecma.warehouseexam.controller.outcomeProduct;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.outcomeProduct.OutcomeProductAddDTO;
import ai.ecma.warehouseexam.payload.outcomeProduct.OutcomeProductInfoDTO;
import ai.ecma.warehouseexam.payload.outcomeProduct.OutcomeProductUpdateDTO;
import ai.ecma.warehouseexam.service.interfaces.OutcomeProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OutcomeProductControllerImpl implements OutcomeProductController{

    private final OutcomeProductService outcomeProductService;

    @Override
    public ApiResult<OutcomeProductInfoDTO> getOne(Integer id) {
        return outcomeProductService.getOne(id);
    }

    @Override
    public ApiResult<List<OutcomeProductInfoDTO>> getAll(int page, int size) {
        return outcomeProductService.getAll(page,size);
    }

    @Override
    public ApiResult<OutcomeProductInfoDTO> add(OutcomeProductAddDTO outcomeProductAddDTO) {
        return outcomeProductService.add(outcomeProductAddDTO);
    }

    @Override
    public ApiResult<OutcomeProductInfoDTO> update(OutcomeProductUpdateDTO outcomeProductUpdateDTO, Integer id) {
        return outcomeProductService.update(outcomeProductUpdateDTO,id);
    }
}

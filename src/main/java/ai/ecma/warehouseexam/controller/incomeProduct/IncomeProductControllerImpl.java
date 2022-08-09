package ai.ecma.warehouseexam.controller.incomeProduct;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.incomeProduct.IncomeProductAddDTO;
import ai.ecma.warehouseexam.payload.incomeProduct.IncomeProductInfoDTO;
import ai.ecma.warehouseexam.payload.incomeProduct.IncomeProductUpdateDTO;
import ai.ecma.warehouseexam.service.interfaces.IncomeProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IncomeProductControllerImpl implements IncomeProductController{

    private final IncomeProductService incomeProductService;

    @Override
    public ApiResult<IncomeProductInfoDTO> getOne(Integer id) {
        return incomeProductService.getOne(id);
    }

//    @Override
//    public ApiResult<List<IncomeProductInfoDTO>> getAll() {
//        return incomeProductService.getAll();
//    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return incomeProductService.getAll(page, size);
    }

    @Override
    public ApiResult<IncomeProductInfoDTO> add(IncomeProductAddDTO incomeProductAddDTO) {
        return incomeProductService.add(incomeProductAddDTO);
    }

    @Override
    public ApiResult<IncomeProductInfoDTO> update(IncomeProductUpdateDTO incomeProductUpdateDTO, Integer id) {
        return incomeProductService.update(incomeProductUpdateDTO, id);
    }
}
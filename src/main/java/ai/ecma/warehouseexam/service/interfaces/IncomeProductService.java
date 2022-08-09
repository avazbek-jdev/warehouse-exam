package ai.ecma.warehouseexam.service.interfaces;


import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.incomeProduct.IncomeProductAddDTO;
import ai.ecma.warehouseexam.payload.incomeProduct.IncomeProductInfoDTO;
import ai.ecma.warehouseexam.payload.incomeProduct.IncomeProductUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IncomeProductService {

    ApiResult<IncomeProductInfoDTO> getOne(Integer id);

//    ApiResult<List<IncomeProductInfoDTO>> getAll();

    ApiResult<List<IncomeProductInfoDTO>> getAll(int page, int size);

    ApiResult<IncomeProductInfoDTO> add(IncomeProductAddDTO incomeProductAddDTO);

    ApiResult<IncomeProductInfoDTO> update(IncomeProductUpdateDTO incomeProductUpdateDTO, Integer id);


//    ApiResult<IncomeProductInfoDTO> getOne(Integer id);
//
//    ApiResult<List<IncomeProductInfoDTO>> getAll(int page, int size);
//
//    ApiResult<IncomeProductInfoDTO> add(IncomeProductAddDTO incomeProductAddDTO);
//
//    ApiResult<IncomeProductInfoDTO> update(IncomeProductUpdateDTO incomeProductUpdateDTO, Integer id);

}

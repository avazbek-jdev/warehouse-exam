package ai.ecma.warehouseexam.service;

import ai.ecma.warehouseexam.entity.Currency;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.mapper.CurrencyMapper;
import ai.ecma.warehouseexam.payload.currency.CurrencyAddDTO;
import ai.ecma.warehouseexam.payload.currency.CurrencyInfoDTO;
import ai.ecma.warehouseexam.payload.currency.CurrencyUpdateDTO;
import ai.ecma.warehouseexam.repository.CurrencyRepository;
import ai.ecma.warehouseexam.service.interfaces.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService{

    private final CurrencyMapper currencyMapper;
    private final CurrencyRepository currencyRepository;

    @Override
    public ApiResult<List<CurrencyInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Currency> currencyPage = currencyRepository.findAll(pageable);
       // Page<Currency> currencyPage = currencyRepository.findAll(pageable);
        List<Currency> currencies = currencyPage.getContent();
        List<CurrencyInfoDTO> currencyInfoDTOS = currencies
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(currencyInfoDTOS);
    }

    @Override
    public ApiResult<CurrencyInfoDTO> add(CurrencyAddDTO currencyAddDTO) {
        checkName(currencyAddDTO.getName());
        Currency currency = currencyMapper.mapAddDTOToEntity(currencyAddDTO);
        currencyRepository.save(currency);
        return returnApiResult(currency,true,"Success");
    }

    @Override
    public ApiResult<CurrencyInfoDTO> update(CurrencyUpdateDTO currencyUpdateDTO, Integer id) {
        checkName(currencyUpdateDTO.getName(),id);
        Currency currency = getByIdOrElseThrow(id);
        currencyMapper.updateEntity(currencyUpdateDTO,currency);
        currencyRepository.save(currency);
        return returnApiResult(currency,true,"Success");
    }

    @Override
    public String delete(Integer id) {
        Currency currency = getByIdOrElseThrow(id);
        currencyRepository.delete(currency);
        return "Success";
    }

    private ApiResult<CurrencyInfoDTO> returnApiResult(Currency currency, boolean success, String msg) {
        CurrencyInfoDTO currencyInfoDTO = entityToInfoDTO(currency);
        return new ApiResult<>(currencyInfoDTO,success,msg);
    }

    public Currency getByIdOrElseThrow(Integer id) {
        return currencyRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Currency")
        );
    }

    @Override
    public ApiResult<CurrencyInfoDTO> getOne(Integer id) {
        Currency currency = getByIdOrElseThrow(id);
        CurrencyInfoDTO currencyInfoDTO = entityToInfoDTO(currency);
        return ApiResult.successResponse(currencyInfoDTO);
    }

    private void checkName(String name) {
        boolean exists = currencyRepository.existsByNameAndActiveTrue(name);
        if (exists) throw RestException.alreadyExist("Currency");
    }

    private void checkName(String name, Integer id) {
        boolean exists = currencyRepository.existsByNameAndIdNotAndActiveTrue(name,id);
        if (exists) throw RestException.alreadyExist("Currency");
    }

    private CurrencyInfoDTO entityToInfoDTO(Currency currency) {
        return currencyMapper.entityToInfoDTO(currency);
    }
}

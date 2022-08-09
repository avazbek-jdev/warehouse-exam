package ai.ecma.warehouseexam.service;

import ai.ecma.warehouseexam.entity.Currency;
import ai.ecma.warehouseexam.entity.OutcomeWarehouse;
import ai.ecma.warehouseexam.entity.Warehouse;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.payload.outcomeWarehouse.OutcomeWarehouseAddDTO;
import ai.ecma.warehouseexam.payload.outcomeWarehouse.OutcomeWarehouseInfoDTO;
import ai.ecma.warehouseexam.payload.outcomeWarehouse.OutcomeWarehouseUpdateDTO;
import ai.ecma.warehouseexam.repository.OutcomeWarehouseRepository;
import ai.ecma.warehouseexam.service.interfaces.CurrencyService;
import ai.ecma.warehouseexam.service.interfaces.OutcomeWarehouseService;
import ai.ecma.warehouseexam.service.interfaces.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutcomeWarehouseServiceImpl implements OutcomeWarehouseService {

    private final OutcomeWarehouseRepository outcomeWarehouseRepository;
    private final WarehouseService warehouseService;
    private final CurrencyService currencyService;

    @Override
    public ApiResult<OutcomeWarehouseInfoDTO> getOne(Integer id) {
        OutcomeWarehouse outcomeWarehouse = getByIdOrElseThrow(id);
        OutcomeWarehouseInfoDTO outcomeWarehouseInfoDTO = entityToInfoDTO(outcomeWarehouse);
        return ApiResult.successResponse(outcomeWarehouseInfoDTO);
    }

    @Override
    public ApiResult<List<OutcomeWarehouseInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<OutcomeWarehouse> productPage = outcomeWarehouseRepository.findAll(pageable);
        List<OutcomeWarehouse> outcomeWarehouses = productPage.getContent();
        List<OutcomeWarehouseInfoDTO> outcomeWarehouseInfoDTOS = outcomeWarehouses
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(outcomeWarehouseInfoDTOS);
    }

    @Override
    public ApiResult<OutcomeWarehouseInfoDTO> add(OutcomeWarehouseAddDTO outcomeWarehouseAddDTO) {
        Warehouse warehouse = warehouseService.getByIdOrElseThrow(outcomeWarehouseAddDTO.getWarehouseId());
        Currency currency = currencyService.getByIdOrElseThrow(outcomeWarehouseAddDTO.getCurrencyId());
       // Currency currency = currencyService.getByIdOrElseThrow(outcomeWarehouseAddDTO.getCurrencyId());
        OutcomeWarehouse outcomeWarehouse = new OutcomeWarehouse(
                warehouse,
                currency,
                random()
        );
        outcomeWarehouseRepository.save(outcomeWarehouse);
        return ApiResult.successResponse(entityToInfoDTO(outcomeWarehouse));
    }

    @Override
    public ApiResult<OutcomeWarehouseInfoDTO> update(OutcomeWarehouseUpdateDTO outcomeWarehouseUpdateDTO, Integer id) {
        Warehouse warehouse = warehouseService.getByIdOrElseThrow(outcomeWarehouseUpdateDTO.getWarehouseId());
       Currency currency = currencyService.getByIdOrElseThrow(outcomeWarehouseUpdateDTO.getCurrencyId());
        OutcomeWarehouse outcomeWarehouse = getByIdOrElseThrow(id);
        outcomeWarehouse.setWarehouse(warehouse);
        outcomeWarehouse.setCurrency(currency);
        outcomeWarehouseRepository.save(outcomeWarehouse);
        return ApiResult.successResponse(entityToInfoDTO(outcomeWarehouse));
    }

    @Override
    public OutcomeWarehouse getByIdOrElseThrow(Integer id) {
        return outcomeWarehouseRepository.findById(id).orElseThrow(
                () -> RestException.notFound("OutcomeWarehouse")
        );
    }

    private Long random() {

        double rand = Math.random();

        Double a = rand * 1_000_000;

        return a.longValue();
    }

    private OutcomeWarehouseInfoDTO entityToInfoDTO(OutcomeWarehouse outcomeWarehouse) {
        return new OutcomeWarehouseInfoDTO(
                outcomeWarehouse.getWarehouse().getId(),
                outcomeWarehouse.getCurrency().getId()
        );
    }

}

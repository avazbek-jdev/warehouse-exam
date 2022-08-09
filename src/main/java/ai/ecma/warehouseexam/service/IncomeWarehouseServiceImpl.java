package ai.ecma.warehouseexam.service;


import ai.ecma.warehouseexam.entity.Currency;
import ai.ecma.warehouseexam.entity.IncomeWarehouse;
import ai.ecma.warehouseexam.entity.Supplier;
import ai.ecma.warehouseexam.entity.Warehouse;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.payload.incomeWarehouse.IncomeWarehouseAddDTO;
import ai.ecma.warehouseexam.payload.incomeWarehouse.IncomeWarehouseInfoDTO;
import ai.ecma.warehouseexam.payload.incomeWarehouse.IncomeWarehouseUpdateDTO;
import ai.ecma.warehouseexam.repository.IncomeWarehouseRepository;
import ai.ecma.warehouseexam.service.interfaces.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeWarehouseServiceImpl implements IncomeWarehouseService {

    private final IncomeWarehouseRepository incomeWarehouseRepository;
    private final IncomeProductService incomeProductService;
    private final WarehouseService warehouseService;
    private final CurrencyService currencyService;
    private final SupplierService supplierService;

    public IncomeWarehouseServiceImpl(@Lazy IncomeWarehouseRepository incomeWarehouseRepository,
                                      @Lazy IncomeProductService incomeProductService,
                                      @Lazy WarehouseService warehouseService,
                                      @Lazy CurrencyService currencyService,
                                      @Lazy SupplierService supplierService) {
        this.incomeWarehouseRepository = incomeWarehouseRepository;
        this.incomeProductService = incomeProductService;
        this.warehouseService = warehouseService;
        this.currencyService = currencyService;
        this.supplierService = supplierService;
    }

    /*private final*/

    @Override
    public ApiResult<IncomeWarehouseInfoDTO> getOne(Integer id) {
        IncomeWarehouse incomeWarehouse = getByIdOrElseThrow(id);
        IncomeWarehouseInfoDTO incomeWarehouseInfoDTO = entityToInfoDTO(incomeWarehouse);
        return ApiResult.successResponse(incomeWarehouseInfoDTO);
    }

    private ApiResult<IncomeWarehouseInfoDTO> returnApiResult(IncomeWarehouse incomeWarehouse, boolean success, String msg) {
        IncomeWarehouseInfoDTO inWarehouseInfoDTO = entityToInfoDTO(incomeWarehouse);
        return new ApiResult<>(inWarehouseInfoDTO, success, msg);
    }

    @Override
    public ApiResult<List<IncomeWarehouseInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<IncomeWarehouse> incomeWarehousePage = incomeWarehouseRepository.findAll(pageable);
        List<IncomeWarehouse> incomeWarehouseList = incomeWarehousePage.getContent();
        List<IncomeWarehouseInfoDTO> incomeWarehouseInfoDTOS = incomeWarehouseList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(incomeWarehouseInfoDTOS);
    }

    @Override
    public ApiResult<IncomeWarehouseInfoDTO> update(IncomeWarehouseUpdateDTO incomeWarehouseUpdateDTO, Integer id) {
        IncomeWarehouse incomeWarehouse = getByIdOrElseThrow(id);
        Supplier supplier = supplierService.getByIdOrElseThrow(incomeWarehouseUpdateDTO.getSupplierId());
        Currency currency = currencyService.getByIdOrElseThrow(incomeWarehouseUpdateDTO.getCurrencyId());
        incomeWarehouse.setSupplier(supplier);
        incomeWarehouse.setCurrency(currency);
        incomeWarehouseRepository.save(incomeWarehouse);
        return ApiResult.successResponse(entityToInfoDTO(incomeWarehouse));
    }

    @Override
    public ApiResult<IncomeWarehouseInfoDTO> add(IncomeWarehouseAddDTO incomeWarehouseAddDTO) {

        Warehouse warehouse = warehouseService.getByIdOrElseThrow(incomeWarehouseAddDTO.getWarehouseId());
        Supplier supplier = supplierService.getByIdOrElseThrow(incomeWarehouseAddDTO.getSupplierId());
        Currency currency = currencyService.getByIdOrElseThrow(incomeWarehouseAddDTO.getCurrencyId());
        IncomeWarehouse incomeWarehouse = new IncomeWarehouse(
                warehouse,
                supplier,
                currency,
                Math.toIntExact(random()),
                random()
        );
        incomeWarehouseRepository.save(incomeWarehouse);
        return ApiResult.successResponse(entityToInfoDTO(incomeWarehouse));
    }

    public IncomeWarehouse getByIdOrElseThrow(Integer id) {
        return incomeWarehouseRepository.findById(id).orElseThrow(
                () -> RestException.notFound("IncomeWarehouse")
        );
    }

    private Long random() {

        double rand = Math.random();

        Double a = rand * 1_000_000;

        return a.longValue();
    }

    public IncomeWarehouseInfoDTO entityToInfoDTO(IncomeWarehouse incomeWarehouse) {
        return new IncomeWarehouseInfoDTO(
                incomeWarehouse.getWarehouse().getId(),
                incomeWarehouse.getSupplier().getId(),
                incomeWarehouse.getCurrency().getId(),
                incomeWarehouse.getFactureNumber(),
                incomeWarehouse.getUniqueId()
        );
    }



}

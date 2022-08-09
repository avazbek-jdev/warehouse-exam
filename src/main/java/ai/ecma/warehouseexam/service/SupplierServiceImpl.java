package ai.ecma.warehouseexam.service;

import ai.ecma.warehouseexam.entity.Supplier;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.mapper.SupplierMapper;
import ai.ecma.warehouseexam.payload.supplier.SupplierAddDTO;
import ai.ecma.warehouseexam.payload.supplier.SupplierInfoDTO;
import ai.ecma.warehouseexam.payload.supplier.SupplierUpdateDTO;
import ai.ecma.warehouseexam.repository.SupplierRepository;
import ai.ecma.warehouseexam.service.interfaces.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public ApiResult<SupplierInfoDTO> getOne(Integer id) {
        Supplier supplier = getByIdOrElseThrow(id);
        SupplierInfoDTO supplierInfoDTO = entityToInfoDTO(supplier);
        return ApiResult.successResponse(supplierInfoDTO);
    }

    @Override
    public ApiResult<List<SupplierInfoDTO>> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Supplier> supplierPage = supplierRepository.findAll(pageable);
        List<Supplier> suppliers = supplierPage.getContent();
        List<SupplierInfoDTO> supplierInfoDTOS = suppliers
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(supplierInfoDTOS);
    }

    @Override
    public ApiResult<SupplierInfoDTO> add(SupplierAddDTO supplierAddDTO) {
        checkPhoneNumber(supplierAddDTO.getPhoneNumber());
        Supplier supplier = supplierMapper.insertAddDTO(supplierAddDTO);
        supplierRepository.save(supplier);
        SupplierInfoDTO supplierInfoDTO = entityToInfoDTO(supplier);
        return ApiResult.successResponse(supplierInfoDTO);
    }

    @Override
    public ApiResult<SupplierInfoDTO> update(SupplierUpdateDTO supplierUpdateDTO, Integer id) {
        supplierRepository.existsByPhoneNumberAndIdNot(supplierUpdateDTO.getPhoneNumber(),id);
        Supplier supplier = getByIdOrElseThrow(id);
        supplierMapper.update(supplierUpdateDTO,supplier);
        supplierRepository.save(supplier);
        SupplierInfoDTO supplierInfoDTO = entityToInfoDTO(supplier);
        return ApiResult.successResponse(supplierInfoDTO);
    }

    @Override
    public String delete(Integer id) {
        Supplier supplier = getByIdOrElseThrow(id);
        supplierRepository.delete(supplier);
        return "success";
    }

    private void checkPhoneNumber(String phoneNumber) {
        boolean exists = supplierRepository.existsByPhoneNumber(phoneNumber);
        if (exists) throw RestException.alreadyExist("Supplier");
    }

    @Override
    public Supplier getByIdOrElseThrow(Integer id) {
        return supplierRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Supplier")
        );
    }

    private SupplierInfoDTO entityToInfoDTO(Supplier supplier) {return supplierMapper.entityToInfoDTO(supplier);}

}

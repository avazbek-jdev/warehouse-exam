package ai.ecma.warehouseexam.mapper;

import ai.ecma.warehouseexam.entity.Currency;
import ai.ecma.warehouseexam.payload.currency.CurrencyAddDTO;
import ai.ecma.warehouseexam.payload.currency.CurrencyInfoDTO;
import ai.ecma.warehouseexam.payload.currency.CurrencyUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface CurrencyMapper {


    CurrencyInfoDTO entityToInfoDTO(Currency currency);

    Currency mapAddDTOToEntity(CurrencyAddDTO currencyAddDTO);

    void updateEntity(CurrencyUpdateDTO currencyUpdateDTO, @MappingTarget Currency currency);
}

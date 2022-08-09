package ai.ecma.warehouseexam.payload.incomeWarehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IncomeWarehouseAddDTO {

    private Integer warehouseId;

    private Integer supplierId;

    private Integer currencyId;

}

package ai.ecma.warehouseexam.payload.outcomeWarehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OutcomeWarehouseUpdateDTO {

    private Integer warehouseId;

    private Integer currencyId;

}

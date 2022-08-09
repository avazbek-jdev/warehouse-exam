package ai.ecma.warehouseexam.payload.outcomeProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OutcomeProductAddDTO {

    private Integer outcomeWarehouseId;

    private Integer productId;

    private Integer unitId;

    private Double unitAmount;

    private Double price;

}

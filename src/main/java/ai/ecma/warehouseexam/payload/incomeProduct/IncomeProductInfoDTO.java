package ai.ecma.warehouseexam.payload.incomeProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class IncomeProductInfoDTO {

    private Integer productId;

    private Integer incomeWarehouseId;

    private Integer unitId;

    private Double unitAmount;

    private Double price;

    private Timestamp expiredDate;

}

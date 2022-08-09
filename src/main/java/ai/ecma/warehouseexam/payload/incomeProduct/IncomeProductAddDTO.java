package ai.ecma.warehouseexam.payload.incomeProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class IncomeProductAddDTO {

    private Integer productId;

    private Integer unitId;

    private Integer warehouseId;

    private Double unitAmount;

    private Double price;

    private Timestamp expirationDate;

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = Timestamp.valueOf(expirationDate);
    }
}

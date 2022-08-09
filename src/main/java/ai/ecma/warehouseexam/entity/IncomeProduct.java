package ai.ecma.warehouseexam.entity;

import ai.ecma.warehouseexam.entity.abs.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "income_product")

public class IncomeProduct extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private IncomeWarehouse incomeWarehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Unit unit;

    private Double unitAmount;

    private Double price;

    private Timestamp expiredDate;


}

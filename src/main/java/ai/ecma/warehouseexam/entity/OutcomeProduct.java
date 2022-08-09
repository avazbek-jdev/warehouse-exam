package ai.ecma.warehouseexam.entity;

import ai.ecma.warehouseexam.entity.abs.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "outcome_product")
public class OutcomeProduct extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private OutcomeWarehouse outcomeWarehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Unit unit;

    private Double unitAmount;

    private Double price;

}

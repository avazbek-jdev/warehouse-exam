package ai.ecma.warehouseexam.entity;

import ai.ecma.warehouseexam.entity.abs.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@ToString
@Entity(name = "outcome_warehouse")
@AllArgsConstructor
@NoArgsConstructor
public class OutcomeWarehouse extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    private Currency currency;

    private Long uniqueId;

}

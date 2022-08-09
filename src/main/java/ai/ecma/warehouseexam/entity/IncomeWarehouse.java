package ai.ecma.warehouseexam.entity;

import ai.ecma.warehouseexam.entity.abs.AbsEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity(name = "income_warehouse")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IncomeWarehouse extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    private Currency currency;

    private Integer factureNumber;

    @Column(unique = true)
    private Long uniqueId;
}
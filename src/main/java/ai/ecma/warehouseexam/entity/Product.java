package ai.ecma.warehouseexam.entity;

import ai.ecma.warehouseexam.entity.abs.AbsEntity;
import ai.ecma.warehouseexam.entity.attachment.Attachment;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "product")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update product set deleted=true where id=?")
@FieldNameConstants
public class Product extends AbsEntity {

    private String name;

  //  private Integer productNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Attachment attachment;

    private Long productUqId;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Unit unit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

package ai.ecma.warehouseexam.entity;

import ai.ecma.warehouseexam.entity.abs.AbsEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity(name = "supplier")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update supplier set deleted=true and active=false where id=?")
public class Supplier extends AbsEntity {

    private String name;

    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Supplier supplier = (Supplier) o;
        return getId() != null && Objects.equals(getId(), supplier.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

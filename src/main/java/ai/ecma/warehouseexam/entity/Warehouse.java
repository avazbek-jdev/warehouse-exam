package ai.ecma.warehouseexam.entity;

import ai.ecma.warehouseexam.entity.abs.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "ware_house")
@SQLDelete(sql = "update ware_house set deleted=true where id=?")
@Where(clause = "deleted=false")
@FieldNameConstants
public class Warehouse extends AbsEntity {

    private String name;

    private boolean active = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Warehouse wareHouse = (Warehouse) o;
        return getId() != null && Objects.equals(getId(), wareHouse.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}

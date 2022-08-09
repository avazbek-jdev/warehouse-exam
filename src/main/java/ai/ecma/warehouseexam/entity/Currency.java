package ai.ecma.warehouseexam.entity;

import ai.ecma.warehouseexam.entity.abs.AbsEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "currency")
@SQLDelete(sql = "update currency set deleted = true, active = false where id = ?")
@Where(clause = "deleted=false")
public class Currency extends AbsEntity {

    @Column(unique = true,nullable = false)
    private String name;

    private boolean active = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Currency currency = (Currency) o;
        return getId() != null && Objects.equals(getId(), currency.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

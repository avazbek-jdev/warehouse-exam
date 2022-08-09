package ai.ecma.warehouseexam.entity;

import ai.ecma.warehouseexam.entity.abs.AbsEntity;
import ai.ecma.warehouseexam.enums.PermissionEnum;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "roles")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update roles set deleted=true where id=?")

public class Role extends AbsEntity {

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<PermissionEnum> permissions;

}

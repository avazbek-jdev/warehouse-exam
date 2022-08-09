package ai.ecma.warehouseexam.entity.attachment;

import ai.ecma.warehouseexam.entity.abs.AbsEntity;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "attachment")
@FieldNameConstants
public class Attachment extends AbsEntity {

    private String originalName;

    private long size;

    private String contentType;

    private String name;

    public Attachment(String originalName, long size, String contentType) {
        this.originalName = originalName;
        this.size = size;
        this.contentType = contentType;
    }
}

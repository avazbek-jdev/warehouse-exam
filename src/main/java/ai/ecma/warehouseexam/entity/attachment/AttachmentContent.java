package ai.ecma.warehouseexam.entity.attachment;

import ai.ecma.warehouseexam.entity.abs.AbsEntity;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "attachment_content")
@FieldNameConstants
public class AttachmentContent extends AbsEntity {

    private byte[] bytes;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Attachment attachment;

}

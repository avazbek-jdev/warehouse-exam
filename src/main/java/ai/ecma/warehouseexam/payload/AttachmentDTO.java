package ai.ecma.warehouseexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentDTO {

    private Integer id;

    private String originalName;

    private long size;

    private String contentType;

    private String url;

}


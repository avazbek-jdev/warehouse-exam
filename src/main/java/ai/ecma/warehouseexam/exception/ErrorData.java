package ai.ecma.warehouseexam.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorData {
    private String msg;
    private Integer errorCode;
    private String fieldName;
}

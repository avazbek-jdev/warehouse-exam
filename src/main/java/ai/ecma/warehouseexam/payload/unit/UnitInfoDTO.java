package ai.ecma.warehouseexam.payload.unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UnitInfoDTO {
    private String name;

    private boolean active  = true;
}

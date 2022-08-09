package ai.ecma.warehouseexam.payload.unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UnitAddDTO {

    private String name;

    private boolean active  = true ;
}

package ai.ecma.warehouseexam.payload.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WarehouseUpdateDTO {

    private String name;

    private boolean active = true;

    public WarehouseUpdateDTO(String name) {
        this.name = name;
    }

}

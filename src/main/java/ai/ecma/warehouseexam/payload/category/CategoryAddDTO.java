package ai.ecma.warehouseexam.payload.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryAddDTO {

    private String name;

    private boolean active = true;

}

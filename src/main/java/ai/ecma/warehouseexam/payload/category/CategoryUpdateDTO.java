package ai.ecma.warehouseexam.payload.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryUpdateDTO {

    private String name;

    private String description;

}

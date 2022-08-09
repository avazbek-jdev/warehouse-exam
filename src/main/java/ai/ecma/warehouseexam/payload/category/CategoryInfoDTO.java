package ai.ecma.warehouseexam.payload.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryInfoDTO {

    private String name;

    private String description;

   // private List<Product> productList;

}

package ai.ecma.warehouseexam.payload.supplier;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierInfoDTO {

    private String name;

    private String phoneNumber;
}

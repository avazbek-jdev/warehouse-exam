package ai.ecma.warehouseexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenDTO {
    private String accessToken;
    private String refreshToken;
}

package ai.ecma.warehouseexam.service.interfaces;

import ai.ecma.warehouseexam.payload.TokenDTO;

public interface TokenService {

    TokenDTO verifyExpiration(TokenDTO tokenDTO);

}

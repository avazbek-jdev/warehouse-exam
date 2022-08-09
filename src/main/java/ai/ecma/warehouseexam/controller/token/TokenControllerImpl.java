package ai.ecma.warehouseexam.controller.token;

import ai.ecma.warehouseexam.payload.TokenDTO;
import ai.ecma.warehouseexam.service.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenControllerImpl implements TokenController{

    private final TokenService tokenService;

    @Override
    public TokenDTO verifyExpiration(TokenDTO tokenDTO) {
        return null;
    }
}

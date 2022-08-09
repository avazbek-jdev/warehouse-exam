package ai.ecma.warehouseexam.controller.auth;


import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.RegisterDTO;
import ai.ecma.warehouseexam.payload.SignInDTO;
import ai.ecma.warehouseexam.payload.TokenDTO;
import ai.ecma.warehouseexam.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public ApiResult<?> signUp(RegisterDTO registerDTO) {
        return authService.signUp(registerDTO);
    }

    @Override
    public ApiResult<TokenDTO> signIn(SignInDTO signInDTO) {
        return authService.signIn(signInDTO);
    }

    @Override
    public ApiResult<?> confirmAccount(Integer userId, String verificationCode) {
        return authService.confirmAccount(userId,verificationCode);
    }

}


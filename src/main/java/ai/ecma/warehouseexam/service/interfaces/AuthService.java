package ai.ecma.warehouseexam.service.interfaces;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.RegisterDTO;
import ai.ecma.warehouseexam.payload.SignInDTO;
import ai.ecma.warehouseexam.payload.TokenDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    ApiResult<?> signUp(RegisterDTO registerDTO);
    ApiResult<TokenDTO> signIn(SignInDTO signInDTO);

    ApiResult<?> confirmAccount(Integer userId, String verificationCode);

}

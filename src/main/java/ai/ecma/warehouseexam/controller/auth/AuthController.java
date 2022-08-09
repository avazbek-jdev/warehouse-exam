package ai.ecma.warehouseexam.controller.auth;


import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.RegisterDTO;
import ai.ecma.warehouseexam.payload.SignInDTO;
import ai.ecma.warehouseexam.payload.TokenDTO;
import ai.ecma.warehouseexam.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

@RequestMapping(AuthController.AUTHCONTROLLER_PATH)
public interface AuthController {
    String AUTHCONTROLLER_PATH = AppConstant.BASE_PATH + "/auth/";
    String SIGN_UP = "sign-up";
    String SIGN_IN = "sign-in";
    String CONFIRM_ACCOUNT = "confirm-account";

    @PostMapping(SIGN_UP)
    ApiResult<?> signUp(@RequestBody RegisterDTO registerDTO);

    @PostMapping(SIGN_IN)
    ApiResult<TokenDTO> signIn(@RequestBody SignInDTO signInDTO);

    @GetMapping(CONFIRM_ACCOUNT)
    ApiResult<?> confirmAccount(@RequestParam Integer userId,
                                @RequestParam String verificationCode);


}


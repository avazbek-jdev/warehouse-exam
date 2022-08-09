package ai.ecma.warehouseexam.service;


import ai.ecma.warehouseexam.entity.Role;
import ai.ecma.warehouseexam.entity.User;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.payload.RegisterDTO;
import ai.ecma.warehouseexam.payload.SignInDTO;
import ai.ecma.warehouseexam.payload.TokenDTO;
import ai.ecma.warehouseexam.repository.RoleRepository;
import ai.ecma.warehouseexam.repository.UserRepository;
import ai.ecma.warehouseexam.security.JWTProvider;
import ai.ecma.warehouseexam.service.interfaces.AuthService;
import ai.ecma.warehouseexam.utils.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    //  private final TwilioService twilioService;

    @Override
    public ApiResult<?> signUp(RegisterDTO registerDTO) {
        checkPhoneNumber(registerDTO.getPhoneNumber());
        checkPasswordAndPrepasword(registerDTO.getPassword(), registerDTO.getPrePassword());
        Role role = roleRepository.findByName(AppConstant.USER_ROLE).orElseThrow(() -> RestException.notFound("Role"));
        if (isValidMobileNo(registerDTO.getPhoneNumber())) {
            //946328802
            User user = new User(
                    registerDTO.getFirstName(),
                    registerDTO.getLastName(),
                    registerDTO.getPhoneNumber(),
                    registerDTO.getEmail(),
                    passwordEncoder.encode(registerDTO.getPassword()),
                    role,
                    generateVerificationCode()
            );
            userRepository.save(user);
            // sendPhoneNumber(user);
        } else {
            throw RestException.restThrow("Phone-Number is wrong");
        }
        return ApiResult.successResponse("Successfully registered. Please confirm by email");
    }

    @Override
    public ApiResult<TokenDTO> signIn(SignInDTO signInDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInDTO.getUsername(),
                            signInDTO.getPassword()
                    )
            );
            String accessToken = jwtProvider.generateAccessToken(signInDTO.getUsername());
            String refreshToken = jwtProvider.generateRefreshToken(signInDTO.getUsername());
            TokenDTO tokenDTO = new TokenDTO(accessToken, refreshToken);
            return ApiResult.successResponse(tokenDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.restThrow("Password or Username is wrong");
        }
    }


    @Override
    public ApiResult<?> confirmAccount(Integer userId, String verificationCode) {
        User user = getUserByIdOrElseThrow(userId);
        if (!Objects.equals(verificationCode, user.getVerificationCode()))
            throw RestException.restThrow("Wrong code");

        user.setEnabled(true);
        user.setVerificationCode(null);
        userRepository.save(user);
        return ApiResult.successResponse("Successfully confirmed");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(username).orElseThrow(() -> RestException.notFound("User"));
    }

    public static boolean isValidMobileNo(String str) {
//        Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Pattern pattern = Pattern.compile("^[+]998([- ])?(90|91|93|94|95|98|99|33|97|71)([- ])?(\\d{3})([- ])?(\\d{2})([- ])?(\\d{2})$");
        Matcher match = pattern.matcher(str);
        return (match.find() && match.group().equals(str));
    }

//    private void sendPhoneNumber(User user) {
//        twilioService.sendVerificationCode(
//                user.getPhoneNumber(),
//                user.getVerificationCode()
//        );
//    }

    private User getUserByIdOrElseThrow(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> RestException.notFound("User"));
    }

    private void checkPasswordAndPrepasword(String password, String prePassword) {
        if (!Objects.equals(password, prePassword))
            throw RestException.restThrow("Password and Prepassword not equal");
    }

    private void checkPhoneNumber(String phoneNumber) {
        boolean exist = userRepository.existsByPhoneNumber(phoneNumber);
        if (exist) throw RestException.alreadyExist("User");
    }

    private String generateVerificationCode() {
        String code = String.valueOf((int) (Math.random() * 1_000_000_000));
        return code.substring(0, 6);
    }

//    private void checkPhoneNumber(String phoneNumber, Integer id) {
//        boolean exist = userRepository.existsByPhoneNumberAndId(phoneNumber, id);
//        if (exist) throw RestException.alreadyExist("User");
//    }
}
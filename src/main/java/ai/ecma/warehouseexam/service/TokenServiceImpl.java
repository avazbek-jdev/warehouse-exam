package ai.ecma.warehouseexam.service;

import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.exception.TokenExpiredException;
import ai.ecma.warehouseexam.payload.TokenDTO;
import ai.ecma.warehouseexam.security.JWTProvider;
import ai.ecma.warehouseexam.service.interfaces.TokenService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.access.secret}")
    private String accessKey;
    @Value("${jwt.refresh.secret}")
    private String refreshKey;

    private final JWTProvider jwtProvider;

    @Override
    public TokenDTO verifyExpiration(TokenDTO tokenDTO) {

        try {
            String username = Jwts
                    .parser()
                    .setSigningKey(accessKey)
                    .parseClaimsJws(tokenDTO.getAccessToken())
                    .getBody()
                    .getSubject();
        } catch (TokenExpiredException ex) {
            ex.printStackTrace();

            try {

                String username = Jwts
                        .parser()
                        .setSigningKey(refreshKey)
                        .parseClaimsJws(tokenDTO.getRefreshToken())
                        .getBody()
                        .getSubject();

                String refreshToken = jwtProvider.generateRefreshToken(username);
                String accessToken = jwtProvider.generateAccessToken(username);

                TokenDTO newTokenDTO = new TokenDTO(accessToken,refreshToken);
                return newTokenDTO;

            } catch (Exception e) {
                e.printStackTrace();
                throw RestException.maker("Refresh token is invalid", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            throw RestException.restThrow("Access token is expired!");
        }

            return tokenDTO;
        }


}

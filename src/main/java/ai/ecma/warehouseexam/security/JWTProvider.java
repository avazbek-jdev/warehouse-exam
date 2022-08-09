package ai.ecma.warehouseexam.security;


import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.exception.TokenExpiredException;
import ai.ecma.warehouseexam.utils.AppConstant;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JWTProvider {
    @Value("${jwt.access.secret}")
    private String accessKey;
    @Value("${jwt.refresh.secret}")
    private String refreshKey;
    private final long accessTokenLifeTime = 24 * 60 * 60 * 1000;
    private final long refreshTokenLifeTime = 7 * 24 * 60 * 60 * 1000;

    public String generateAccessToken(String username) {

        return AppConstant.BEARER_TOKEN +  Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())//BIRTHDATE
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenLifeTime))//DEAD DATE
                .signWith(SignatureAlgorithm.HS512, accessKey)
                .compact();
    }

    public String generateRefreshToken(String username) {

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())//BIRTHDATE
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenLifeTime))//DEAD DATE
                .signWith(SignatureAlgorithm.HS512, refreshKey)
                .compact();

        return AppConstant.BEARER_TOKEN + token;
    }

    public String getUsernameFromToken(String token) {
        try {
            token = token.substring(AppConstant.BEARER_TOKEN.length());
            String username = Jwts
                    .parser()
                    .setSigningKey(accessKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return username;

        }catch (ExpiredJwtException ex){
            ex.printStackTrace();
            throw new TokenExpiredException();
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.forbidden();
        }
    }


}

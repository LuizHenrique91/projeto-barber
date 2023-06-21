package br.com.barber.service.impl;

import br.com.barber.model.UserCredentials;
import br.com.barber.service.TokenService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${webservice.secret}")
    private String secret;
    @Override
    public Boolean isValid(String token) {
        try {
            getClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException  e){
            return false;
        }
    }

    @Override
    public String generateToken(UserCredentials userCredentials) {
        LocalDateTime localDateTime = LocalDateTime.now().plusHours(1);
        Date expiration = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setIssuer("Podologo")
                .setSubject(userCredentials.getId())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private Jws<Claims> getClaimsJws(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    }
}

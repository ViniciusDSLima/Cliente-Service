package com.example.clienteservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.clienteservice.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        }
        catch (JWTCreationException jwtCreationException){
            throw new RuntimeException("erro enquanto o token era gerado " + jwtCreationException);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException jwtVerificationException){
            return "Token negado";
        }
    }

    private Instant genExpirationDate(){
        ZoneId lisbonZone = ZoneId.of("Europe/Lisbon");

        LocalDateTime dateTimeLisbon = LocalDateTime.now(lisbonZone).plusHours(1);
        return dateTimeLisbon.toInstant(ZoneOffset.ofTotalSeconds(lisbonZone.getRules().getOffset(dateTimeLisbon).getTotalSeconds()));
    }
}

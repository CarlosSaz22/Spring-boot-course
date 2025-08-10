package com.auth.jwt.security;

import com.auth.jwt.dto.RequestDto;
import com.auth.jwt.entity.AuthUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;


    private Algorithm algorithm;

    @Autowired
    private RouteValidator routeValidator;

    // Inicializamos el algoritmo con la clave secreta
    @PostConstruct
    protected void init() {
        algorithm = Algorithm.HMAC256(secret);
    }

    public String createToken(AuthUser authUser) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000); // 1 hora

        return JWT.create()
                .withSubject(authUser.getUserName())
                .withClaim("id", authUser.getId())
                .withClaim("role", authUser.getRole())
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .sign(algorithm);
    }

    public boolean validate(String token, RequestDto requestDto) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    // Puedes agregar validaciones extras, p.ej: .withIssuer("tu-emisor")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);

            // Validar rol y ruta si es necesario
            if (!isAdmin(decodedJWT) && routeValidator.isAdmin(requestDto)) {
                return false;
            }
            return true;
        } catch (JWTVerificationException e) {
            return false; // token inválido o expirado
        }
    }

    public String getUserNameFromToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private boolean isAdmin(DecodedJWT decodedJWT) {
        String role = decodedJWT.getClaim("role").asString();
        return "admin".equals(role);
    }
}
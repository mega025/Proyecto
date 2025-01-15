package com.app.foodapp.security;

// Estrctura de un tokken que va a ser generado


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

//    secretKey = jwt.secret del archivo application.properties
    @Value("${jwt.secret}")
    private String secretKey;

//    entre 30min y 1h no más
    @Value("${jwt.expiration}")
    private Long expirationTime;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secretKey));
    }
//    Jwt :Libreria de seguridad de tokens
//    setSubject: Elemento que enviamos al token
//    setIssuedAt: fecha de creacion del token
//    setExpiration:cuadno caduca.  Se seleciona la  fecha actual en milisegundo + la fecha de expiración
//    singWith:Firma  con la clave privada
//    compact : para generar el token publico
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + this.expirationTime))
                .signWith(this.getSigningKey())
                .compact();
    }

//    verificamos  que mi token privado coincida con el token de la solicitud  que llega
    public boolean validateToken(String token) {
        try {
            JwtParser parser = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build();
            parser.parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String extractEmail(String token) {
        JwtParser parser = Jwts.parser()
                .setSigningKey(this.getSigningKey())
                .build();
        Claims claims = parser.parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}

package br.com.quatty.backend.application.config.security;

import br.com.quatty.backend.business.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    @Value("${quatty.jwt.expiration}")
    private String expiration;

    @Value("${quatty.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Date startDate =  new Date();
        Date expirationDate = new Date(startDate.getTime() + Long.parseLong(expiration));
        Key secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        return Jwts.builder()
                .setIssuer("QUATTY'S API")
                .setSubject(user.getId().toString())
                .setIssuedAt(startDate)
                .setExpiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }
}

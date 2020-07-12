package com.shubham.SpringSecurity.security.jwt;

import com.shubham.SpringSecurity.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final String jwtSecret = "shubhamlatkar";

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .setExpiration(new Date((new Date()).getTime() + 500000L))
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println(String.format("Invalid JWT signature: {}", e.getMessage()));
        } catch (MalformedJwtException e) {
            System.out.println(String.format("Invalid JWT token: {}", e.getMessage()));
        } catch (ExpiredJwtException e) {
            System.out.println(String.format("JWT token is expired: {}", e.getMessage()));
        } catch (UnsupportedJwtException e) {
            System.out.println(String.format("JWT token is unsupported: {}", e.getMessage()));
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("JWT claims string is empty: {}", e.getMessage()));
        }
        return false;
    }
}

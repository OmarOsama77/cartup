package com.example.CartUp.auth.security;

import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${app.security.jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${app.security.jwt.access-expiration}")
    private long accessExpiration;

    public String extractUserEmail(String token){

        return extractAllClaims(token).getSubject();
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isAccessTokenValid(String token, UserDetails userDetails){
        //check on the signature
        final String userEmail = extractUserEmail(token);
        return userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public Claims extractAllClaims(String token) {
        System.out.println("arrived about");
         try{
             Claims claims = Jwts.parser().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
             return claims;
         }catch (Exception e){
             throw new ApplicationException(ErrorCode.INVALID_REFRESH_TOKEN);
         }


    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}

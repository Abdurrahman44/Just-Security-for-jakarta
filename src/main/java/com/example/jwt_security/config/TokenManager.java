package com.example.jwt_security.config;

import com.example.jwt_security.Entities.Role;
import com.example.jwt_security.Entities.Users;
import com.example.jwt_security.utils.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
public class TokenManager {

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Users user) {

        String[] rolesOfStrings = new String[0];
        if (!user.getRoles().isEmpty())
            for (Role role : user.getRoles()) {
                rolesOfStrings = List.of(role.getRoleName()).stream().toArray(String[]::new);
            }

        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("username", user.getEmail());
        claims.put("firstName", user.getName());
        claims.put("lastName", user.getLastName());
        claims.put("roles", rolesOfStrings);
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(Utils.EXPIRE_TIME));
        return generateToken(claims, user, tokenValidity);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, Date tokenValidity) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(tokenValidity)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(Utils.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
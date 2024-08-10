package org.gucardev.securityexample4.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.gucardev.securityexample4.service.JwtEncoderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtEncoderServiceImpl implements JwtEncoderService {

    @Value("${jwt-variables.secret-key}")
    private String secretKey;

    @Value("${jwt-variables.expiration-time}")
    private long jwtExpiration;

    public String generateToken(Authentication authentication, UUID tokenSign) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray());
        claims.put("tokenSign", tokenSign);
        return generateToken(claims, authentication.getName());
    }

    private String generateToken(Map<String, Object> extraClaims, String username) {
        return buildToken(extraClaims, username, jwtExpiration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            String username,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}

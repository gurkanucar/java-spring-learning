package org.gucardev.securityexample4.service;

import io.jsonwebtoken.Claims;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public interface JwtDecoderService {

    String extractUsername(String token);

    List<String> extractRoles(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    boolean isTokenExpired(String token);

    UUID extractTokenVersion(String token);

    boolean isTokenValid(String token, UUID tokenSign);

}

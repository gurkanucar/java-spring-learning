package org.gucardev.rediscachingexample.service;

import org.gucardev.rediscachingexample.entity.Token;

import java.util.List;
import java.util.UUID;

public interface TokenService {

    Token save(Token token);

    void deleteById(UUID tokenId);

    List<Token> findAll();

    Token findById(UUID tokenId);
}

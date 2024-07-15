package org.gucardev.rediscachingexample.repository;

import org.gucardev.rediscachingexample.entity.Token;

import java.util.List;

public interface TokenRepository {

    Token save(Token token);

    void deleteById(String tokenId);

    List<Token> findAll();
}

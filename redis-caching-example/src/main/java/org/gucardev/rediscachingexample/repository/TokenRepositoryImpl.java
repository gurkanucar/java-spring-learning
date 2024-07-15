package org.gucardev.rediscachingexample.repository;


import lombok.RequiredArgsConstructor;
import org.gucardev.rediscachingexample.config.RedisConfig;
import org.gucardev.rediscachingexample.entity.Token;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Repository
@RequiredArgsConstructor
class TokenRepositoryImpl implements TokenRepository {

    public static final String TOKEN_REDIS_HASH = "token";
    private final RedisTemplate redisTemplate;
    private final RedisConfig redisConfig;

    @Override
    public Token save(Token token) {
        if (isNull(token.getId())) token.setId(UUID.randomUUID().toString());
        token.setExpiration(redisConfig.getTtl());
        redisTemplate.opsForHash().put(TOKEN_REDIS_HASH, token.getId(), token);
        return token;
    }

    @Override
    public void deleteById(String tokenId) {
        redisTemplate.opsForHash().delete(TOKEN_REDIS_HASH, tokenId);
    }

    @Override
    public List<Token> findAll() {
        return redisTemplate.opsForHash().values(TOKEN_REDIS_HASH);
    }
}

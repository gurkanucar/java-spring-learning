package org.gucardev.rediscachingexample.controller;

import lombok.RequiredArgsConstructor;
import org.gucardev.rediscachingexample.entity.Token;
import org.gucardev.rediscachingexample.service.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/tokens")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @GetMapping
    public Iterable<Token> findAll() {
        return tokenService.findAll();
    }

    @GetMapping("/{tokenId}")
    public Token findById(@PathVariable UUID tokenId) {
        return tokenService.findById(tokenId);
    }

    @PostMapping
    public Token createOrUpdateToken(@RequestBody Token token) {
        return tokenService.save(token);
    }

    @DeleteMapping("/{tokenId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteToken(@PathVariable UUID tokenId) {
        tokenService.deleteById(tokenId);
    }
}
